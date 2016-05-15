package test;

import dao.ItemDao;
import dao.PlanDao;
import dao.RequestDao;
import dao.impl.PlanDaoImpl;
import dao.impl.RequestDaoImpl;
import entity.Item;
import entity.Plan;
import entity.Request;
import util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Description: PlanTest
 * Author: silence
 * Update: silence(2016-05-14 15:00)
 */
public class PlanTest {
    public static void main(String[] args) {
        add();
    }

    public static void add(){
        RequestDao requestDao = new RequestDaoImpl() ;
        Request request1 = requestDao.requestInfo(1017) ;
        Request request2 = requestDao.requestInfo(1018) ;

        ArrayList<Request> requests  = new ArrayList<>() ;
        requests.add(request1) ;
        requests.add(request2) ;

        Plan plan  = new Plan() ;
        Item item = request1.getItem() ;

        plan.setRequests(requests);
        plan.setItem(item);
        plan.setNumber(request1.getNumber() + request2.getNumber());
        plan.setTotalCost(request1.getTotalCost() + request2.getTotalCost());
        plan.setPlanTime(new Date(DateUtil.currentDate()));
        plan.setPlanStauts("需采购");

        PlanDao planDao = new PlanDaoImpl() ;
        planDao.addPlan(plan) ;

    }
}
