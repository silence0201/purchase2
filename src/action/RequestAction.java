package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Item;
import entity.Request;
import entity.User;
import service.IRequestService;
import service.impl.IRequestServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: RequestAction
 * Author: silence
 * Update: silence(2016-05-01 17:48)
 */
public class RequestAction extends ActionSupport {

    private IRequestService service ;

    private int requestID ;
    private Request request ;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public IRequestService getService() {
        return service;
    }

    public void setService(IRequestService service) {
        this.service = service;
    }

    //获取详细信息
    public String info(){
        return  SUCCESS ;
    }

    //添加申请单
    public String add(){
        return SUCCESS ;
    }

    //初始化修改页面
    public String initModify(){
        return SUCCESS ;
    }

    //修改申请单
    public String modify(){
        return SUCCESS ;
    }

    //初始化通知页面
    public String notice(){
        service = new IRequestServiceImpl() ;
        Map session = ActionContext.getContext().getSession() ;
        User user = (User) session.get("user");
        String requestID = user.getUserId() ;

        //获取取货通知信息
        HashMap<String,ArrayList<String>> map = service.getRequestNotices(requestID) ;
        ArrayList<Request> requests = service.getRequestList(requestID) ;

        ActionContext.getContext().put("notices",map.get("notices"));
        ActionContext.getContext().put("passes",map.get("passes"));
        ActionContext.getContext().put("refuses",map.get("refuses"));
        ActionContext.getContext().put("requests",requests);
        int count = map.get("notices").size() + map.get("passes").size()+
                map.get("refuses").size() ;
        ActionContext.getContext().getSession().put("count",count) ;

        return SUCCESS ;
    }

    //初始化列表页面
    public String list(){
        service = new IRequestServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        String requestManID = user.getUserId() ;

        ArrayList<Request> requests = service.getRequestList(requestManID) ;

        ActionContext.getContext().put("requests",requests);

        return SUCCESS ;
    }

    //初始化申请页面
    public String request(){
        service = new IRequestServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        String requestManID = user.getUserId() ;

        int countOfTime = service.countOfTime(requestManID) ;
        double countOfMoney = service.countOfMoney(requestManID) ;
        ArrayList<Item> items = service.items() ;

        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("countOfMoney",countOfMoney);
        ActionContext.getContext().put("items",items);

        return SUCCESS ;
    }
}
