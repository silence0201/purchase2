package service.impl;

import dao.OrderDao;
import dao.PlanDao;
import dao.ProviderDao;
import dao.RequestDao;
import dao.impl.OrderDaoImpl;
import dao.impl.PlanDaoImpl;
import dao.impl.ProviderDaoImpl;
import dao.impl.RequestDaoImpl;
import entity.Item;
import entity.Order;
import entity.Plan;
import entity.Provider;
import entity.Provideritem;
import entity.Request;
import service.IPurchaseService;
import util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Description: IPurchaseServiceImpl
 * Author: silence
 * Update: silence(2016-05-14 09:28)
 */
public class IPurchaseServiceImpl implements IPurchaseService {
    RequestDao requestDao ;
    PlanDao planDao ;
    OrderDao orderDao ;
    ProviderDao providerDao ;

    public ProviderDao getProviderDao() {
        return providerDao;
    }

    public void setProviderDao(ProviderDao providerDao) {
        this.providerDao = providerDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public PlanDao getPlanDao() {
        return planDao;
    }

    public void setPlanDao(PlanDao planDao) {
        this.planDao = planDao;
    }

    public RequestDao getRequestDao() {
        return requestDao;
    }

    public void setRequestDao(RequestDao requestDao) {
        this.requestDao = requestDao;
    }

    @Override
    public  boolean creatrPlan() {
        requestDao = new RequestDaoImpl() ;
        boolean flag = DateUtil.isPlan() ;
        planDao = new PlanDaoImpl() ;
        if (flag){
            ArrayList<Request> requests = requestDao.needPurchaseRequest() ;
            Map<Item,Plan> planMap = new HashMap<>() ;
            if (requests != null){
                for (Request request : requests){
                    Plan plan = planMap.get(request.getItem()) ;
                    if (plan == null){
                        plan = new Plan() ;
                        plan.setItem(request.getItem());
                        plan.setNumber(request.getNumber());
                        plan.setTotalCost(request.getTotalCost());
                        plan.setRequests(new ArrayList<Request>());
                        plan.getRequests().add(request) ;
                        planMap.put(request.getItem(),plan) ;
                    }else{
                        plan.setNumber(plan.getNumber() + request.getNumber());
                        plan.setTotalCost(plan.getTotalCost() + request.getTotalCost());
                        plan.getRequests().add(request) ;
                    }
                }
            }

            Iterator<Plan> plans = planMap.values().iterator() ;

            while (plans.hasNext()){
                Plan plan = plans.next() ;
                plan.setPlanTime(new Date(DateUtil.currentDate()));
                if (plan.getItem().getInventory() >= plan.getNumber() ){
                    plan.setPlanStauts("有货") ;
                }else{
                    plan.setPlanStauts("需受理") ;
                }
                flag = flag && planDao.addPlan(plan) ;
            }
        }
        return flag;
    }

    @Override
    public boolean handle(Plan plan) {
        return false;
    }

    @Override
    public boolean addProvider(Provider provider) {
        return false;
    }

    @Override
    public boolean modifyProvider(Provider provider) {
        return false;
    }

    @Override
    public boolean delProvider(Provider provider) {
        return false;
    }

    @Override
    public boolean modifyProviderItem(Provideritem provideritem) {
        return false;
    }

    @Override
    public boolean delProviderItem(Provideritem provideritem) {
        return false;
    }

    @Override
    public boolean addProviderItem(Provideritem provideritem) {
        return false;
    }

    @Override
    public ArrayList<String> getCheckNotices() {
        requestDao = new RequestDaoImpl() ;

        ArrayList<String> checks = requestDao.checkRequest("采购员") ;

        return checks;
    }

    @Override
    public ArrayList<String> getPurchaseNotices() {
        planDao = new PlanDaoImpl() ;

        ArrayList<String> needPlans = planDao.needOrderPlan() ;

        return needPlans;
    }

    @Override
    public ArrayList<Request> getCheckList(String userID) {
        requestDao = new RequestDaoImpl() ;

        ArrayList<Request> requests = requestDao.checkRequestList(userID) ;

        return requests;
    }

    @Override
    public ArrayList<Order> getOrderList(String purchaseManID) {
        orderDao = new OrderDaoImpl() ;

        ArrayList<Order> orders = orderDao.orderList(purchaseManID) ;

        return orders;
    }

    @Override
    public ArrayList<Provider> getProviders() {
        providerDao = new ProviderDaoImpl() ;

        ArrayList<Provider> providers = providerDao.providerList() ;

        return providers;
    }

    @Override
    public Map<String, Integer> providerstat() {
        return null;
    }

    @Override
    public ArrayList<Item> getProviderItem(Provider provider) {
        return null;
    }
}
