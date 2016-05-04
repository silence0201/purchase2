package action;

import com.opensymphony.xwork2.ActionSupport;
import service.IManagerService;

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
}
