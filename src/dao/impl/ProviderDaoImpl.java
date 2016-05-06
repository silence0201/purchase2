package dao.impl;

import dao.ProviderDao;
import entity.Provider;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: ProviderDaoImpl
 * Author: silence
 * Update: silence(2016-05-07 01:08)
 */
public class ProviderDaoImpl implements ProviderDao {
    @Override
    public ArrayList<Provider> providerList() {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "from Provider " ;
        Query query = session.createQuery(hql) ;
        ArrayList<Provider> providers = (ArrayList<Provider>) query.list();

        tx.commit();
        session.close() ;

        return providers;
    }

    @Override
    public boolean add(Provider provider) {
        Session session = HibernateUtil.getSession() ;

        try{
            session.beginTransaction() ;
            session.save(provider) ;
            session.getTransaction().commit();
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }

        return false ;
    }

    @Override
    public boolean del(Provider provider) {
        Session session = HibernateUtil.getSession() ;

        try {
            session.beginTransaction() ;
            session.delete(provider);
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
    public boolean modify(Provider provider) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.update(provider);
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
