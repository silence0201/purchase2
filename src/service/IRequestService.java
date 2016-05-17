package service;

import entity.Item;
import entity.Request;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description: IRequestService
 * Author: silence
 * Update: silence(2016-05-01 17:24)
 */
public interface IRequestService {

    //查询指定的申请单信息
    public abstract Request getRequestInfo(String requestID) ;

    //修改申请单信息
    public abstract boolean modifyRequest(String requestID,String number) ;

    //添加申请单信息
    public abstract Integer addRequest(String userID,String number,String itemID,String reason) ;

    //删除申请单
    public abstract boolean delRequest(String requestID) ;

    //------获取信息----------//

    //获取已经被处理的申请单列表
    public abstract HashMap<String,ArrayList<String>> getRequestNotices(String requestID) ;

    //获取历史申请信息
    public abstract ArrayList<Request> getRequestList(String requestManID) ;

    //获取当月申请次数
    public abstract int countOfTime(String requestManID) ;

    //获取当月申请金额数
    public abstract double countOfMoney(String requestManID) ;

    //获取商品列表
    public abstract ArrayList<Item> items() ;
}
