package dao.impl;

import dao.OrderDao;
import entity.Order;
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

        Transaction tx = session.beginTransaction() ;

        String hql = "select orders from Order orders " +
                "where orders.orderMan.userId=:userID" +
                " order by orders.orderTime desc " ;
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",userID) ;

        ArrayList<Order> result = (ArrayList<Order>) query.list();
        tx.commit();
        session.close() ;
        return result;
    }

    @Override
    public boolean add(Order order) {
        Session session = HibernateUtil.getSession() ;
        try {
            session.beginTransaction() ;
            session.save(order) ;
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

        Transaction tx = session.beginTransaction() ;

        Date startDate = DateUtil.getStartOfMonth() ;

        String hql = "select count(orders) from Order orders" +
                " where orders.orderMan.userId=:userID and orders.orderTime>=:start" ;
        Query query = session.createQuery(hql) ;
        query.setParameter("userID",userID) ;
        query.setParameter("start",startDate) ;

        Number num = (Number) query.uniqueResult() ;
        int count = num.intValue() ;

        tx.commit();
        session.close() ;
        return count;
    }

    @Override
    public Order lastOrder(String userID) {
        ArrayList<Order> orders = orderList(userID) ;

        return orders.get(0);
    }
}
