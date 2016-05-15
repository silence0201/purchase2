package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Export;
import entity.Import;
import entity.Item;
import entity.User;
import service.IStockService;
import service.impl.IStockServiceImpl;

import java.util.ArrayList;

/**
 * Description: StockAction
 * Author: silence
 * Update: silence(2016-05-01 17:51)
 */
public class StockAction extends ActionSupport {

    private IStockService service ;

    private int orderID ;
    private int requestID ;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public IStockService getService() {
        return service;
    }

    public void setService(IStockService service) {
        this.service = service;
    }

    //查看采购单
    public String infoOrder(){
        return SUCCESS ;
    }

    //查看需求单
    public String infoRequest(){
        return SUCCESS ;
    }

    //确认入库单
    public String addExport(){
        return SUCCESS ;
    }

    //确定出库单
    public String addImport(){
        return SUCCESS ;
    }

    //跳转到库存状态页面
    public String status(){
        service = new IStockServiceImpl() ;

        User user = (User) ActionContext.getContext().getSession().get("user") ;
        String userID = user.getUserId() ;

        ArrayList<Item> items = service.getItemsInfo() ;
        int countOfExport  = service.getCountOfExport(userID) ;
        int countOfImport = service.getcountOfImport(userID) ;
        ActionContext.getContext().put("items",items);
        ActionContext.getContext().put("countOfExport",countOfExport);
        ActionContext.getContext().put("countOfImport",countOfImport);

        return SUCCESS ;
    }

    //跳转到入库列表页面
    public String impart(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        String userID = user.getUserId() ;

        ArrayList<Import> imports = service.getImportList(userID) ;
        ActionContext.getContext().put("imports",imports);

        return SUCCESS ;
    }

    //跳转到出库列表页面
    public String export(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        String userID = user.getUserId() ;

        ArrayList<Export> exports = service.getExportList(userID) ;
        ActionContext.getContext().put("exports",exports);

        return SUCCESS ;
    }


}
