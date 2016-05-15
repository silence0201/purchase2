package dao.impl;

import dao.ImportDao;
import entity.Import;
import entity.Item;
import entity.Order;
import entity.Plan;
import entity.Request;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: ImportDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 00:57)
 */
public class ImportDaoImpl implements ImportDao {
    @Override
    public int count(String stockManID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql  = "select count(export) from Export export" +
                " where export.stockMan.userId=:userID" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",stockManID) ;

        Number num = (Number) query.uniqueResult();
        int count = num.intValue() ;

        tx.commit();
        session.close() ;
        return count;
    }

    @Override
    public ArrayList<Import> importsList(String stockManID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select import from Import import" +
                " where import.stockMan.userId=:userID" +
                " order by import.importTime desc ";
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",stockManID) ;

        ArrayList<Import> imports = (ArrayList<Import>) query.list();

        tx.commit();
        session.close() ;
        return imports;
    }

    @Override
    public boolean add(Import impart) {
        Session session = HibernateUtil.getSession() ;
        Order order = impart.getOrder() ;
        order.setOrderStatus("入库完成");
        ArrayList<Request> requests = (ArrayList<Request>) order.getPlan().getRequests();

        for (Request request : requests){
            request.setRequestStatus("到货");
        }

        Item item = order.getPlan().getItem() ;

        int invertory = item.getInventory() + order.getPlan().getNumber() ;
        item.setInventory(invertory);

        try {
            session.beginTransaction() ;
            session.save(item) ;
            session.update(order);
            session.save(impart) ;
            for (Request request : requests){
                session.update(request);
            }
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }
        return false;
    }
}
