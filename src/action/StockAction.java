package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import entity.Export;
import entity.Import;
import entity.Item;
import entity.Order;
import entity.Request;
import entity.User;
import service.IStockService;
import service.impl.IStockServiceImpl;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Description: StockAction
 * Author: silence
 * Update: silence(2016-05-01 17:51)
 */
public class StockAction extends ActionSupport {

    private IStockService service ;

    public IStockService getService() {
        return service;
    }

    public void setService(IStockService service) {
        this.service = service;
    }

    //初始化出库信息页面
    public String initExport(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        ArrayList<Export> exports = service.getExportList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        Request request = service.getRequestByID(requestID) ;

        if (request == null){
            request = new Request() ;
        }

        ActionContext.getContext().put("r",request);
        ActionContext.getContext().put("exports",exports);

        return SUCCESS ;

    }

    //确认入库单
    public String addExport(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];

        boolean flag = service.addExport(requestID,user.getUserId()) ;
        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //跳转到登记页面
    public String checkIn(){
        service = new IStockServiceImpl() ;

        User user = (User) ActionContext.getContext().getSession().get("user") ;
        String userID = user.getUserId() ;

        int countOfExport  = service.getCountOfExport(userID) ;
        int countOfImport = service.getcountOfImport(userID) ;
        ActionContext.getContext().put("countOfExport",countOfExport);
        ActionContext.getContext().put("countOfImport",countOfImport);

        return SUCCESS ;
    }

    //初始化入库信息页面
    public String initImport(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        String userID = user.getUserId() ;

        ArrayList<Import> imports = service.getImportList(userID) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String orderID = ((String[]) pragram.get("orderID"))[0];
        Order order = service.getOrderByID(orderID) ;
        if (order == null){
            order = new Order() ;
        }

        ActionContext.getContext().put("imports",imports);
        ActionContext.getContext().put("o",order);

        return SUCCESS ;
    }

    //确定出库单
    public String addImport(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String orderID = ((String[]) pragram.get("orderID"))[0];

        boolean flag = service.addImport(orderID,user.getUserId()) ;

        if (flag){
            return SUCCESS ;
        }

        return ERROR ;
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

    //出库信息列表
    public String exportList(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        ArrayList<Export> exports = service.getExportList(user.getUserId()) ;
        int count = 0 ;
        int allCount = 0 ;
        Date start  = DateUtil.getStartOfMonth() ;
        for (Export export : exports){
            if (export.getExportTime().after(start)){
                count++ ;
            }
            allCount++ ;
        }


        ActionContext.getContext().put("exports",exports);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("allCount",allCount);

        return SUCCESS ;
    }

    //入库信息列表
    public String importList(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        ArrayList<Import>  imports = service.getImportList(user.getUserId()) ;


        int count = 0 ;
        int allCount = 0 ;
        Date start  = DateUtil.getStartOfMonth() ;
        for (Import impart : imports){
            if (impart.getImportTime().after(start)){
                count++ ;
            }
            allCount++ ;
        }

        ActionContext.getContext().put("imports",imports);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("allCount",allCount);

        return SUCCESS ;
    }

    //出入单详细信息
    public String exportInfo(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        ArrayList<Export> exports = service.getExportList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String exportID = ((String[]) pragram.get("exportID"))[0];

        int count = 0 ;
        int allCount = 0 ;
        Date start  = DateUtil.getStartOfMonth() ;
        Export expart = null;
        for (Export export : exports){
            if (export.getExportTime().after(start)){
                count++ ;
            }
            if (export.getExportId().equals(new Integer(exportID))){
                expart = export ;
            }
            allCount++ ;
        }


        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("allCount",allCount);
        if (expart !=null){
            ActionContext.getContext().put("export",expart);
            return SUCCESS ;
        }
        return INPUT ;
    }

    //入库详细信息
    public String importInfo(){
        service = new IStockServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        ArrayList<Import> imports = service.getImportList(user.getUserId()) ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String importID = ((String[]) pragram.get("importID"))[0];

        int count = 0 ;
        int allCount = 0 ;
        Date start  = DateUtil.getStartOfMonth() ;
        Import impart = null;
        for (Import im : imports){
            if (im.getImportTime().after(start)){
                count++ ;
            }
            if (im.getImportId().equals(new Integer(importID))){
                impart = im ;
            }
            allCount++ ;
        }

        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("allCount",allCount);
        if (impart !=null){
            ActionContext.getContext().put("impart",impart);
            return SUCCESS ;
        }

        return INPUT ;
    }

}
