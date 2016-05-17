package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Request;
import entity.User;
import service.IDiVManagerService;
import service.impl.IDivManagerServiceImpl;

import java.util.ArrayList;
import java.util.Map;

/**
 * Description: DivManagerAction
 * Author: silence
 * Update: silence(2016-05-01 18:01)
 */
public class DivManagerAction extends ActionSupport {

    private IDiVManagerService service ;

    private Request request ;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public IDiVManagerService getService() {
        return service;
    }

    public void setService(IDiVManagerService service) {
        this.service = service;
    }

    //显示审核信息页面
    public String info(){
        service = new IDivManagerServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        Request request = service.getRequestInfo(Integer.parseInt(requestID)) ;
        ArrayList<Request> checkList = service.getCheckList(user.getUserId()) ;

        ActionContext.getContext().put("request",request);
        ActionContext.getContext().put("checkList",checkList);

        return SUCCESS ;
    }

    //处理审核
    public String handle(){
        service = new IDivManagerServiceImpl() ;
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
        service = new IDivManagerServiceImpl() ;
        User user = (User) ActionContext.getContext().getSession().get("user") ;

        String position = user.getPosition() ;
        String userID = user.getUserId() ;

        ArrayList<String> checks = service.getCheckNotice(position) ;
        ArrayList<Request> checkList = service.getCheckList(userID) ;
        ActionContext.getContext().put("checkNotices",checks);
        ActionContext.getContext().getSession().put("count",checks.size()) ;
        ActionContext.getContext().put("checkList",checkList);

        return SUCCESS ;
    }

    //显示需求申请信息
    public String query(){
        service = new IDivManagerServiceImpl() ;
        User user = (User)ActionContext.getContext().getSession().get("user") ;

        String positon = user.getPosition() ;
        int coutOfTime = service.getCountOfTime(user.getUserId()) ;
        double sumOfMoney = service.getSumOfMoney(user.getUserId()) ;

        ArrayList<Request> requests = service.getRequesList(positon) ;
        ActionContext.getContext().put("requests",requests);
        ActionContext.getContext().put("countOfTime",coutOfTime);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);


        return SUCCESS ;
    }

    //显示审核信息
    public String work(){
        service = new IDivManagerServiceImpl() ;

        User user = (User)ActionContext.getContext().getSession().get("user") ;

        String userID = user.getUserId() ;
        String position = user.getPosition() ;

        ArrayList<Request> checkList = service.getCheckList(userID) ;
        int allCheckCount = checkList.size() ;
        int checkCount = service.getCountOfCheck(userID) ;
        int checkOfDepart = service.getCountOfCheckPart(position) ;


        ActionContext.getContext().put("checkList",checkList);
        ActionContext.getContext().put("allCheckCount",allCheckCount);
        ActionContext.getContext().put("checkCount",checkCount);
        ActionContext.getContext().put("checkOfDepart",checkOfDepart);


        return SUCCESS ;
    }

    //显示具体详情
    public String moreInfo(){
        service = new IDivManagerServiceImpl() ;

        Map pragram = ActionContext.getContext().getParameters() ;
        String requestID = ((String[]) pragram.get("requestID"))[0];
        request = service.getRequestInfo(Integer.parseInt(requestID)) ;
        User user = (User)ActionContext.getContext().getSession().get("user") ;
        int coutOfTime = service.getCountOfTime(user.getUserId()) ;
        double sumOfMoney = service.getSumOfMoney(user.getUserId()) ;
        ActionContext.getContext().put("countOfTime",coutOfTime);
        ActionContext.getContext().put("sumOfMoney",sumOfMoney);
        if (request !=null){
            return SUCCESS ;
        }

        return INPUT ;
    }
}
