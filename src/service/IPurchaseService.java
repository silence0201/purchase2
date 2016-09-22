package service;

import entity.Item;
import entity.Order;
import entity.Plan;
import entity.Provider;
import entity.Provideritem;
import entity.Request;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description: IPurchaseService
 * Author: silence
 * Update: silence(2016-05-01 17:30)
 */
public interface IPurchaseService {

    //汇总计划
    public abstract boolean creatrPlan() ;

    //处理审核信息
    public abstract boolean handle(String requestID,String status,String reason,String userID) ;

    //处理采购计划
    public abstract Order makeOrder(String planID,String userID,String provideritemID) ;

    //查看详细供应信息
    public abstract ArrayList<Provideritem> getItemProviders(String itemID) ;

    //添加供应商
    public abstract boolean addProvider(String providerName,String provinces,String contant,String tele,String address) ;

    //修改供应商
    public abstract boolean modifyProvider(String providerID,String providerName,String provinces,String contant,String tele,String address) ;

    //删除供应商信息
    public abstract boolean delProvider(String providerID) ;

    //修改供应商商品信息
    public abstract boolean modifyProviderItem(String providerItemID,String price,String quality) ;

    //删除供应商商品信息
    public abstract boolean delProviderItem(String providerItemID) ;

    //增加供应商商品信息
    public abstract boolean addProviderItem(String providerID, String itemName, String price, String quality) ;

    //-----获取信息-------//
    //获取需要审核的列表
    public ArrayList<String> getCheckNotices();

    //获取需要采购的列表
    public ArrayList<String> getPurchaseNotices();

    //获取最近审核记录
    public ArrayList<Request> getCheckList(String userID) ;

    //获取订单信息
    public ArrayList<Order> getOrderList(String purchaseManID);

    //获取供应商信息
    public ArrayList<Provider> getProviders();

    //获取供应商的按地区分类信息
    public Map<String,Integer> providerstat();

    //获取供应商提供商品的信息
    public ArrayList<Item> getProviderItem(Provider provider);

    //根据id获取申请单
    public abstract Request getRequestByID(String requestID) ;

    //根据id获取计划单
    public abstract Plan getPlanByID(String planID) ;

    //获取供应商的详细信息
    public abstract ArrayList<Provideritem> getProvideritemByProviderID(String providerID) ;

    //根据id获取供应商信息
    public abstract Provider getProviderByID(String providerID) ;

    //根据id获取商品条目
    public abstract Provideritem getProviderItemByID(String provideItemID) ;

}
