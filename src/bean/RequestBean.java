package bean;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: 获取与申请信息相关的数据
 * Author: silence
 * Update: silence(2016-05-01 16:16)
 */
public class RequestBean {

    //获取已经被处理的申请单列表
    public  ArrayList<String> getRequestNotices(String requestID){
        return null ;
    }

    //获取历史申请信息
    public ArrayList<Request> getRequestList(String requestManID){
        return null ;
    }

    //获取当月申请次数
    public int countOfTime(String requestManID){
        return 0 ;
    }

    //获取当月申请金额数
    public double countOfMoney(String requestManID){
        return 0 ;
    }
}
