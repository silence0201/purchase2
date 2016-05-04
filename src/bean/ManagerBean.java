package bean;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: 获取总经理需要的数据
 * Author: silence
 * Update: silence(2016-05-01 17:18)
 */
public class ManagerBean {

    //获取总经理需要的审核的通知
    public ArrayList<String> getCheckNotices(){
        return null ;
    }

    //获取公司内本月所有的申请信息
    public ArrayList<Request> getRequestList(){
        return null ;
    }

    //获取审核历史记录
    public ArrayList<Request> getCheckList(){
        return null ;
    }
}
