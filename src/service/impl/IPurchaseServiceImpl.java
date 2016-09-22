package service.impl;

import dao.ItemDao;
import dao.OrderDao;
import dao.PlanDao;
import dao.ProviderDao;
import dao.ProviderItemDao;
import dao.RequestDao;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.PlanDaoImpl;
import dao.impl.ProviderDaoImpl;
import dao.impl.ProviderItemDaoImpl;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
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
    private RequestDao requestDao ;
    private PlanDao planDao ;
    private OrderDao orderDao ;
    private ProviderDao providerDao ;
    private ProviderItemDao providerItemDao ;
    private ItemDao itemDao ;

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
    public boolean handle(String requestID,String status,String reason,String userID) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(new Integer(requestID)) ;
        request.setReason(reason);
        request.setRequestStatus(status);
        request.setAuditor(new UserDaoImpl().getByID(userID));
        request.setAuditTime(new Date(DateUtil.currentDate()));

        boolean flag = requestDao.modifyRequest(request) ;

        return flag;
    }

    @Override
    public Order makeOrder(String planID,String userID,String provideritemID) {
        providerItemDao = new ProviderItemDaoImpl() ;
        Plan plan = getPlanByID(planID) ;
        Order order = new Order() ;
        Provideritem provideritem = providerItemDao.getByID(new Integer(provideritemID)) ;
        order.setOrderMan(new UserDaoImpl().getByID(userID));
        order.setOrderStatus("采购中");
        order.setOrderTime(new Date(DateUtil.currentDate()));
        order.setPlan(plan);
        order.setProvideritem(provideritem);
        order.setTotalCost(provideritem.getPrice() * plan.getNumber());
        orderDao.add(order) ;

        return order;
    }

    @Override
    public ArrayList<Provideritem> getItemProviders(String itemID) {
        providerItemDao = new ProviderItemDaoImpl() ;

        ArrayList<Provideritem> provideritems = providerItemDao.providers(new Integer(itemID)) ;

        return provideritems;
    }

    @Override
    public boolean addProvider(String providerName,String provinces,String contant,String tele,String address) {

        providerDao = new ProviderDaoImpl() ;

        Provider provider = new Provider() ;
        provider.setProviderName(providerName);
        provider.setProvinces(provinces);
        provider.setContant(contant);
        provider.setTele(tele);
        provider.setAddress(address);
        provider.setStatus("有效");

        boolean flag = providerDao.add(provider) ;

        return flag ;
    }

    @Override
    public boolean modifyProvider(String providerID,String providerName,String provinces,String contant,String tele,String address) {
        providerDao = new ProviderDaoImpl() ;

        Provider provider =  new Provider() ;
        provider.setProviderId(new Integer(providerID));
        provider.setAddress(address);
        provider.setTele(tele);
        provider.setProviderName(providerName);
        provider.setProvinces(provinces) ;
        provider.setContant(contant);
        provider.setStatus("有效");

        boolean flag = providerDao.modify(provider) ;

        return flag;
    }

    @Override
    public boolean delProvider(String providerID) {
        providerDao = new ProviderDaoImpl() ;
        Provider provider = providerDao.getByID(new Integer(providerID)) ;

        boolean flag = providerDao.del(provider) ;

        return flag;
    }

    @Override
    public boolean modifyProviderItem(String providerItemID, String price, String quality) {
        providerItemDao = new ProviderItemDaoImpl() ;

        Provideritem provideritem = providerItemDao.getByID(new Integer(providerItemID)) ;
        provideritem.setPrice(new Double(price));
        provideritem.setQuality(quality);

        boolean flag =  providerItemDao.modify(provideritem)  ;

        return flag;
    }

    @Override
    public boolean delProviderItem(String providerItemID) {
        providerItemDao = new ProviderItemDaoImpl() ;

        Provideritem provider = providerItemDao.getByID(new Integer(providerItemID)) ;

        boolean flag =  providerItemDao.del(provider) ;

        return flag;
    }

    @Override
    public boolean addProviderItem(String providerID, String itemName, String price, String quality) {
        itemDao = new ItemDaoImpl() ;
        providerItemDao = new ProviderItemDaoImpl() ;
        providerDao = new ProviderDaoImpl() ;

        Provideritem provideritem = new Provideritem() ;

        Item item = itemDao.add(itemName) ;
        Provider provider = providerDao.getByID(new Integer(providerID)) ;

        provideritem.setPrice(new Double(price));
        provideritem.setQuality(quality);
        provideritem.setItem(item);
        provideritem.setProvider(provider);
        provideritem.setStatus("有效");

        boolean flag = providerItemDao.add(provideritem) ;

        return flag;
    }

    @Override
    public ArrayList<Provideritem> getProvideritemByProviderID(String providerID) {
        providerItemDao = new ProviderItemDaoImpl() ;

        ArrayList<Provideritem> provideritems = providerItemDao.items(new Integer(providerID)) ;

        return provideritems;
    }

    @Override
    public Provider getProviderByID(String providerID) {
        providerDao = new ProviderDaoImpl() ;

        Provider provider = providerDao.getByID(new Integer(providerID)) ;

        return provider;
    }

    @Override
    public Provideritem getProviderItemByID(String provideItemID) {
        providerItemDao = new ProviderItemDaoImpl() ;
        Provideritem provideritem  = providerItemDao.getByID(new Integer(provideItemID)) ;

        return provideritem;
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


    @Override
    public Request getRequestByID(String requestID) {
        requestDao = new RequestDaoImpl() ;
        Request request = requestDao.requestInfo(new Integer(requestID)) ;

        return request ;
    }

    @Override
    public Plan getPlanByID(String planID) {
        planDao = new PlanDaoImpl() ;

        Plan plan = planDao.planInfo(new Integer(planID)) ;

        return plan;
    }


}
