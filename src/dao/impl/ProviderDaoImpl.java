package dao.impl;

import dao.ProviderDao;
import dao.ProviderItemDao;
import entity.Provider;
import entity.Provideritem;
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
            Transaction tx = session.beginTransaction() ;
            session.save(provider) ;
            tx.commit();
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
        ProviderItemDao providerItemDao = new ProviderItemDaoImpl() ;
        provider.setStatus("无效");

        boolean flag = modify(provider) ;
        ArrayList<Provideritem> items = providerItemDao.items(provider.getProviderId()) ;

        for (Provideritem item : items){
            flag = flag && providerItemDao.del(item) ;
        }

        return flag;
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

    @Override
    public Provider getByID(Integer providerID) {
        Session session = HibernateUtil.getSession() ;

        Provider provider = (Provider) session.get(Provider.class,providerID);

        session.close() ;
        return provider;
    }
}
