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
import java.util.List;

@WebServlet(value = "/DepartmentServer")
public class DepartmentServer extends HttpServlet {
    private static String contantPath = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contantPath = request.getContextPath();
        String op = request.getParameter("op");
        if(op.equals("departmentRegister")){
            this.doRegisterDepartment(request, response);
        } else if (op.equals(("getDepartmentById"))) {
            this.doGetDepartmentById(request, response);
        } else if (op.equals("getAllDepartments")) {
            this.doGetAllDepartments(request, response);
        } else if (op.equals("departmentDeleteById")) {
            this.doDeleteDepartmentById(request,response);
        }
    }

    private void doRegisterDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = null;
        String departmentName = request.getParameter("departmentName");
        String departmentIntro = request.getParameter("departmentIntro");

        department = new Department(0,departmentName,departmentIntro);

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentCtrl();
        boolean registered = departmentCtrl.registDepartment(department);
        String path = null;

        if(registered){
            path = contantPath + "/admin/operation.jsp?msg=succeed";
        }else{
            path = contantPath + "/admin/operation.jsp?msg=fail";
        }
        response.sendRedirect(path);
    }

    private void doGetDepartmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  可能错
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String path = contantPath + "/getInfo?type=Department&departmentId="+departmentId;

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

        String path = "/getInfo.jsp?type=departments";

        int departmentNum = departments.size();
        System.out.println(departmentNum);
        if(departmentNum == 0){
            path = "/getInfo.jsp?type=departments&msg=nothing";
        }

        request.setAttribute("departments",departments);
        //  不完全跳转，跳转后并不会将URL地址修改
        request.getRequestDispatcher(path).forward(request,response);
    }

    private void doDeleteDepartmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));


        System.out.println("部门删除方法里获取的"+departmentId);
        DepartmentDao departmenCtrl = DepartmentFactory.instance().getDepartmentCtrl();

        String path = contantPath + "/admin/operation.jsp?msg=succeed";

        if(departmentId == 1){
            path = contantPath + "/admin/operation.jsp?msg=noway";
        }else{
            boolean deleted = departmenCtrl.deleteDepartmentById(departmentId);
            if(!deleted){
                path = contantPath + "/admin/operation.jsp?msg=fail";
            }
        }

        //  不需要再进行 Attribute 进行servlet传递时，就可以直接使用 sendRedirect 直接跳转页面
        response.sendRedirect(path);
    }
}