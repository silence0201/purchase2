package dao.impl;

import dao.OrderDao;
import entity.Order;
import entity.Plan;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DateUtil;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Description: OrderDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 00:06)
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public ArrayList<Order> orderList(String userID) {
        Session session = HibernateUtil.getSession() ;
        ArrayList<Order> result = null ;

        Transaction tx = session.beginTransaction() ;
        if (userID.startsWith("P")){
            String hql = "select orders from Order orders " +
                    "where orders.orderMan.userId=:userID" +
                    " order by orders.orderTime desc " ;
            Query query = session.createQuery(hql) ;
            query.setParameter("userID",userID) ;

            result = (ArrayList<Order>) query.list();
        }else{
            String hql = "from Order " ;
            Query query = session.createQuery(hql) ;
            result = (ArrayList<Order>)query.list() ;
        }

        tx.commit();
        session.close() ;
        return result;
    }

    @Override
    public boolean add(Order order) {
        Session session = HibernateUtil.getSession() ;
        Plan plan = order.getPlan() ;
        plan.setPlanStauts("已受理");

        try {
            session.beginTransaction() ;
            session.save(order) ;
            session.update(plan);
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }
        return false;
    }

    @Override
    public Order getByID(Integer orderID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        Order order = (Order) session.get(Order.class,orderID);


        tx.commit();
        session.close() ;

        return order;
    }

    @Override
    public int count(String userID) {
        Session session = HibernateUtil.getSession() ;
        int count = 0 ;
        Transaction tx = session.beginTransaction() ;

        Date startDate = DateUtil.getStartOfMonth() ;
        if (userID.startsWith("P")) {
            String hql = "select count(orders) from Order orders" +
                    " where orders.orderMan.userId=:userID and orders.orderTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("userID",userID) ;
            query.setParameter("start",startDate) ;

            Number num = (Number) query.uniqueResult() ;
            if (num != null){
                count = num.intValue() ;
            }
        }else {
            String hql = "select count(orders) from Order orders" +
                    " where orders.orderTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("start",startDate) ;

            Number num = (Number) query.uniqueResult() ;
            if (num != null){
                count = num.intValue() ;
            }
        }
        tx.commit();
        session.close() ;
        return count;
    }

    @Override
    public double sum(String userID) {
        Session session = HibernateUtil.getSession() ;
        double sum = 0 ;
        Transaction tx = session.beginTransaction() ;

        Date startDate = DateUtil.getStartOfMonth() ;
        if (userID.startsWith("P")) {
            String hql = "select sum(orders.plan.totalCost) from Order orders" +
                    " where orders.orderMan.userId=:userID and orders.orderTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("userID",userID) ;
            query.setParameter("start",startDate) ;

            Number num = (Number) query.uniqueResult() ;
            if (num != null){
                sum = num.doubleValue() ;
            }
        }else {
            String hql = "select sum(orders.plan.totalCost) from Order orders" +
                    " where orders.orderTime>=:start" ;
            Query query = session.createQuery(hql) ;
            query.setParameter("start",startDate) ;

            Number num = (Number) query.uniqueResult() ;
            if (num != null){
                sum = num.doubleValue() ;
            }
        }
        tx.commit();
        session.close() ;
        return sum ;
    }

    @Override
    public Order lastOrder(String userID) {
        ArrayList<Order> orders = orderList(userID) ;

        return orders.get(0);
    }

}
