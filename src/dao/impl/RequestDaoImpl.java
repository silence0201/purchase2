package dao.impl;

import dao.RequestDao;
import entity.Request;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DateUtil;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Description: RequestDaoImpl
 * Author: silence
 * Update: silence(2016-05-06 17:09)
 */
public class RequestDaoImpl implements RequestDao {
    @Override
    public ArrayList<String> notices(String requestManID) {
        ArrayList<String> result;

        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;


        String hql = "select request.requestId from Request request " +
                "where request.requestMan.userId=:requestManID and request.requestStatus ='到货' " +
                " order by request.requestTime desc " ;

        Query query = session.createQuery(hql) ;
        query.setParameter("requestManID",requestManID) ;

        result = (ArrayList<String>) query.list();

        tx.commit();
        session.close() ;

        return result;
    }

    @Override
    public Request requestInfo(int requestID) {

        Session session  = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        Request request = (Request) session.get(Request.class,requestID);

        tx.commit();
        session.close() ;

        return request;
    }

    @Override
    public boolean modifyRequest(Request request) {

        Session session = HibernateUtil.getSession() ;

        try {
            Transaction tx =  session.beginTransaction() ;
            session.update(request);
            tx.commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false ;
        }finally {
            session.close() ;
        }
    }

    @Override
    public ArrayList<Request> requestList(String requestManID) {
        ArrayList<Request> result ;

        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select request from Request request " +
                "where request.requestMan.userId=:requestManID " +
                "order by request.requestTime desc " ;

        Query query = session.createQuery(hql) ;
        query.setParameter("requestManID",requestManID) ;
        result = (ArrayList<Request>) query.list();

        tx.commit();
        session.close() ;



        return result;
    }

    @Override
    public boolean addRequest(Request request) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.save(request);
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
            return false ;
        }finally {
            session.close() ;
        }
    }

    @Override
    public ArrayList<String> checkRequest(String position) {
        ArrayList<String> result = new ArrayList<>() ;

        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select request.requestId from Request request" +
                " where request.requestStatus='未审核' and request.totalCost > :start and request.totalCost <= :end "+
                " and request.requestMan.position like :position" ;

        Query query = session.createQuery(hql) ;

        if (position.equals("采购员")){
            query.setParameter("start",0.0) ;
            query.setParameter("end",1000.0) ;
            query.setParameter("position","%") ;
        }else if (position.endsWith("部门经理")){
            String[] s = position.split("经理") ;
            String p = s[0] + "申请员" ;
            query.setParameter("start",1000.0) ;
            query.setParameter("end",5000.0) ;
            query.setParameter("position",p) ;
        }else{
            query.setParameter("start",5000.0) ;
            query.setParameter("end",100000.0) ;
            query.setParameter("position","%") ;
        }


        result = (ArrayList<String>) query.list();

        tx.commit();
        session.close() ;

        return result;
    }

    @Override
    public int countOfTime(String userID) {
        Session session = HibernateUtil.getSession() ;
        int count = 0;
        Transaction tx = session.beginTransaction() ;

        Date startDate = DateUtil.getStartOfMonth();
        System.out.println(startDate);

        if (userID.startsWith("D")){
            String position = new UserDaoImpl().getByID(userID).getPosition() ;
            String p = position.split("经理")[0] ;
            String hql = "select count(request) from Request request" +
                    " where request.requestMan.position =:position and request.requestTime >=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("position",p+"申请员") ;
            query.setParameter("start",startDate) ;
            Number num = (Number)query.uniqueResult() ;
            count = num.intValue() ;
        }else if (userID.startsWith("R")){
            String hql = "select count(request) from Request request" +
                    " where request.requestMan.userId=:userID and request.requestTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("userID",userID) ;
            query.setParameter("start",startDate) ;

            Number num = (Number)query.uniqueResult() ;
            count = num.intValue() ;
        }else{
            String  hql = "select count (request) from Request request" +
                    " where request.requestTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("start",startDate) ;

            Number num = (Number)query.uniqueResult() ;
            count = num.intValue() ;
        }
        tx.commit();
        session.close() ;

        return count ;
    }

    @Override
    public double countOfMoney(String userID) {
        Session session = HibernateUtil.getSession() ;
        double sum = 0;
        Transaction tx = session.beginTransaction() ;

        Date startDate = DateUtil.getStartOfMonth();

        if (userID.startsWith("D")){
            String position = new UserDaoImpl().getByID(userID).getPosition() ;
            String p = position.split("经理")[0] ;
            String hql = "select sum(request.totalCost) from Request request" +
                    " where request.requestMan.position like :position and request.requestTime >=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("position",p) ;
            query.setParameter("start",startDate) ;
            Number num = (Number)query.uniqueResult() ;
            sum = num.doubleValue() ;
        }else if(userID.startsWith("R")){
            String hql = "select sum(request.totalCost) from Request request" +
                    " where request.requestMan.userId=:userID and request.requestTime >=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("userID",userID) ;
            query.setParameter("start",startDate) ;

            Number num = (Number)query.uniqueResult() ;
            sum = num.doubleValue() ;
        }else{
            String  hql = "select sum(request.totalCost) from Request request" +
                    " where request.requestTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("start",startDate) ;

            Number num = (Number)query.uniqueResult() ;
            sum = num.doubleValue() ;
        }
        tx.commit();
        session.close() ;

        return sum ;
    }
}
