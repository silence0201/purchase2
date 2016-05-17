package service.impl;

import dao.OrderDao;
import dao.RequestDao;
import dao.impl.OrderDaoImpl;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Order;
import entity.Request;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.IManagerService;
import util.DateUtil;
import util.HibernateUtil;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Description: IManagerServiceImpl
 * Author: silence
 * Update: silence(2016-05-13 18:35)
 */
public class IManagerServiceImpl implements IManagerService {

    RequestDao requestDao  ;
    OrderDao orderDao ;

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public boolean handle(String requestID,String status,String reason,String userID) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;
        request.setReason(reason);
        request.setRequestStatus(status);
        request.setAuditor(new UserDaoImpl().getByID(userID));
        request.setAuditTime(new Date(DateUtil.currentDate()));

        boolean flag = requestDao.modifyRequest(request) ;

        return flag;
    }

    @Override
    public Request getRequestInfo(String requestID) {
        Session session = HibernateUtil.getSession() ;

        Request request = (Request) session.get(Request.class,new Integer(requestID));

        session.close() ;

        return request;
    }

    @Override
    public ArrayList<String> getCheckNotices() {
        requestDao = new RequestDaoImpl() ;

        ArrayList<String> checks = requestDao.checkRequest("总经理") ;

        return checks;
    }

    @Override
    public ArrayList<Request> getRequestList() {
        requestDao = new RequestDaoImpl() ;

        ArrayList<Request> requests = requestDao.requestList("M00001","总经理") ;

        return requests ;
    }

    @Override
    public int getCountOfRequest() {
        requestDao = new RequestDaoImpl() ;

        int  count = requestDao.countOfTime("M00001") ;

        return count;
    }

    @Override
    public double getSumOfRequest() {
        requestDao = new RequestDaoImpl() ;

        double sum = requestDao.countOfMoney("M00001") ;

        return sum ;
    }

    @Override
    public ArrayList<Order> getOrders() {
        orderDao = new OrderDaoImpl() ;

        ArrayList<Order> orders = orderDao.orderList("D000001") ;

        return orders;
    }

    @Override
    public int getCountOfOrder() {
        orderDao = new OrderDaoImpl() ;

        int count = orderDao.count("M00001") ;

        return count;
    }

    @Override
    public double getSumOfOrder() {
        orderDao = new OrderDaoImpl() ;

        double sum = orderDao.sum("M00001") ;

        return sum;
    }

    @Override
    public ArrayList<Request> getCheckList(String userID) {
        requestDao = new RequestDaoImpl() ;

        ArrayList<Request> checks = requestDao.checkRequestList(userID) ;

        return checks;
    }

    @Override
    public int getCountOfCheck(String userID) {
        requestDao = new RequestDaoImpl() ;

        int count = requestDao.countOfCheck(userID) ;

        return count;
    }

    @Override
    public double getSumOfCheck(String userID) {
        requestDao = new RequestDaoImpl() ;

        double sum = requestDao.countOfMoney(userID) ;

        return sum;
    }
}
