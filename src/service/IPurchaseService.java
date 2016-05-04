package service;

import entity.Plan;
import entity.Provider;
import entity.Provideritem;

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

}
