package ctrl.dao;

import model.Admin;
import model.Employee;

import java.util.List;

public interface AdminDao {
    public Admin loginAdmin(Admin admin);

    //  超级管理员特殊功能选项
    public boolean regisAdmin(Admin admin);

    public boolean updateAdmin(Admin admin);

    public boolean deleteAdminById(int adminId);

    public boolean getAdminById(int adminId);

    //  超级管理员功能选项
    public List<Admin> getAllAdmin();

    public boolean registEmployeeOnDepartment(Employee employee);

    public List<Employee> getEmployeesOnDepartment(int departmentId);

    public boolean updateEmployee(Employee employee);

    //  超级管理员功能选项
    public List<Employee> getAllEmployees();
}
