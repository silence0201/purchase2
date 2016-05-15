package service.impl;

import dao.ItemDao;
import dao.RequestDao;
import dao.impl.ItemDaoImpl;
import dao.impl.RequestDaoImpl;
import entity.Item;
import entity.Request;
import service.IRequestService;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description: IRequestServiceImpl
 * Author: silence
 * Update: silence(2016-05-10 21:59)
 */
public class IRequestServiceImpl implements IRequestService {
    private RequestDao requestDao ;
    private ItemDao itemDao ;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public Request getRequestInfo(String requestID) {
        return null;
    }

    @Override
    public boolean modifyRequest(Request request) {
        return false;
    }

    @Override
    public boolean addRequest(Request request) {
        return false;
    }

    //----获取信息----//
    @Override
    public HashMap<String,ArrayList<String>> getRequestNotices(String requestID) {
        requestDao = new RequestDaoImpl() ;

        ArrayList<String> notices = requestDao.notices(requestID) ;
        ArrayList<String> passes = requestDao.pass(requestID) ;
        ArrayList<String> refuses = requestDao.refuse(requestID) ;

        HashMap<String,ArrayList<String>> result = new HashMap<>() ;

        result.put("notices",notices) ;
        result.put("passes",passes) ;
        result.put("refuses",refuses) ;

        return result;
    }

    @Override
    public ArrayList<Request> getRequestList(String requestManID) {
        requestDao = new RequestDaoImpl() ;
        ArrayList<Request> requests = requestDao.requestList(requestManID,"申请员") ;
        return requests ;
    }

    @Override
    public int countOfTime(String requestManID) {
        requestDao = new RequestDaoImpl() ;

        return requestDao.countOfTime(requestManID);
    }

    @Override
    public double countOfMoney(String requestManID) {
        requestDao =  new RequestDaoImpl() ;

        return requestDao.countOfMoney(requestManID);
    }

    @Override
    public ArrayList<Item> items() {
        itemDao = new ItemDaoImpl() ;

        ArrayList<Item>items = itemDao.itemInfo() ;

        return items;
    }

}
