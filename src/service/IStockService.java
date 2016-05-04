package service;

import entity.Export;
import entity.Import;

/**
 * Description: IStockManService
 * Author: silence
 * Update: silence(2016-05-01 17:27)
 */
public interface IStockService {

    //添加入库单信息
    public abstract boolean addImport(Import impart) ;

    //添加出库单信息
    public abstract boolean addExport(Export export) ;

}
