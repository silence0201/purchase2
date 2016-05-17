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

    private Request request ;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public IRequestService getService() {
        return service;
    }

    public void setService(IRequestService service) {
        this.service = service;
    }

    //获取详细信息
    public String info(){
        service = new IRequestServiceImpl() ;
        Map session = ActionContext.getContext().getSession() ;
        User user = (User) session.get("user");
        ArrayList<Request> requests = service.getRequestList(user.getUserId()) ;
        ActionContext.getContext().put("requests",requests);

        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];


        if (requestID == null){
            return INPUT ;
        }
        request = service.getRequestInfo(requestID) ;
        return  SUCCESS ;
    }

    //添加申请单
    public String add(){
        service = new IRequestServiceImpl() ;
        Map session = ActionContext.getContext().getSession() ;
        User user = (User) session.get("user");
        int countOfTime = service.countOfTime(user.getUserId()) ;
        double countOfMoney = service.countOfMoney(user.getUserId()) ;
        ActionContext.getContext().put("countOfTime",countOfTime);
        ActionContext.getContext().put("countOfMoney",countOfMoney);

        Map pragram = ActionContext.getContext().getParameters() ;
        String itemID = ((String[]) pragram.get("item"))[0];
        String number = ((String[]) pragram.get("number"))[0] ;
        String reason = ((String[]) pragram.get("reason"))[0] ;
        String userID = user.getUserId() ;

        Integer requestID = service.addRequest(userID,number,itemID,reason) ;
        if (requestID.equals(0)){
            return ERROR ;
        }
        request = service.getRequestInfo(requestID.toString()) ;
        return SUCCESS ;
    }

    //初始化修改页面
    public String initModify(){
        service = new IRequestServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;
        ArrayList<Request> requests = service.getRequestList(user.getUserId()) ;
        ActionContext.getContext().put("requests",requests);

        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        ActionContext.getContext().put("requestID",requestID);


        if (requestID == null){
            return INPUT ;
        }
        request = service.getRequestInfo(requestID) ;
        ActionContext.getContext().put("number",request.getNumber());
        return  SUCCESS ;
    }

    //修改申请单
    public String modify(){
        service = new IRequestServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        String number = ((String[]) pragram.get("number"))[0] ;

        if (service.modifyRequest(requestID,number)){
            return SUCCESS ;
        }else{
            return ERROR ;
        }
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

    //删除申请
    public String del(){
        service = new IRequestServiceImpl() ;
        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];

        boolean flag = service.delRequest(requestID) ;
        if (flag){
            return SUCCESS ;
        }else{
            return ERROR ;
        }
    }
}
