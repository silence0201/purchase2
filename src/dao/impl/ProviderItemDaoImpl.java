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
    public ArrayList<Provideritem> items(Integer providerID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select provideritem from Provideritem provideritem " +
                "where provideritem.provider.id=:providerID" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("providerID",providerID) ;
        ArrayList<Provideritem> provideritems = (ArrayList<Provideritem>) query.list();

        tx.commit();
        session.close() ;

        return provideritems;
    }
    @Override
    public ArrayList<Provideritem> providers(Integer itemID) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql = "select provideritem from Provideritem provideritem " +
                "where provideritem.item.itemId=:itemID" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("itemID",itemID) ;

        ArrayList<Provideritem> provideritems = (ArrayList<Provideritem>) query.list();

        tx.commit();
        session.close() ;

        return provideritems;
    }

    @Override
    public boolean delProvider(Integer providerID) {
        Session session = HibernateUtil.getSession() ;

        ArrayList<Provideritem> provideritems = this.items(providerID) ;

        try{
            session.beginTransaction() ;
            for (Provideritem provideritem : provideritems){
                this.del(provideritem) ;
            }
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }

        return false;
    }

    @Override
    public boolean add(Provideritem provideritem) {
        Session session = HibernateUtil.getSession() ;

        Item item = provideritem.getItem() ;

        int count =  1 ;
        double sum = provideritem.getPrice() ;
        for (Provideritem items : providers(item.getItemId())){
            sum += items.getPrice() ;
            count++ ;
        }
        double avgPrice = sum / count ;
        item.setAvePrice(avgPrice);

        try {
            session.beginTransaction() ;
            if (session.get(Item.class,item.getItemId()) == null){
                session.update(item);
            }else{
                session.save(item) ;
            }
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

        Item item = provideritem.getItem() ;

        int count = 0 ;
        double sum = 0 ;
        for (Provideritem items : providers(item.getItemId())){
            sum += items.getPrice() ;
            count++ ;
        }
        double avgPrice = sum / count ;
        item.setAvePrice(avgPrice);

        try {
            session.beginTransaction() ;
            session.update(item);
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

        Item item = provideritem.getItem() ;

        int count = -1 ;
        double sum = -1 * provideritem.getPrice() ;
        for (Provideritem items : providers(item.getItemId())){
            sum += items.getPrice() ;
            count++ ;
        }

        double avgPrice = sum / count ;
        item.setAvePrice(avgPrice);

        try {
            session.beginTransaction() ;
            session.update(item);
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
