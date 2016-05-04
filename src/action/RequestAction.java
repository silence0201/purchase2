package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.Request;
import service.IRequestService;

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
}
