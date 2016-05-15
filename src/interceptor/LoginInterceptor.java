package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

/**
 * Description: LoginInterceptor
 * Author: silence
 * Update: silence(2016-05-10 10:57)
 */
public class LoginInterceptor extends AbstractInterceptor {

    private String position ;
    private String status ;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map session = actionInvocation.getInvocationContext().getSession();

        String newStatus = (String) session.get("status") ;  //获取状态
        String newPositions = (String) session.get("position") ;  //获取职位

        if (newStatus!=null&&newPositions!=null&&status.equals(newStatus) && newPositions.endsWith(position)){
            return actionInvocation.invoke() ;
        }

        return "noPermission" ;
    }
}
