package action;

import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import service.ICheckService;

/**
 * Description: CheckAction
 * Author: silence
 * Update: silence(2016-05-01 17:44)
 */
public class CheckAction extends ActionSupport {

    private ICheckService service ;

    private String userID ;
    private String password ;
    private User user ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ICheckService getService() {
        return service;
    }

    public void setService(ICheckService service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    //执行登陆方法
    public String login(){
        return SUCCESS ;
    }

    //执行注销方法
    public String logout(){
        return SUCCESS ;
    }
}
