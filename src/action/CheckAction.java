package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.User;
import service.ICheckService;
import service.impl.ICheckServiceImpl;

import java.util.Map;

/**
 * Description: CheckAction
 * Author: silence
 * Update: silence(2016-05-01 17:44)
 */
public class CheckAction extends ActionSupport {

    private ICheckService service ;

    private String userID ;
    private String password ;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ICheckService getService() {
        return service;
    }

    public void setService(ICheckService service) {
        this.service = service;
    }

    //执行登陆方法
    public String login(){

        service = new ICheckServiceImpl() ;
        User user = service.loginCheck(userID,password) ;

        if (user != null){
            ActionContext ctx = ActionContext.getContext() ;
            //获取session
            Map session = ctx.getSession() ;
            session.put("status","Login") ;
            session.put("position",user.getPosition()) ;
            session.put("user",user) ;
            return SUCCESS ;
        }
        return "fail" ;
    }
    //跳转到指定的页面
    public String jump() {
        Map session = ActionContext.getContext().getSession() ;

        String status = (String) session.get("status");
        if (status == null){
            return "fail" ;
        }

        String position = (String)session.get("position") ;

        if (position.endsWith("部门经理")){
            return "divManager" ;
        }
        if (position.equals("总经理")){
            return "manager" ;
        }
        if (position.equals("采购员")){
            return "purchase" ;
        }
        if (position.equals("库管员")){
            return "stock" ;
        }
        if (position.endsWith("申请员")){
            return "request" ;
        }

        return "fail" ;
    }
    //执行注销方法
    public String logout(){
        Map session = ActionContext.getContext().getSession() ;

        session.remove("status") ;
        session.remove("position") ;
        if (session.get("status")!=null && session.get("position")!=null){
            return INPUT ;
        }
        return SUCCESS ;
    }


}
