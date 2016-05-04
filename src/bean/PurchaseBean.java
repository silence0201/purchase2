package bean;

import entity.Item;
import entity.Order;
import entity.Provider;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description: 采购员所需要的基本信息
 * Author: silence
 * Update: silence(2016-05-01 16:51)
 */
public class PurchaseBean {

    //获取需要审核的列表
    public ArrayList<String> getCheckNotices(){
        return null ;
    }

    //获取需要采购的列表
    public ArrayList<String> getPurchaseNotices(){
        return null ;
    }

    //获取订单信息
    public ArrayList<Order> getOrderList(String purchaseManID){
        return null ;
    }

    //获取供应商信息
    public ArrayList<Provider> getProviders(){
        return null ;
    }

    //获取供应商的按地区分类信息
    public Map<String,Integer> providerstat(){
        return null ;
    }

    //获取供应商提供商品的信息
    public ArrayList<Item> getProviderItem(Provider provider){
        return null ;
    }
}
