package dao.impl;

import dao.ProviderItemDao;
import entity.Item;
import entity.Provideritem;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: ProviderItemDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 01:16)
 */
public class ProviderItemDaoImpl implements ProviderItemDao {
    @Override
    public ArrayList<Item> items(Integer providerID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select provideritem from Provideritem provideritem " +
                "where provideritem.provider.id=:providerID" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("providerID",providerID) ;
        ArrayList<Item> items = (ArrayList<Item>) query.list();

        tx.commit();
        session.close() ;

        return items;
    }

    @Override
    public boolean add(Provideritem provideritem) {
        Session session = HibernateUtil.getSession() ;

        try {
            session.beginTransaction() ;
            session.save(provideritem) ;
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
    public boolean modify(Provideritem provideritem) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.update(provideritem);
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
    public boolean del(Provideritem provideritem) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.delete(provideritem);
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
