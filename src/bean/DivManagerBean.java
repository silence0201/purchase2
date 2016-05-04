package bean;

import entity.Request;

import java.util.ArrayList;

/**
 * Description: 获取部门经理所需要的数据
 * Author: silence
 * Update: silence(2016-05-01 17:13)
 */
public class DivManagerBean {

    //获取需要审核的列表信息
    public ArrayList<String> getCheckNotice(){
        return null ;
    }

    //获取该部门本月申请信息列表
    public ArrayList<Request> getRequesList(String position){
        return null ;
    }

    //获取审核记录列表
    public ArrayList<Request> getCheckList(String divManagerID){
        return null ;
    }
}
