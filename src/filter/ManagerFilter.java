package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Description: ${NAME}
 * Author: silence
 * Update: silence(2016-05-01 16:09)
 */
@WebFilter(filterName = "ManagerFilter",urlPatterns = "/boss/*")
public class ManagerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req ;
        HttpSession session = request.getSession() ;

        String status = (String) session.getAttribute("status") ;  //获取状态
        String position = (String) session.getAttribute("position") ;  //获取职位

        if (status != null && position !=null&status.equals("Login") && position.endsWith("总经理")){
            chain.doFilter(req,resp);
        }else{
            String path = request.getContextPath() ;
            HttpServletResponse response = (HttpServletResponse)resp ;
            response.sendRedirect(path+"/login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
