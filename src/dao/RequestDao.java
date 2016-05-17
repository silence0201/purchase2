package dao;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: RequestDao
 * Author: silence
 * Update: silence(2016-05-01 14:33)
 */
public interface RequestDao {
    public abstract ArrayList<String> notices(String requestManID) ;   //获取到货的通知通知单号
    public abstract ArrayList<String> pass(String requestID) ; //获取通过的通知单号
    public abstract ArrayList<String> refuse(String requestID) ;  //获取被拒的通知单号
    public abstract Request requestInfo(int requestID) ;  //根据编号获取详细信息
    public abstract boolean modifyRequest(Request request) ;  //修改指定的申请单
    public abstract ArrayList<Request> requestList(String requestManID,String position) ;  //获取申请单列表
    public abstract ArrayList<Request> needPurchaseRequest() ;  //获取需要采购的申请单
    public abstract Integer addRequest(Request request) ;  //添加申请信息
    public abstract boolean delRequest(Request request) ; //删除申请单信息
    public abstract ArrayList<String> checkRequest(String position) ;  //获取需要审核的信息
    public abstract ArrayList<Request> checkRequestList(String auditorID) ;  //获取审核信息
    public abstract int countOfTime(String userID) ;  //获取指定用户月申请次数
    public abstract double countOfMoney(String userID); //获取指定用户月申请金额
    public abstract int countOfCheck(String userID) ;  //获取用户月审核次数
    public abstract int countOfDepart(String position) ; //获取部门审核通过的次数
}
