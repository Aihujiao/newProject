package ctrl.server;

import ctrl.implement.dao.EmployeeDao;
import ctrl.factory.EmployeeFactory;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/EmployeeServer")
public class EmployeeServer extends HttpServlet {
    String contextPath = null;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contextPath = request.getContextPath();
        String op = request.getParameter("op");
        if(op.equals("employeeLogin")){
            this.doLoginEmployee(request,response);
        } else if (op.equals("employeeUpdate")) {
            this.doUpdateEmployee(request,response);
        } else if (op.equals("getAllEmployees")) {
            this.doGetAllEmployees(request, response);
        } else if (op.equals("employeeDeleteById")) {
            this.doDeleteEmployeeById(request, response);
        } else if (op.equals("employeeRegister")) {
            this.doRegisterEmployee(request, response);
        }
    }

    private void doLoginEmployee (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String EmployeeName = request.getParameter("employeeName");
        String EmployeePassword = request.getParameter("employeePassword");
        System.out.println("管理员昵称是"+EmployeeName+"管理员密码是"+EmployeePassword);
        Employee employee = new Employee(0,EmployeeName,EmployeePassword,0,0,null,0,0,null,0);

        EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeDao();
        employee = employeeCtrl.loginEmployee(employee);

        String path = contextPath + "/employee/operation.jsp";

        if(employee == null){
            path = contextPath + "/employee/login.jsp?msg=fail";
        }else {
            request.getSession().setAttribute("employee",employee);
        }

        response.sendRedirect(path);
    }
    
    private void doUpdateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Employee newEmployee = null;
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");
        int newGender = Integer.parseInt(request.getParameter("newGender"));
        int newAge = Integer.parseInt(request.getParameter("newAge"));
        String newProfile = request.getParameter("newProfile");
        int newEmployeeDepartmentId = Integer.getInteger(request.getParameter("newEmployeeDepartmentId"));
        int newEmployeePowerId = Integer.parseInt(request.getParameter("newEmployeePowerId"));
        String newEmployeePosition = request.getParameter("newEmployeePosition");
        int newEmployeeStation = Integer.getInteger(request.getParameter("newEmployeeStation"));

        newEmployee = new Employee(employeeId,newName,newPassword,newGender,newAge,newProfile,newEmployeeDepartmentId,newEmployeePowerId,newEmployeePosition,newEmployeeStation);

        EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeDao();
        employeeCtrl.updateEmployee(newEmployee);

        String path = contextPath + "/employee/operation.jsp?msg=succeed";
        if(newEmployee != null){
            request.getSession().setAttribute("employee",newEmployee);
        }else{
            path = contextPath + "/employee/operation.jsp?msg=err";
        }

        response.sendRedirect(path);
    }

    //  管理员操作
    private void doGetAllEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeDao();
        //  创建数组集合用于存储 员工数据
        List<Employee> employees = new ArrayList<>();
        //  将员工集合赋值给 employees
        employees = employeeCtrl.getAllEmployees();
        //  判断集合中元素个数
        int employeeNum = employees.size();

        System.out.println("所有员工人数为"+employeeNum);

        String path = null;
        if (employeeNum != 0){
            path = "/getInfo.jsp?type=employees";
            request.setAttribute("employees",employees);
        }else{
            path = "/getInfo.jsp?msg=nothing";
        }

        //  带参跳转
        request.getRequestDispatcher(path).forward(request,response);
    }

    //  员工操作
    private void doGetSameDepartmentEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

    }
    private void doDeleteEmployeeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        System.out.println(employeeId);
        EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeDao();
        boolean deleted = employeeCtrl.deleteEmployeeById(employeeId);

        String path = contextPath + "/employee/operation.jsp?msg=succeed";

        if(!deleted){
            path = contextPath + "/employee/operation.jsp?msg=fail";
        }

        //  直接使用URL传参到新页面使页面进行判断
        response.sendRedirect(path);
    }

    private void doRegisterEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Employee employee = new Employee();
        String employeeName = request.getParameter("employeeName");
        String employeePassword = request.getParameter("employeePassword");
        int employeeGender = Integer.parseInt(request.getParameter("employeeGender"));
        int employeeAge = Integer.parseInt(request.getParameter("employeeAge"));
        String employeeProfile = request.getParameter("employeeProfile");
        int employeeDepartmentId =Integer.parseInt(request.getParameter("employeeDepartmentId"));
        String employeePosition = request.getParameter("employeePosition");
        int employeeStation = Integer.parseInt(request.getParameter("employeeStation"));

        employee = new Employee(0,employeeName,employeePassword,employeeGender,employeeAge,employeeProfile,employeeDepartmentId,0,employeePosition,employeeStation);

        EmployeeDao employeeCtrl = EmployeeFactory.instance().getEmployeeDao();
        boolean registered = employeeCtrl.registerEmployee(employee);

        String path = "/admin/operation.jsp?msg=succeed";
        if (registered){
            request.setAttribute("employee",employee);
        }else {
            path = "/admin/operation.jsp?msg=fail";
        }

        request.getRequestDispatcher(path).forward(request,response);
    }
}
