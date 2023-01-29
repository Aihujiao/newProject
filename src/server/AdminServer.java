package server;

import ctrl.dao.AdminDao;
import factory.AdminFactory;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jspWeb", value = "/AdminServer")
public class AdminServer extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String op = request.getParameter("op");
        System.out.println("此时op要操作的方法是" + op);
        if(op.equals("adminLogin")){
            System.out.println("执行到这里");
            this.doLoginAdmin(request, response);
        }else{

        }
    }

    private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String AdminNickName = request.getParameter("adminNickName");
        String AdminPassword = request.getParameter("adminPassword");
        System.out.println("管理员昵称是"+AdminNickName+"管理员密码是"+AdminPassword);
        System.out.println("执行到这里-37");
        Admin admin = new Admin(0,AdminNickName,AdminPassword,null,0,0);
        System.out.println("执行到这里-39");
        //  通过工厂实例化接口
        //  通过方法创建AdminCtrl实例
        System.out.println(admin.getAdminNickName());
        AdminDao AdminCtrl = AdminFactory.instance().getAdminDao();
        //  将前端获取的管理员对象值传入数据库检索
        //  无法执行
        System.out.println("执行到这里-45");
        admin = AdminCtrl.loginAdmin(admin);

        String path = request.getContextPath()+"/admin/info.jsp";
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
        }else{
            path = request.getContextPath()+"/admin/login.jsp";
        }

        response.sendRedirect(path);
    }
}
