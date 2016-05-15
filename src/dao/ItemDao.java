package dao;

import entity.Item;

import java.util.ArrayList;

/**
 * Description: ItemDao
 * Author: silence
 * Update: silence(2016-05-01 15:25)
 */
public interface ItemDao {
    public abstract ArrayList<Item> itemInfo() ;   //获取物品的详细信息列表
    public abstract Item item(Integer itemID) ; //获取指定商品的信息
}
