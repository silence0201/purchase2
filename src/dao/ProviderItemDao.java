package dao;

import entity.Provideritem;

import java.util.ArrayList;

/**
 * Description: ProviderItemDao
 * Author: silence
 * Update: silence(2016-05-01 15:53)
 */
public interface ProviderItemDao {
    public abstract ArrayList<Provideritem> items(Integer providerID) ; //获取供应商供应的商品
    public abstract boolean add(Provideritem provideritem) ; // 添加供应商提供的商品
    public abstract boolean modify(Provideritem provideritem) ;//修改供应商提供的商品
    public abstract boolean del(Provideritem provideritem) ; //删除供应商提供的商品信息
    public abstract ArrayList<Provideritem> providers(Integer itemID) ;   //获取供应商品的供应商
    public abstract boolean delProvider(Integer providerID) ;  //删除供应商的信息
    public abstract Provideritem getByID(Integer providerItemID) ; //根绝ID获取对象
}
