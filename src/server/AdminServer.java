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
            this.doLoginAdmin(request, response);
        }else if(op.equals("adminUpdate")){
            this.doUpdateAdmin(request, response);
        } else if (op.equals("adminRegister")) {
            this.doRegisterAdmin(request, response);
        }
    }

    private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String AdminNickName = request.getParameter("adminNickName");
        String AdminPassword = request.getParameter("adminPassword");
        System.out.println("管理员昵称是"+AdminNickName+"管理员密码是"+AdminPassword);
        Admin admin = new Admin(0,AdminNickName,AdminPassword,null,0,0);
        //  通过工厂实例化接口
        //  通过方法创建AdminCtrl实例
        System.out.println(admin.getAdminNickName());
        AdminDao AdminCtrl = AdminFactory.instance().getAdminDao();
        //  将前端获取的管理员对象值传入数据库检索
        //  无法执行
        admin = AdminCtrl.loginAdmin(admin);

        String path = request.getContextPath()+"/admin/info.jsp";
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
        }else{
            path = request.getContextPath()+"/admin/login.jsp";
        }

        response.sendRedirect(path);
    }

    private void doUpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin newAdmin = null;
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        String newNickName = request.getParameter("newNickName");
        String newPassword = request.getParameter("newPassword");
        String newProfile = request.getParameter("newProfile");
        int newDepartmentId = Integer.parseInt(request.getParameter("newDepartmentId"));
        int adminStation = Integer.parseInt(request.getParameter("adminStation"));

        newAdmin = new Admin(adminId,newNickName,newPassword,newProfile,newDepartmentId,adminStation);

        AdminDao AdminCtrl = AdminFactory.instance().getAdminDao();
        AdminCtrl.updateAdmin(newAdmin);



        String path = request.getContextPath()+"/admin/info.jsp?msg=succeed";
        if(newAdmin != null){
            request.getSession().setAttribute("admin",newAdmin);
        }else{
            path = request.getContextPath() + "/admin/info.jsp?msg=err";
        }


        response.sendRedirect(path);
    }

    private boolean doRegisterAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = null;

        //  获取前端信息
        String AdminNickName = request.getParameter("adminNickName");
        String AdminPassword = request.getParameter("adminPassword");
        String AdminProfile = request.getParameter("adminProfile");
        int AdminDepartmentId = Integer.parseInt(request.getParameter("adminDepartmentId"));

        admin = new Admin(0,AdminNickName,AdminPassword,AdminProfile,AdminDepartmentId,0);

        AdminDao AdminCtrl = AdminFactory.instance().getAdminDao();

        boolean Registed = AdminCtrl.registAdmin(admin);

        String path = request.getContextPath()+"/admin/info.jsp?msg=registSucceed";

        if(!Registed){
            path = request.getContextPath()+"/admin/info.jsp?msg=registFalse";
        }

        response.sendRedirect(path);

        return Registed;
    }
}
