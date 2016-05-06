package dao;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: RequestDao
 * Author: silence
 * Update: silence(2016-05-01 14:33)
 */
public interface RequestDao {
    public abstract ArrayList<String> notices(String requestManID) ;   //获取处理后的通知通知单号
    public abstract Request requestInfo(int requestID) ;  //根据编号获取详细信息
    public abstract boolean modifyRequest(Request request) ;  //修改指定的申请单
    public abstract ArrayList<Request> requestList(String requestManID) ;  //获取申请单列表
    public abstract boolean addRequest(Request request) ;  //添加申请信息
    public abstract ArrayList<String> checkRequest(String position) ;  //获取需要审核的信息
    public abstract int countOfTime(String userID) ;  //获取指定用户月申请次数
    public abstract double countOfMoney(String userID); //获取指定用户月申请金额
}
