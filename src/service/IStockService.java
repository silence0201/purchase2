package service;

import entity.Export;
import entity.Import;
import entity.Item;
import entity.Order;
import entity.Request;

import java.util.ArrayList;

/**
 * Description: IStockManService
 * Author: silence
 * Update: silence(2016-05-01 17:27)
 */
public interface IStockService {

    //添加入库单信息
    public abstract boolean addImport(String orderID,String userID) ;

    //添加出库单信息
    public abstract boolean addExport(String requestID,String userID) ;

    //-----获取信息------//
    //获取物品的相关信息
    public abstract ArrayList<Item> getItemsInfo();

    //获取入库列表
    public abstract ArrayList<Import> getImportList(String stockManID);

    //获取出库列表
    public abstract ArrayList<Export> getExportList(String stockManID);

    //获取一月内入库次数
    public abstract int getcountOfImport(String stockManID);

    //获取一月内入库次数
    public abstract int getCountOfExport(String stockManID);

    //根据id获取申请信息
    public abstract Request getRequestByID(String requestID) ;

    //感觉id获取订单信息
    public abstract Order getOrderByID(String orderID) ;

}
