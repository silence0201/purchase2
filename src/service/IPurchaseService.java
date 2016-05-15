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

    //处理采购计划
    public abstract boolean handle(Plan plan) ;

    //添加供应商
    public abstract boolean addProvider(Provider provider) ;

    //修改供应商
    public abstract boolean modifyProvider(Provider provider) ;

    //删除供应商信息
    public abstract boolean delProvider(Provider provider) ;

    //修改供应商商品信息
    public abstract boolean modifyProviderItem(Provideritem provideritem) ;

    //删除供应商商品信息
    public abstract boolean delProviderItem(Provideritem provideritem) ;

    //增加供应商商品信息
    public abstract boolean addProviderItem(Provideritem provideritem) ;

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
}
