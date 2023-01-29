package ctrl;

import ctrl.dao.DepartmentDao;
import db.ExecuteDB;
import model.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentCtrl extends ExecuteDB implements DepartmentDao {
    //  添加部门
    public boolean registDepartment(Department department){
        String sql = "insert into departments value (null,?,?,?)";
        int departmentAdminId = department.getDepartmentAdminId();
        String departmentName = department.getDepartmentName();
        String departmentIntro = department.getDepartmentIntro();
        Object objects[] = {departmentAdminId,departmentName,departmentIntro};

        boolean added = executeDBUpdate(sql, objects);

        return  added;
    }

    //  修改部门信息
    public boolean updateDepartment(Department department){
        String sql = "update departments set departmentAdminId = ?,departmentName =?,departmentIntro = ? where departmentId = ?";
        int departmentId = department.getDepartmentId();
        int departmentAdminId = department.getDepartmentAdminId();
        String departmentName = department.getDepartmentName();
        String departmentIntro = department.getDepartmentIntro();

        Object objects[] = {departmentAdminId,departmentName,departmentIntro,departmentId};
        boolean updated = executeDBUpdate(sql, objects);

        return updated;
    }

    //  查询部门信息
    public Department getDepartmentById(int departmentId){
        Department department = new Department();
        String sql = "select * from departments where departmentId = ?";

        Object objects[] = {departmentId};
        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if (rs.next()){
                int departmentAdminId = rs.getInt("departmentId");
                String departmentName = rs.getString("departmentName");
                String departmentIntro = rs.getString("departmentIntro");
                department = new Department(departmentId, departmentAdminId, departmentName, departmentIntro);

                return department;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  查询所有部门信息
    public List<Department> getAllDepartment(){
        List<Department> list =new ArrayList();
        Department department = new Department();
        String sql = "select * from departments";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            if(rs.next()){
                int departmentId = rs.getInt("departmentId");
                int departmentAdminId = rs.getInt("departmentAdminId");
                String departmentName = rs.getString("departmentName");
                String departmentIntro = rs.getString("departmentIntro");
                department = new Department(departmentId,departmentAdminId,departmentName,departmentIntro);
                list.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //  超级管理员
    //  删除部门信息
    public boolean deleteDepartment(int departmentId){
        boolean isEmpty = false;
        boolean deleted =false;
        String sql = "select * from employees where employeeDepartmentId = ?";
        ResultSet rs = executeDBQuery(sql, null);
        try {
            if (rs.next()){
                isEmpty = true;
            }
            //  前端做限制，先读取管理员的部门编号，再传值执行该方法
            sql = "delete from departments where departmentId = ?";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //  如果为空就可以删除该部门
        if(isEmpty){
            Object objects[] = {departmentId};
            deleted = executeDBUpdate(sql, objects);
        }
        return deleted;
    }
}
