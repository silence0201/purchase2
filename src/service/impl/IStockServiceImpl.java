package service.impl;

import dao.ExportDao;
import dao.ImportDao;
import dao.ItemDao;
import dao.OrderDao;
import dao.RequestDao;
import dao.UserDao;
import dao.impl.ExportDaoImpl;
import dao.impl.ImportDaoImpl;
import dao.impl.ItemDaoImpl;
import dao.impl.OrderDaoImpl;
import dao.impl.RequestDaoImpl;
import dao.impl.UserDaoImpl;
import entity.Export;
import entity.Import;
import entity.Item;
import entity.Order;
import entity.Request;
import entity.User;
import service.IStockService;
import util.DateUtil;

import java.sql.Date;
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
    RequestDao requestDao ;
    UserDao userDao ;
    OrderDao orderDao ;

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public boolean addImport(String orderID,String userID) {
        importDao = new ImportDaoImpl() ;
        userDao = new UserDaoImpl() ;
        Import anImport= new Import() ;
        Order order = getOrderByID(orderID) ;
        User user = userDao.getByID(userID) ;
        anImport.setOrder(order);
        anImport.setStockMan(user);
        anImport.setImportTime(new Date(DateUtil.currentDate()));

        boolean flag = importDao.add(anImport) ;

        return flag;
    }

    @Override
    public boolean addExport(String requestID,String userID) {
        exportDao = new ExportDaoImpl() ;
        userDao = new UserDaoImpl() ;
        Export export = new Export() ;
        Request request = getRequestByID(requestID) ;
        User user = userDao.getByID(userID) ;
        export.setRequest(request);
        export.setStockMan(user);
        export.setExportTime(new Date(DateUtil.currentDate()));

        boolean flag = exportDao.add(export) ;

        return flag;
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

    @Override
    public Request getRequestByID(String requestID) {
        requestDao = new RequestDaoImpl() ;

        Request request = requestDao.requestInfo(Integer.parseInt(requestID)) ;

        return request;
    }

    @Override
    public Order getOrderByID(String orderID) {
        orderDao = new OrderDaoImpl() ;

        Order order = orderDao.getByID(new Integer(orderID)) ;

        return order;
    }
}
