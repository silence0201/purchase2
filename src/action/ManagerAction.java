package action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Order;
import entity.Request;
import entity.User;
import service.IManagerService;
import service.impl.IManagerServiceImpl;

import java.util.ArrayList;

/**
 * Description: ManagerAction
 * Author: silence
 * Update: silence(2016-05-01 18:06)
 */
public class ManagerAction extends ActionSupport {

    private IManagerService service ;

    private int reuqestID ;
    private String status ;

    //显示审核详细信息
    public String info(){
        return SUCCESS ;
    }

    //处理审核信息
    public String handle(){
        return SUCCESS ;
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

}
