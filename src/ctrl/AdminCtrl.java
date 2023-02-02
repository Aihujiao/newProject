package ctrl;

import ctrl.dao.AdminDao;
import db.ExecuteDB;
import model.Admin;
import model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCtrl extends ExecuteDB implements AdminDao {
    //  登录方法
    public Admin loginAdmin(Admin admin){
        //  读取前端登录页面的信息
        System.out.println("进入管理员登录页面");
        String adminNickName = admin.getAdminNickName();
        String adminPassword = admin.getAdminPassword();

        String sql = "select * from admins where adminNickName = ? and adminPassword = ?";
        Object objects[]={adminNickName,adminPassword};

        ResultSet rs = executeDBQuery(sql,objects);

        try {
            if(rs.next()){
                //  将数据库的数据赋值到对象上
                int adminId = rs.getInt("adminId");
                String adminProfile = rs.getString("adminProfile");
                int adminDepartmentId = rs.getInt("adminDepartmentId");
                int adminStation = rs.getInt("adminStation");
                admin = new Admin(adminId, adminNickName, adminPassword, adminProfile, adminDepartmentId, adminStation);
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }
        return admin;
    }

    @Override
    public boolean registAdmin(Admin admin) {
        //  超級管理員
        //  读取前端登录页面的信息
        String adminNickName = admin.getAdminNickName();
        String adminPassword = admin.getAdminPassword();
        String adminProfile = admin.getAdminProfile();
        int adminDepartmentId = admin.getAdminDepartmentId();
        int adminStation = admin.getAdminStation();

        String sql = "insert into admins value (null,?,?,?,?,?)";
        Object[] objects = {adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStation};

        boolean registed = executeDBUpdate(sql, objects);

        return registed;
    }


    //  修改管理员信息
    public boolean updateAdmin(Admin admin){
        String adminNickName = admin.getAdminNickName();
        String adminPassword = admin.getAdminPassword();
        String adminProfile = admin.getAdminProfile();
        int adminDepartment = admin.getAdminDepartmentId();
        int adminStation = admin.getAdminStation();
        int adminId = admin.getAdminId();

        String sql ="update admins set adminNickName = ?,adminPassword = ?,adminProfile = ?,adminDepartmentId = ?,adminStation = ? where adminId = ?";
        Object objects[] = {adminNickName,adminPassword,adminProfile,adminDepartment,adminStation,adminId};

        boolean updated = executeDBUpdate(sql,objects);

        return updated;
    }

    //  删除管理员
    public boolean deleteAdminById(int adminId){
        String sql = "delete from admins where adminId = ?";
        Object objects[] = {adminId};

        boolean deleted = executeDBUpdate(sql, objects);
        return deleted;
    }

    @Override
    public Admin getAdminById(int adminId) {
        Admin admin = null;
        String sql = "select * from admins where adminId = ?";
        Object[] objects = {adminId};

        ResultSet rs = executeDBQuery(sql, objects);
        try {
            if(rs.next()){
                String adminNickName = rs.getString("adminNickName");
                String adminPassword = rs.getString("adminPassword");
                String adminProfile = rs.getString("adminProfile");
                int adminDepartmentId = rs.getInt("adminDepartmentId");
                int adminStation = rs.getInt("adminStation");

                admin = new Admin(adminId,adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStation);
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }

        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        //  超级管理员
        List<Admin> list = new ArrayList<>();
        Admin admin = new Admin();
        String sql = "select * from admins";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            //  循环判断是否存在下一个数据
            while (rs.next()){
                int adminId = rs.getInt("adminId");
                String adminNickName = rs.getString("adminNickName");
                String adminPassword = rs.getString("adminPassword");
                String adminProfile = rs.getString("adminProfile");
                int adminDepartmentId = rs.getInt("adminDepartmentId");
                int adminStation = rs.getInt("adminStation");

                admin = new Admin(adminId,adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStation);

                list.add(admin);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }

        return list;
    }

    //  添加本部员工
    public boolean registEmployeeOnDepartment(Employee employee){
        String sql = "insert into employees value (null,?,?,?,?,?,?,?)";
        String employeeName = employee.getEmployeeName();
        String employeePassword = employee.getEmployeePassword();
        int employeeGender = employee.getEmployeeGender();
        int employeeAge = employee.getEmployeeAge();
        String employeeProfile = employee.getEmployeeProfile();
        int employeeDepartmentId = employee.getEmployeeDepartmentId();

        Object objects[] = {employeeName,employeePassword,employeeGender,employeeAge,employeeProfile,employeeDepartmentId};
        boolean added = executeDBUpdate(sql, objects);

        return added;
    }

    //  查询本部门员工
    public List<Employee> getEmployeesOnDepartment(int departmentId){
        String sql = "select * from employees where departmentId = ?";
        Object objects[] = {departmentId};
        ResultSet rs = executeDBQuery(sql, objects);
        //  实例化 List 集合 Employee 类的一个对象 list
        List<Employee> list = new ArrayList<>();

        try{
            while(rs.next()){
                int employeeId = rs.getInt("employeeId");
                String employeeName = rs.getString("employeeName");
                String employeePassword = rs.getString("employeePassword");
                int employeeGender = rs.getInt("employeeGender");
                int employeeAge = rs.getInt("employeeAge");
                String employeeProfile = rs.getString("employeeProfile");
                int employeeDepartmentId = rs.getInt("employeeDepartmentId");
                int employeePowerId = rs.getInt("employeePowerId");
                String employeePosition = rs.getString("employeePosition");
                int employeeStation = rs.getInt("employeeStation");

                Employee employee = new Employee(employeeId, employeePassword, employeeName, employeeGender, employeeAge, employeeProfile, employeeDepartmentId,employeePowerId, employeePosition, employeeStation);
                list.add(employee);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            close(rs);
        }

        return list;
    }

    //  修改多个
    //  超级管理员修改信息功能？？


    //  查询所有员工信息
    public List<Employee> getAllEmployees(){
        List<Employee> list = new ArrayList<>();
        String sql = "select * from employees";

        ResultSet rs = executeDBQuery(sql,null);
        try {
            while(rs.next()){
                int employeeId = rs.getInt("employeeId");
                String employeeName = rs.getString("employeeName");
                String employeePassword = rs.getString("employeePassword");
                int employeeGender = rs.getInt("employeeGender");
                int employeeAge = rs.getInt("employeeAge");
                String employeeProfile = rs.getString("employeeProfile");
                int employeeDepartmentId = rs.getInt("employeeDepartmentId");
                int employeePowerId = rs.getInt("employeePowerId");
                String employeePosition = rs.getString("employeePosition");
                int employeeStation = rs.getInt("employeeStation");

                Employee employee = new Employee(employeeId, employeePassword, employeeName, employeeGender, employeeAge, employeeProfile, employeeDepartmentId,employeePowerId, employeePosition, employeeStation);
                list.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }

        return list;
    }
}
