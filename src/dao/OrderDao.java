package dao;


import entity.Order;

import java.util.ArrayList;

/**
 * Description: OrderDao
 * Author: silence
 * Update: silence(2016-05-01 15:46)
 */
public interface OrderDao {
    public abstract ArrayList<Order> orderList(String userID) ;  //返回订单列表
    public abstract boolean add(Order order) ;   //添加订单
}
