package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Order;
import entity.Request;
import entity.User;
import service.IManagerService;
import service.impl.IManagerServiceImpl;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description: ManagerAction
 * Author: silence
 * Update: silence(2016-05-01 18:06)
 */
public class ManagerAction extends ActionSupport {

    private IManagerService service ;

    private Request request ;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public IManagerService getService() {
        return service;
    }

    public void setService(IManagerService service) {
        this.service = service;
    }

    //显示审核详细信息
    public String info(){
        service = new IManagerServiceImpl() ;

        User user = (User) ActionContext.getContext().getSession().get("user") ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;


        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];

        request = service.getRequestInfo(requestID) ;

        if (request == null){
            return INPUT ;
        }

        ActionContext.getContext().put("request",request);
        ActionContext.getContext().put("checkList",checkList);


        return SUCCESS ;
    }

    //处理审核信息
    public String handle(){

        service = new IManagerServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user");
        Map pragram = ActionContext.getContext().getParameters() ;

        String requestID = ((String[]) pragram.get("requestID"))[0];
        String status = ((String[]) pragram.get("status"))[0];
        String reason = ((String[]) pragram.get("reason"))[0];
        String userID = user.getUserId() ;

        boolean flag = service.handle(requestID,status,reason,userID) ;

        if (flag){
            return SUCCESS ;
        }
        return ERROR ;
    }

    //显示通知页面
    public String notice(){
        service = new IManagerServiceImpl() ;

        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<String> checkNotices = service.getCheckNotices() ;
        int count = checkNotices.size() ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;

        ActionContext.getContext().put("checkNotices",checkNotices);
        ActionContext.getContext().getSession().put("count",count) ;
        ActionContext.getContext().put("checkList",checkList);

        return SUCCESS ;
    }

    //显示需求申请信息
    public String query(){
        service = new IManagerServiceImpl() ;

        ArrayList<Request> requests = service.getRequestList() ;
        int allCount = requests.size() ;
        int count = service.getCountOfRequest() ;
        double sum = service.getSumOfRequest() ;

        ActionContext.getContext().put("requests",requests);
        ActionContext.getContext().put("allCount",allCount);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("sum",sum);

        return SUCCESS ;
    }

    //显示审核信息
    public String work(){
        service = new IManagerServiceImpl() ;

        User user = (User) ActionContext.getContext().getSession().get("user") ;

        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;
        int allCount = checkList.size() ;
        int count = service.getCountOfCheck(user.getUserId()) ;

        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("allCount",allCount);
        ActionContext.getContext().put("count",count);

        return SUCCESS ;
    }

    //显示订单列表页面
    public String orders(){
        service = new IManagerServiceImpl() ;

        ArrayList<Order> orders = service.getOrders() ;
        int allCount = orders.size() ;
        int count = service.getCountOfOrder() ;
        double sum = service.getSumOfOrder() ;

        ActionContext.getContext().put("orders",orders);
        ActionContext.getContext().put("allCount",allCount);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("sum",sum);

        return SUCCESS ;
    }

    //显示申请单的详细信息
    public String moreRequestInfo(){
        service = new IManagerServiceImpl() ;

        int allCount = service.getRequestList().size() ;
        int count = service.getCountOfRequest() ;
        double sum = service.getSumOfRequest() ;
        Map pragram = ActionContext.getContext().getParameters() ;

        String requestID = ((String[]) pragram.get("requestID"))[0];
        request = service.getRequestInfo(requestID) ;

        ActionContext.getContext().put("allCount",allCount);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("sum",sum);


        return SUCCESS ;
    }

    //显示订单的详细信息
    public String moreOrderInfo(){
        service = new IManagerServiceImpl() ;

        ArrayList<Order> orders = service.getOrders() ;
        int allCount = orders.size() ;
        int count = service.getCountOfOrder() ;
        double sum = service.getSumOfOrder() ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String orderID = ((String[]) pragram.get("orderID"))[0];
        Order order = null ;
        for (Order arder: orders){
            if (arder.getOrderId().equals(new Integer(orderID))){
                order = arder ;
            }
        }

        ActionContext.getContext().put("allCount",allCount);
        ActionContext.getContext().put("count",count);
        ActionContext.getContext().put("sum",sum);
        ActionContext.getContext().put("arder",order);


        return SUCCESS ;
    }

}
