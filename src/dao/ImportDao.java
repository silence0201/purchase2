package dao;

import entity.Import;

import java.util.ArrayList;

/**
 * Description: ImportDao
 * Author: silence
 * Update: silence(2016-05-01 15:33)
 */
public interface ImportDao {
    public abstract int count(String stockManID);  //返回一个月内入库的次数
    public abstract ArrayList<Import> importsList(String stockManID) ; //返回入库列表
    public abstract boolean add(Import impart) ;  //添加入库信息
}
