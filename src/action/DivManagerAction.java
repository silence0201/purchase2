package action;

import com.opensymphony.xwork2.ActionSupport;
import service.IDiVManagerService;

/**
 * Description: DivManagerAction
 * Author: silence
 * Update: silence(2016-05-01 18:01)
 */
public class DivManagerAction extends ActionSupport {

    private IDiVManagerService service ;

    private int requestID ;
    private String status ;

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public IDiVManagerService getService() {
        return service;
    }

    public void setService(IDiVManagerService service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //显示审核信息页面
    public String info(){
        return SUCCESS ;
    }

    //处理审核
    public String handle(){
        return SUCCESS ;
    }
}
