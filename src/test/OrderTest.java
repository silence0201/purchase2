package test;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;

/**
 * Description: OrderTest
 * Author: silence
 * Update: silence(2016-05-07 00:10)
 */
public class OrderTest {
    public static void main(String[] args) {
//        order();
        count();
    }
    public static void order(){
        OrderDao orderDao = new OrderDaoImpl() ;

        System.out.println(orderDao.getByID(1001));
    }

    public static void count(){
        OrderDao orderDao = new OrderDaoImpl() ;

        System.out.println(orderDao.count("P00001"));
    }
}
