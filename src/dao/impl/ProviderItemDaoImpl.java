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
            return true ;
        }catch (Exception e){
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }

        return false;
    }

    @Override
    public Provideritem getByID(Integer providerItemID) {
        Session session = HibernateUtil.getSession() ;

        Provideritem provideritem = (Provideritem) session.get(Provideritem.class,providerItemID);

        session.close() ;

        return provideritem;
    }

    @Override
    public boolean add(Provideritem provideritem) {
        Session session = HibernateUtil.getSession() ;

        Item item = provideritem.getItem() ;

        int count =  1 ;
        double sum = provideritem.getPrice() ;
        for (Provideritem items : providers(item.getItemId())){
            if (items.getStatus().equals("有效")){
                sum += items.getPrice() ;
                count++ ;
            }
        }
        double avgPrice = sum / count ;
        item.setAvePrice(avgPrice);

        try {
            session.beginTransaction() ;
            session.update(item);
//            if (session.get(Item.class,item.getItemId()) == null){
//                session.update(item);
//            }else{
//                session.save(item) ;
//            }
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
            if (items.getStatus().equals("有效")){
                if (items.getProviderItemId().equals(provideritem.getProviderItemId())){
                    sum += provideritem.getPrice() ;
                }else{
                    sum += items.getPrice() ;
                }
                count++ ;
            }
        }

        double avgPrice = 0 ;
        if (count != 0) {
            avgPrice = sum / count ;
        }


        try {
            session.beginTransaction() ;
            session.update(provideritem);

            item.setAvePrice(avgPrice);
            session.update(item);
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
        provideritem.setStatus("无效");

        Item item = provideritem.getItem() ;

        int count = -1 ;
        double sum = -1 * provideritem.getPrice() ;
        for (Provideritem items : providers(item.getItemId())){
            if (items.getStatus().equals("有效")){
                sum += items.getPrice() ;
                count++ ;
            }
        }
        if (count !=0){
            double avgPrice = sum / count ;
            item.setAvePrice(avgPrice);
        }else{
            item.setAvePrice(0);
        }


        try {
            Transaction tx = session.beginTransaction() ;
            session.update(provideritem);
            session.update(item);
            tx.commit();
            return true ;
        }catch (Exception e){
            System.out.println(e);
            session.getTransaction().rollback();
        }finally {
            session.close() ;
        }
        return false;
    }

}
