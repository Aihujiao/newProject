package ctrl.implement.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
