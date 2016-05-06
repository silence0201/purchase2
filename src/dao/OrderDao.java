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
    public abstract Order getByID(Integer orderID) ;  //通过编号获取详细信息
    public abstract int count(String userID) ;  //获取一个月内采购统计信息
    public abstract Order lastOrder(String userID) ; //获取最后一个订单信息
}
