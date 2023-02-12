package ctrl.server;

import ctrl.dao.*;
import ctrl.factory.AdminFactory;
import ctrl.factory.DepartmentFactory;
import model.*;

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
    private static String path = null;
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
        } else if (op.equals("toAdminUpdate")) {
            this.toAdminUpdate(request,response);
        } else if (op.equals("adminRegister")) {
            this.doRegisterAdmin(request, response);
        } else if (op.equals("adminDeleteById")) {
            this.doDeleteAdminById(request, response);
        } else if (op.equals("getAllAdmins")) {
            this.doGetAllAdmins(request, response);
        } else if (op.equals("getAdminsByName")) {
            this.doGetAdminsByName(request,response);
        } else if (op.equals("toEmployeeRegister")) {
            this.toEmployeeRegister(request,response);
        }

        request.getRequestDispatcher(path).forward(request,response);
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

        path = "/admin/operation.jsp";
        if(admin != null){
            request.getSession().setAttribute("admin",admin);
        }else{
            path = "/adminLogin.jsp?msg=nothing";
        }

    }

    private void toAdminUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = null;
        List<Station> stations = null;
        List<Power> powers = null;

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();
        StationDao stationCtrl = AdminFactory.instance().getStationCtrl();
        PowerDao powerCtrl = AdminFactory.instance().getPowerCtrl();

        departments = departmentCtrl.getAllDepartmentOptions();
        stations = stationCtrl.getAllStationOptions();
        powers = powerCtrl.getAllPowerOptions();

        request.setAttribute("departments",departments);
        request.setAttribute("stations",stations);
        request.setAttribute("powers",powers);

        path ="/admin/update.jsp";
    }

    private void doUpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin newAdmin = null;
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        String newNickName = request.getParameter("newNickName");
        String newPassword = request.getParameter("newPassword");
        String newProfile = request.getParameter("newProfile");
        int newDepartmentId = Integer.parseInt(request.getParameter("newDepartmentId"));
        int newStationId = Integer.parseInt(request.getParameter("newStationId"));
        int newPowerId = Integer.parseInt(request.getParameter("newPowerId"));

        System.out.println("新的各项数据为"+newNickName+" , "+newPassword+" , "+newProfile+" , "+newDepartmentId+" , "+newStationId+" , "+newPowerId);
        newAdmin = new Admin(adminId,newNickName,newPassword,newProfile,newDepartmentId,newStationId,newPowerId);

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        adminCtrl.updateAdmin(newAdmin);

        path = "/admin/operation.jsp?msg=succeed";
        if(newAdmin != null){
            request.getSession().setAttribute("admin",newAdmin);
        }else{
            path = "/admin/operation.jsp?msg=err";
        }

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
        path = null;

        if(Exist){
            Exist = true;
        }else{
            Registered = adminCtrl.registerAdmin(admin);

            path = contextPath + "/admin/operation.jsp?msg=registerSucceed&exist=false";
        }

        if(!Registered){
            path = contextPath + "/admin/operation.jsp?msg=registerFalse&exist="+Exist;
        }

    }

    private void doDeleteAdminById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  获取 session 全局变量

        int adminId = Integer.parseInt(request.getParameter("adminId"));

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();

        path = contextPath + "/adminLogin.jsp?msg=succeed";

        if(adminId == 1){
            path = contextPath + "/adminLogin.jsp?msg=noway";
        }else{
            boolean deleted = adminCtrl.deleteAdminById(adminId);
            if(!deleted){
                path = contextPath + "/adminLogin.jsp?msg=fail";
            }
        }
    }

    private void doGetAllAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> adminList = null;

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        adminList = adminCtrl.getAllAdmins();

        path = "/getInfo.jsp?type=admins";

        int adminNum = adminList.size();

        if(adminNum == 0){
            //  如果查到的管理员数量为0，就执行这内容
            path = "/getInfo.jsp?type=admins&msg=nothing";
        }

        request.setAttribute("admins",adminList);
    }

    private void doGetAdminsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> adminList = null;
        String adminLikeName = request.getParameter("adminLikeName");

        AdminDao adminCtrl = AdminFactory.instance().getAdminCtrl();
        adminList = adminCtrl.getAllLikeAdminsName(adminLikeName);
        int adminsNum = adminList.size();

        path = contextPath + "/getInfo?type=admins";

        if(adminsNum > 0){
            request.setAttribute("admins",adminList);
        }else{
            path = path + "msg=nothing";
        }
    }

    private void toEmployeeRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments = null;
        List<Position> positions = null;
        List<Station> stations = null;

        AdminFactory ADF = AdminFactory.instance();
        DepartmentDao departmentCtrl = ADF.getDepartmentCtrl();
        PositionDao positionCtrl = ADF.getPositionCtrl();
        StationDao StationCtrl = ADF.getStationCtrl();

        departments = departmentCtrl.getAllDepartmentOptions();
        positions = positionCtrl.getAllPositionOptions();
        stations = StationCtrl.getAllStationOptions();

        request.setAttribute("departments",departments);
        request.setAttribute("positions",positions);
        request.setAttribute("stations",stations);

        path = "/employee/employeeRegister.jsp";
    }
}
