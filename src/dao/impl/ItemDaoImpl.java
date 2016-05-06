package dao.impl;

import dao.ItemDao;
import entity.Item;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Description: ItemDaoImpl
 * Author: silence
 * Update: silence(2016-05-06 22:27)
 */
public class ItemDaoImpl implements ItemDao {

    @Override
    public ArrayList<Item> itemInfo() {

        Session session = HibernateUtil.getSession() ;

        Transaction tx =  session.beginTransaction() ;
        String hql ="from Item" ;

        Query query = session.createQuery(hql) ;

        ArrayList<Item> result = (ArrayList<Item>) query.list();

        return result;
    }
}
