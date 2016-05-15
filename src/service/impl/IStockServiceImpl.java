package service.impl;

import dao.ExportDao;
import dao.ImportDao;
import dao.ItemDao;
import dao.impl.ExportDaoImpl;
import dao.impl.ImportDaoImpl;
import dao.impl.ItemDaoImpl;
import entity.Export;
import entity.Import;
import entity.Item;
import service.IStockService;

import java.util.ArrayList;

/**
 * Description: IStockServiceImpl
 * Author: silence
 * Update: silence(2016-05-11 20:29)
 */
public class IStockServiceImpl implements IStockService {

    ItemDao itemDao  ;
    ImportDao importDao ;
    ExportDao exportDao ;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public boolean addImport(Import impart) {
        return false;
    }

    @Override
    public boolean addExport(Export export) {
        return false;
    }

    @Override
    public ArrayList<Item> getItemsInfo() {
        itemDao = new ItemDaoImpl() ;

        ArrayList<Item> items = itemDao.itemInfo() ;

        return items;
    }

    @Override
    public ArrayList<Import> getImportList(String stockManID) {
        importDao = new ImportDaoImpl() ;

        ArrayList<Import> imports = importDao.importsList(stockManID) ;

        return imports;
    }

    @Override
    public ArrayList<Export> getExportList(String stockManID) {
        exportDao = new ExportDaoImpl() ;

        ArrayList<Export> exports = exportDao.exportList(stockManID) ;
        return exports;
    }

    @Override
    public int getcountOfImport(String stockManID) {

        importDao  = new ImportDaoImpl() ;

        int count = importDao.count(stockManID) ;

        return count;
    }

    @Override
    public int getCountOfExport(String stockManID) {
        exportDao = new ExportDaoImpl() ;

        int count = exportDao.count(stockManID) ;

        return count;
    }
}
