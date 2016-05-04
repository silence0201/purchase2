package action;

import com.opensymphony.xwork2.ActionSupport;
import service.IStockService;

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


}
