package service.impl;

import dao.ItemDao;
import dao.RequestDao;
import dao.UserDao;
import dao.impl.ItemDaoImpl;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Item;
import entity.Request;
import entity.User;
import service.IRequestService;

import java.sql.Date;
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
    private UserDao userDao ;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

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
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;

        return request;
    }

    @Override
    public boolean modifyRequest(String requestID,String number) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;
        request.setNumber(Integer.parseInt(number));
        boolean flag = requestDao.modifyRequest(request) ;

        return flag;
    }

    @Override
    public Integer addRequest(String userID,String number,String itemID,String reason) {
        requestDao = new RequestDaoImpl() ;
        itemDao = new ItemDaoImpl() ;
        userDao = new UserDaoImpl() ;

        Request request = new Request() ;
        Item item = itemDao.item(Integer.parseInt(itemID)) ;
        User user = userDao.getByID(userID) ;
        request.setItem(item);
        request.setRequestMan(user);
        request.setNumber(Integer.parseInt(number));
        request.setTotalCost(Integer.parseInt(number)* item.getAvePrice());
        request.setRequestTime(new Date(System.currentTimeMillis()));
        int inventory = item.getInventory() ;
        if (inventory > request.getNumber()){
            request.setRequestStatus("有货");
        }else{
            request.setRequestStatus("未审核");
        }
        request.setReason(reason);

        return requestDao.addRequest(request);
    }

    @Override
    public boolean delRequest(String requestID) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;

        boolean flag = requestDao.delRequest(request) ;

        return flag;
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
