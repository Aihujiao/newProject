package ctrl.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(value = "/*")
public class CodeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //  以 response 方式进行跳转时,设置request对象的数据格式为 UTF-8
        servletResponse.setCharacterEncoding("UTF-8");
        //  以 request 方式进行跳转时,设置request对象的数据格式为 UTF-8
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        //  可以在servlet执行前执行信息格式转码

        boolean flag = true;

        //  不会过滤的页面
        String[] suffixs = {"/Login","/AdminServer","/DepartmentServer","/EmployeeServer","/PowerServer","/ProfileUpdateServer","/StationServer",".js",".css",".ico",".jpg",".png"};

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String urlName = httpServletRequest.getServletPath();

        System.out.println("获得的网页名有 = " + urlName);

        for (String suffix: suffixs)
        {
            //  urlName.indexOf(suffix) != -1
            if(urlName.contains(suffix)){
                flag = false;
                break;
            }
        }

        //  如果  flag 为真,这个页面将不再执行将不会执行过滤
        if(flag){
            //  为之后登录的过滤预留代码空间
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
