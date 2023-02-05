package ctrl.implement;

import ctrl.dao.DepartmentDao;
import ctrl.db.ExecuteDB;
import model.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentImplement extends ExecuteDB implements DepartmentDao {
    //  添加部门
    public boolean registDepartment(Department department){
        String sql = "insert into departments value (null,?,?)";
        String departmentName = department.getDepartmentName();
        String departmentIntro = department.getDepartmentIntro();
        Object objects[] = {departmentName,departmentIntro};

        boolean added = executeDBUpdate(sql, objects);

        return  added;
    }

    //  修改部门信息
    public boolean updateDepartment(Department department){
        String sql = "update departments set departmentName =?,departmentIntro = ? where departmentId = ?";
        int departmentId = department.getDepartmentId();
        String departmentName = department.getDepartmentName();
        String departmentIntro = department.getDepartmentIntro();

        Object objects[] = {departmentName,departmentIntro,departmentId};
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
                String departmentName = rs.getString("departmentName");
                String departmentIntro = rs.getString("departmentIntro");
                department = new Department(departmentId, departmentName, departmentIntro);

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
        Department department = null;
        String sql = "select * from departments";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            while (rs.next()){
                int departmentId = rs.getInt("departmentId");
                String departmentName = rs.getString("departmentName");
                String departmentIntro = rs.getString("departmentIntro");
                department = new Department(departmentId,departmentName,departmentIntro);
                list.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    //  超级管理员
    //  删除部门信息
    public boolean deleteDepartmentById(int departmentId){
        boolean isEmpty = false;
        boolean deleted =false;
        String sql = "select * from employees where employeeDepartmentId = ?";
        Object[] objects = {departmentId};
        ResultSet rs = executeDBQuery(sql, objects);
        try {
            if (!rs.next()){
                System.out.println("此时部门员工为空");
                isEmpty = true;
            }else {
                //  如果部门里还有员工，则不能执行下方删除部门的*关键代码*
                return false;
            }
            //  前端做限制，先读取管理员的部门编号，再传值执行该方法
            //  删除部门的 *关键代码*
            sql = "delete from departments where departmentId = ?";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //  如果为空就可以删除该部门
        if(isEmpty){
            deleted = executeDBUpdate(sql, objects);
        }
        return deleted;
    }
}
