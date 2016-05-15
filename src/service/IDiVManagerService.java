package service;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: IDiVManagerService
 * Author: silence
 * Update: silence(2016-05-01 17:38)
 */
public interface IDiVManagerService {

    //审核处理
    public abstract boolean handle(Request request) ;

    //获取申请单的详细信息
    public abstract Request getRequestInfo(int requestID) ;

    //-----获取信息-----//

    //获取需要审核的列表信息
    public abstract ArrayList<String> getCheckNotice(String position);

    //获取该部门本月申请信息列表
    public abstract ArrayList<Request> getRequesList(String position);

    //获取审核记录列表
    public abstract ArrayList<Request> getCheckList(String divManagerID);

    //获取部门月申请次数
    public abstract int getCountOfTime(String userID) ;

    //获取部门申请金额
    public abstract double getSumOfMoney(String userID) ;

    //获取月审核次数
    public abstract int getCountOfCheck(String userID) ;

    //获取本部门审核通过次数
    public abstract int getCountOfCheckPart(String position) ;
}
