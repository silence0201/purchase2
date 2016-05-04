package dao;

import entity.Provider;

import java.util.ArrayList;

/**
 * Description: ProviderDao
 * Author: silence
 * Update: silence(2016-05-01 15:50)
 */
public interface ProviderDao {
    public abstract ArrayList<Provider> providerList() ; //返回供应商列表
    public abstract boolean add(Provider provider) ; //添加供应商信息
    public abstract boolean del(Provider provider) ; //删除供应商信息
    public abstract boolean modify(Provider provider) ; //修改供应商信息
}
