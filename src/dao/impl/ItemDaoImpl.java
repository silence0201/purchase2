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

    @Override
    public Item item(Integer itemID) {
        Session session  = HibernateUtil.getSession() ;

        Item item = (Item) session.get(Item.class,itemID);

        return item;
    }

    @Override
    public Item add(String itemName) {
        Session session = HibernateUtil.getSession() ;

        Transaction tx = session.beginTransaction() ;

        String hql  = "select item from Item item" +
                " where item.itemName=:itemName" ;
        Query query =  session.createQuery(hql) ;
        query.setParameter("itemName",itemName) ;

        ArrayList<Item> items = (ArrayList<Item>)query.list() ;

        session.close() ;

        if (items !=null && items.size() != 0){
            return items.get(0) ;
        }

        Item item = new Item() ;
        item.setAvePrice(0);
        item.setItemName(itemName);
        item.setAvePrice(0);
        item.setInventory(0);

        session = HibernateUtil.getSession() ;
        session.beginTransaction() ;
        session.save(item) ;
        session.getTransaction().commit();
        session.close() ;

        return item;
    }
}
