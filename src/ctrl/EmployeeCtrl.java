package ctrl;

import ctrl.dao.EmployeeDao;
import db.ExecuteDB;
import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCtrl extends ExecuteDB implements EmployeeDao {
    //  为部门添加员工
    private boolean addEmployee(Employee employee){
        String sql = "insert into employees value(null,?,?,?,?,?,?,?,?)";
        String employeeName = employee.getEmployeeName();
        String employeePassword =employee.getEmployeePassword();
        int employeeGender = employee.getEmployeeGender();
        int employeeAge = employee.getEmployeeAge();
        String employeeProfile = employee.getEmployeeProfile();
        int employeeDepartmentId = employee.getDepartmentId();
        String employeePosition = employee.getEmployeePosition();
        int employeeStation = employee.getEmployeeStation();

        Object objects[] = {employeeName,employeePassword,employeeGender,employeeAge,employeeProfile,employeeDepartmentId,employeePosition,employeeStation};
        boolean added = executeDBUpdate(sql, objects);

        return added;
    }

    //  更新员工信息
    private boolean updateEmployee(Employee employee){
        String sql = "update employees set employeeName = ?,employeePassword = ?,employeeGender = ?,employeeAge = ?,employeeProfile = ?,employeeDepartmentId = ?,employeePosition = ?,employeeStation = ? where employeeId = ?";
        String employeeName = employee.getEmployeeName();
        String employeePassword =employee.getEmployeePassword();
        int employeeGender = employee.getEmployeeGender();
        int employeeAge = employee.getEmployeeAge();
        String employeeProfile = employee.getEmployeeProfile();
        int employeeDepartmentId = employee.getDepartmentId();
        String employeePosition = employee.getEmployeePosition();
        int employeeStation = employee.getEmployeeStation();
        int employeeId = employee.getEmployeeId();

        Object objects[] ={employeeName,employeePassword,employeeGender,employeeAge,employeeProfile,employeeDepartmentId,employeePosition,employeeStation,employeeId};
        boolean updated = executeDBUpdate(sql, objects);

        return updated;
    }

    //  查询某位员工信息
    private Employee getEmployeeInfor(int employeeId){
        Employee employee = null;
        String sql = "select * from employees where employeeId = ?";
        Object objects[] = {employeeId};

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if(rs.next()){
                String employeeName = rs.getString("employeeName");
                String employeePassword = rs.getString("employeePassword");
                int employeeGender = rs.getInt("employeeGender");
                int employeeAge = rs.getInt("employeeAge");
                String employeeProfile = rs.getString("employeeProfile");
                int employeeDepartmentId = rs.getInt("employeeDepartmentId");
                String employeePosition = rs.getString("employeePosition");
                int employeeStation = rs.getInt("employeeStation");
                employee = new Employee(employeeId,employeeName,employeePassword,employeeGender,employeeAge,employeeProfile,employeeDepartmentId,employeePosition,employeeStation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employee;
    }
}
