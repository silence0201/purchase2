package dao;

import entity.Export;

import java.util.ArrayList;

/**
 * Description: Export
 * Author: silence
 * Update: silence(2016-05-01 15:38)
 */
public interface ExportDao {
    public abstract int count(String stockManID) ;  //返回一个月内出库的次数
    public abstract ArrayList<Export> exportList(String stockManID)  ;//返回出库信息列表
    public abstract boolean add(Export export) ; //添加出库信息
}
