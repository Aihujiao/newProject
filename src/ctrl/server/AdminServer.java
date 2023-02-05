package ctrl.server;

import ctrl.dao.AdminDao;
import ctrl.factory.AdminFactory;
import model.Admin;
import model.Power;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/AdminServer")
//  为什么改了 jspWeb 就无法访问
public class AdminServer extends HttpServlet {
    private static String contextPath = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = request.getContextPath();
        String op = request.getParameter("op");
        System.out.println("此时op要操作的方法是" + op);
        if(op.equals("adminLogin")){
            this.doLoginAdmin(request, response);
        }else if(op.equals("adminUpdate")){
            this.doUpdateAdmin(request, response);
        } else if (op.equals("adminRegister")) {
            this.doRegisterAdmin(request, response);
        } else if (op.equals("adminDeleteById")) {
            this.doDeleteAdminById(request, response);
        } else if (op.equals("getAllAdmins")) {
            this.doGetAllAdmins(request, response);
        } else if (op.equals("getAllAdminPowers")) {
//            this.
        }
    }

    private void doLoginAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String AdminNickName = request.getParameter("adminNickName");
        String AdminPassword = request.getParameter("adminPassword");
        System.out.println("管理员昵称是"+AdminNickName+"管理员密码是"+AdminPassword);
        Admin admin = new Admin(0,AdminNickName,AdminPassword,null,0,0,5);
        //  通过工厂实例化接口
        //  通过方法创建adminCtrl实例
        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        admin = adminCtrl.loginAdmin(admin);
        //  将前端获取的管理员对象值传入数据库检索

        String path = contextPath + "/admin/operation.jsp";
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
        }else{
            path = contextPath + "/adminLogin.jsp?msg=nothing";
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
        int adminStationId = Integer.parseInt(request.getParameter("adminStation"));
        int adminPowerId = Integer.parseInt(request.getParameter("adminPowerId"));

        newAdmin = new Admin(adminId,newNickName,newPassword,newProfile,newDepartmentId,adminStationId,adminPowerId);

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        adminCtrl.updateAdmin(newAdmin);



        String path = contextPath + "/admin/operation.jsp?msg=succeed";
        if(newAdmin != null){
            request.getSession().setAttribute("admin",newAdmin);
        }else{
            path = contextPath + "/admin/operation.jsp?msg=err";
        }

        response.sendRedirect(path);
    }

    private void doRegisterAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = null;

        //  获取前端信息
        String AdminNickName = request.getParameter("adminNickName");
        String AdminPassword = request.getParameter("adminPassword");
        String AdminProfile = request.getParameter("adminProfile");
        int AdminDepartmentId = Integer.parseInt(request.getParameter("adminDepartmentId"));

        //  管理员的权限默认为5普通权限
        admin = new Admin(0,AdminNickName,AdminPassword,AdminProfile,AdminDepartmentId,0,5);

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();

        boolean Exist = adminCtrl.hadAdmin(AdminNickName);
        boolean Registered = false;
        String path = null;

        if(Exist){
            Exist = true;
        }else{
            Registered = adminCtrl.registerAdmin(admin);

            path = contextPath + "/admin/operation.jsp?msg=registerSucceed&exist=false";
        }

        if(!Registered){
            path = contextPath + "/admin/operation.jsp?msg=registerFalse&exist="+Exist;
        }

        response.sendRedirect(path);
    }

    private void doDeleteAdminById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  获取 session 全局变量

        int adminId = Integer.parseInt(request.getParameter("adminId"));

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();

        String path = contextPath + "/adminLogin.jsp?msg=succeed";

        if(adminId == 1){
            path = contextPath + "/adminLogin.jsp?msg=noway";
        }else{
            boolean deleted = adminCtrl.deleteAdminById(adminId);
            if(!deleted){
                path = contextPath + "/adminLogin.jsp?msg=fail";
            }
        }
        response.sendRedirect(path);
    }

    private void doGetAllAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> adminList = null;

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        adminList = adminCtrl.getAllAdmins();

        String path = "/getInfo.jsp?type=admins";

        int adminNum = adminList.size();

        if(adminNum == 0){
            //  如果查到的管理员数量为0，就执行这内容
            path = "/getInfo.jsp?type=admins&msg=nothing";
        }

        request.setAttribute("admins",adminList);

        //  带着 request 和 response 参数值进行页面跳转
        //  response.sendRedirect 需要带项目名，而 request.getRequestDispatcher 则不用
        request.getRequestDispatcher(path).forward(request,response);
    }

    private void doGetAllAdminPower(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Power> powerList = null;

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        powerList = adminCtrl.getAllAdminPowers();

        String path = contextPath + "/getInfo?type=powers";

        int adminNum = powerList.size();

        if(adminNum == 0){
            //  如果查到的管理员数量为0，就执行这内容
            path = "/getInfo.jsp?type=admins&msg=nothing";
        }

        request.setAttribute("admins",powerList);

        request.getRequestDispatcher(path).forward(request,response);
    }
}