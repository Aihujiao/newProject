package server;

import ctrl.dao.DepartmentDao;
import factory.DepartmentFactory;
import model.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "jspWeb",value = "DepartmentServer")
public class DepartmentServer extends HttpServlet {
    private static String contextPath = null;
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
        } else if (op.equals("DeleteDepartmentById")) {
            
        }
    }

    private void doRegisterDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = null;
        String departmentName = request.getParameter("departmentName");
        String departmentIntro = request.getParameter("departmentIntro");

        department = new Department(0,departmentName,departmentIntro);

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentDaoDao();
        boolean registered = departmentCtrl.registDepartment(department);
        String path = null;
        if(registered){
            path = contextPath+"/admin/operation.jsp?msg=succeed";
        }else{
            path = contextPath+"/admin/operation.jsp?msg=fail";
        }
        response.sendRedirect(path);
    }

    private void doGetDepartmentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        String path = contextPath+"/getInfo?type=Department&departmentId="+departmentId;

        DepartmentDao departmentCtrl = DepartmentFactory.instance().getDepartmentDaoDao();

        Department department = departmentCtrl.getDepartmentById(departmentId);

        if(department != null){
            request.setAttribute("department",department);
            request.getRequestDispatcher(path).forward(request,response);
        }
    }
}
