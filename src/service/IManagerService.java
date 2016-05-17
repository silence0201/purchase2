package service;

import entity.Order;
import entity.Request;

import java.util.ArrayList;

/**
 * Description: IManagerService
 * Author: silence
 * Update: silence(2016-05-01 17:41)
 */
public interface IManagerService {
    //需求审核处理
    public abstract boolean handle(String requestID,String status,String reason,String userID) ;

    //需求信息获取
    public abstract Request getRequestInfo (String requestID) ;

    //-----获取信息-----//

    //获取总经理需要的审核的通知
    public abstract ArrayList<String> getCheckNotices();

    //获取公司内本月所有的申请信息
    public abstract ArrayList<Request> getRequestList();

    //获取当月申请单数
    public abstract int getCountOfRequest() ;

    //获取当月总的申请金额
    public abstract double getSumOfRequest() ;

    //获取公司的订单信息
    public abstract ArrayList<Order> getOrders() ;

    //获取本月订单数
    public abstract int  getCountOfOrder() ;

    //获取本月订单金额
    public abstract double getSumOfOrder() ;

    //获取审核信息
    public abstract ArrayList<Request> getCheckList(String userID) ;

    //获取当月审核的次数
    public abstract int getCountOfCheck(String userID) ;

    //获取当月审核的金额
    public abstract  double getSumOfCheck(String userID) ;

}
