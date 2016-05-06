package test;

import dao.RequestDao;
import dao.UserDao;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Request;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Description: RequestTest
 * Author: silence
 * Update: silence(2016-05-06 17:20)
 */
public class RequestTest {
    public static void main(String[] args) {
//        notice();
//        modify();
//        info();
//        requests();
//        add();
//        check();
        time();
    }



    //获取通知测试
    public static void notice(){
        RequestDao requestDao = new  RequestDaoImpl() ;

        ArrayList<String> notices = requestDao.notices("R00011") ;
        System.out.println("需要处理的通知:");
        System.out.println(notices);
    }

    //获取详细信息测试
    public static void info(){
        RequestDao requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(1006) ;

        System.out.println(request);
    }

    //修改申请记录
    public static void modify(){
        RequestDao requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(1001) ;

        request.setRequestStatus("完成");

        System.out.println(requestDao.modifyRequest(request));
    }

    //获取所有申请单详细信息
    public static void requests(){
        RequestDao requestDao = new RequestDaoImpl() ;

        ArrayList<Request> requests = requestDao.requestList("R00011") ;

        Iterator<Request> iterator = requests.iterator() ;

        while (iterator.hasNext()){
            Request request = iterator.next() ;
            System.out.println(request);
        }
//        System.out.println(requests);
    }

    public static void  add(){
        RequestDao requestDao = new RequestDaoImpl() ;

        Request request = new Request() ;
        UserDao userDao = new UserDaoImpl() ;
//        request.setRequestId(new Integer(1017));
        request.setItem(requestDao.requestInfo(1001).getItem());
        request.setNumber(12);
        request.setTotalCost(request.getNumber()* request.getItem().getAvePrice());
        request.setRequestMan(userDao.getByID("R00012"));
        request.setRequestTime(new Date(System.currentTimeMillis()));
        request.setRequestStatus("未审核");
        request.setReason("没有理由");

        boolean flag = requestDao.addRequest(request) ;
        System.out.println(flag);
    }

    public static void check(){
        RequestDao requestDao = new RequestDaoImpl() ;

        ArrayList<String> result1 = requestDao.checkRequest("总经理") ;
        ArrayList<String> result2 = requestDao.checkRequest("采购员") ;
        ArrayList<String> result3 = requestDao.checkRequest("财务部门经理") ;
        System.out.println("总经理需要审核:"+result1);
        System.out.println("采购员需要审核:"+result2);
        System.out.println("财务部门经理需要审核:"+result3);

    }

    public static void time(){
        RequestDao requestDao = new RequestDaoImpl() ;

        System.out.println(requestDao.countOfTime("R00011"));
        System.out.println(requestDao.countOfTime("D00001"));
    }
}
