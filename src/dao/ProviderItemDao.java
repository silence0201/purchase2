package dao;

import entity.Item;
import entity.Provider;
import entity.Provideritem;

import java.util.ArrayList;

/**
 * Description: ProviderItemDao
 * Author: silence
 * Update: silence(2016-05-01 15:53)
 */
public interface ProviderItemDao {
    public abstract ArrayList<Item> items(Integer providerID) ; //获取供应商供应的商品
    public abstract boolean add(Provideritem provideritem) ; // 添加供应商提供的商品
    public abstract boolean modify(Provideritem provideritem) ;//修改供应商提供的商品
    public abstract boolean del(Provideritem provideritem) ; //删除供应商提供的商品信息
}
