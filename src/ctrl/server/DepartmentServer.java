package ctrl.server;

import ctrl.dao.DepartmentDao;
import ctrl.factory.DepartmentFactory;
import model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/DepartmentServer")
public class DepartmentServer extends HttpServlet {
    private static String contextPath = null;
    private static String path = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = request.getContextPath();
        String op = request.getParameter("op");
        if(op.equals("departmentRegister")){
            this.doRegisterDepartment(request, response);
        } else if (op.equals(("getDepartmentById"))) {
            this.doGetDepartmentById(request, response);
        } else if (op.equals("getAllDepartments")) {
            this.doGetAllDepartments(request, response);
        } else if (op.equals("departmentDeleteById")) {
            this.doDeleteDepartmentById(request,response);
        } else if (op.equals("getDepartmentsByName")) {
            this.doGetDepartmentsByName(request,response);
        }

        request.getRequestDispatcher(path).forward(request,response);
    }

    private void doRegisterDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = null;
        String departmentName = request.getParameter("departmentName");
        String departmentIntro = request.getParameter("departmentIntro");

        department = new Department(0,departmentName,departmentIntro);

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();
        boolean registered = departmentCtrl.registDepartment(department);
        path = null;

        if(registered){
            path = contextPath + "/admin/operation.jsp?msg=succeed";
        }else{
            path = contextPath + "/admin/operation.jsp?msg=fail";
        }
    }

    private void doGetDepartmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  可能错
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        path = contextPath + "/getInfo?type=Department&departmentId="+departmentId;

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();

        Department department = departmentCtrl.getDepartmentById(departmentId);

        if(department != null){
            request.setAttribute("department",department);
            request.getRequestDispatcher(path).forward(request,response);
        }
    }

    private void doGetAllDepartments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();
        List<Department> departments = departmentCtrl.getAllDepartment();

        path = "/getInfo.jsp?type=departments";

        int departmentNum = departments.size();
        System.out.println(departmentNum);
        if(departmentNum == 0){
            path = "/getInfo.jsp?type=departments&msg=nothing";
        }

        request.setAttribute("departments",departments);
    }

    private void doDeleteDepartmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        System.out.println("部门删除方法里获取的"+departmentId);
        DepartmentDao departmenCtrl = DepartmentFactory.instance().getDepartmentCtrl();

        path = contextPath + "/admin/operation.jsp?msg=succeed";

        if(departmentId == 1){
            path = contextPath + "/admin/operation.jsp?msg=noway";
        }else{
            boolean deleted = departmenCtrl.deleteDepartmentById(departmentId);
            if(!deleted){
                path = contextPath + "/admin/operation.jsp?msg=fail";
            }
        }
    }

    private void doGetDepartmentsByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departmentList = new ArrayList<>();

        String departmentLikeName = request.getParameter("departmentLikeName");
        String searched = request.getParameter("searched");

        System.out.println("之前搜索的内容是:"+searched);

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();
        departmentList = departmentCtrl.getDepartmentsByLikeName(departmentLikeName);

        int departmentsNum = departmentList.size();

        path = "/getInfo.jsp?type=departments";

        if(departmentsNum > 0){
            request.setAttribute("searched",searched);
            request.setAttribute("departments",departmentList);
        }else{
            path = path + "msg=nothing";
        }
    }

}
