package ctrl.implement;

import ctrl.dao.AdminDao;
import ctrl.db.ORMUtil;
import model.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminImplement extends ORMUtil implements AdminDao {
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
                int adminStationId = rs.getInt("adminStationId");
                int adminPowerId = rs.getInt("adminPowerId");
                admin = new Admin(adminId, adminNickName, adminPassword, adminProfile, adminDepartmentId, adminStationId,adminPowerId);
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
    public boolean registerAdmin(Admin admin) {
        //  超級管理員
        //  读取前端登录页面的信息
        String adminNickName = admin.getAdminNickName();
        String adminPassword = admin.getAdminPassword();
        String adminProfile = admin.getAdminProfile();
        int adminDepartmentId = admin.getAdminDepartmentId();
        int adminStationIdId = admin.getAdminStationId();

        String sql = "insert into admins value (null,?,?,?,?,?)";
        Object[] objects = {adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStationIdId};

        boolean registered = executeDBUpdate(sql, objects);

        return registered;
    }

    @Override
    public boolean hadAdmin(String adminNickName) {
        String sql = "select adminId from admins where adminNickName = ?";
        Object[] objects = {adminNickName};
        boolean Exist = false;

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if (rs.next()){
                Exist = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }
        return Exist;
    }

    @Override
    public String getAdminDepartmentNameById(int adminDepartmentId) {
        String departmentName = null;
        String sql = "select departmentName from departments where departmentId = ?";
        Object[] objects = {adminDepartmentId};

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if(rs.next()){
                departmentName = rs.getString("departmentName");
                System.out.println("得到的部门名称是"+ departmentName);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }

        return departmentName;
    }

    @Override
    public String getAdminStationById(int adminStationId) {
        String adminStationName = null;
        String sql = "select stationName from stations where stationId = ?";
        Object[] objects = {adminStationId};

        ResultSet rs = executeDBQuery(sql, objects);
        try {
            if(rs.next()){
                //  如果能查到状态信息就将值附到adminStationName并最后返回
                adminStationName = rs.getString("stationName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adminStationName;
    }

    @Override
    public String getAdminPower(int adminPowerId) {
        String adminPowerName = null;
        String sql = "select powerName from powers where powerId = ?";
        Object[] objects = {adminPowerId};

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if (rs.next()){
                adminPowerName = rs.getString("powerName");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adminPowerName;
    }


    //  修改管理员信息
    public boolean updateAdmin(Admin admin){
        String adminNickName = admin.getAdminNickName();
        String adminPassword = admin.getAdminPassword();
        String adminProfile = admin.getAdminProfile();
        int adminDepartment = admin.getAdminDepartmentId();
        int adminStationId = admin.getAdminStationId();
        int adminId = admin.getAdminId();

        String sql ="update admins set adminNickName = ?,adminPassword = ?,adminProfile = ?,adminDepartmentId = ?,adminStationId = ? where adminId = ?";
        Object objects[] = {adminNickName,adminPassword,adminProfile,adminDepartment,adminStationId,adminId};

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
                int adminStationId = rs.getInt("adminStationId");
                int adminPowerId = rs.getInt("adminPowerId");

                admin = new Admin(adminId,adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStationId,adminPowerId);
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
        String sql = "select * from admins limit ?,?";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            //  循环判断是否存在下一个数据
            while (rs.next()){
                int adminId = rs.getInt("adminId");
                String adminNickName = rs.getString("adminNickName");
                String adminPassword = rs.getString("adminPassword");
                String adminProfile = rs.getString("adminProfile");
                int adminDepartmentId = rs.getInt("adminDepartmentId");
                int adminStationId = rs.getInt("adminStationId");
                int adminPowerId = rs.getInt("adminPowerId");

                admin = new Admin(adminId,adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStationId,adminPowerId);

                list.add(admin);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }

        return list;
    }

    @Override
    public List<Admin> getAllLikeAdminsName(String adminLikeName) {
        String sql = "select * from admins where adminNickName like '%?%'";

        List<Admin> adminList = null;
        Admin admin = null;

        Object[] objects = {adminLikeName};

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            while (rs.next()){
                int adminId = rs.getInt("adminId");
                String adminNickName = rs.getString("adminNickName");
                String adminPassword = rs.getString("adminPassword");
                String adminProfile = rs.getString("adminProfile");
                int adminDepartmentId = rs.getInt("adminDepartmentId");
                int adminStationId = rs.getInt("adminStationId");
                int adminPowerId = rs.getInt("adminPowerId");

                admin = new Admin(adminId,adminNickName,adminPassword,adminProfile,adminDepartmentId,adminStationId,adminPowerId);
                adminList.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return adminList;
    }

    @Override
    public List<Admin> adminList(Map<String, Object> conditionMap) {
        String sql = "select * from admins limit ?,?";
        int begin = Integer.parseInt(conditionMap.get("begin").toString());
        int size= Integer.parseInt(conditionMap.get("size").toString());
        Object[] objects = {begin,size};
        List<Admin> adminList = getORMS(sql, objects, Admin.class);

        return adminList;
    }

    @Override
    public List<Admin> adminQuery(Map<String, Object> conditionMap) {
        List<Admin> adminList = null;

        if(conditionMap != null){
            String sql = null;
            Object[] objects = null;
            int adminId = Integer.parseInt(conditionMap.get("adminId").toString());
            String adminNickName = conditionMap.get("adminName").toString();
            int adminDepartmentId = Integer.parseInt(conditionMap.get("adminDepartmentId").toString());
            int adminPowerId = Integer.parseInt(conditionMap.get("adminPowerId").toString());
            int adminPositionId = Integer.parseInt(conditionMap.get("adminPositionId").toString());

            if(adminId != 0 && adminDepartmentId ==0 && adminNickName == null && adminPowerId == 0 && adminPositionId == 0){
                sql = "select adminId,adminNickName ,adminDepartmentId,adminPowerId,adminPositionId from admins where adminId = ? limit ?,?";
                int begin = Integer.parseInt(conditionMap.get("begin").toString());
                int size = Integer.parseInt(conditionMap.get("size").toString());
                objects = new Object[]{adminId,begin,size};
            } else if (adminId == 0 && adminDepartmentId !=0 && adminNickName == null && adminPowerId == 0 && adminPositionId == 0) {
                sql = "select adminId,adminNickName ,adminDepartmentId,adminPowerId,adminPositionId from admins where adminDepartmentId = ? limit ?,?";
                int begin = Integer.parseInt(conditionMap.get("begin").toString());
                int size = Integer.parseInt(conditionMap.get("size").toString());
                objects = new Object[]{adminDepartmentId,begin,size};
            } else if (adminId == 0 && adminDepartmentId ==0 && adminNickName != null && adminPowerId == 0 && adminPositionId == 0) {
                sql = "select adminId,adminNickName ,adminDepartmentId,adminPowerId,adminPositionId from admins where adminNickName = ? limit ?,?";
                int begin = Integer.parseInt(conditionMap.get("begin").toString());
                int size = Integer.parseInt(conditionMap.get("size").toString());
                objects = new Object[]{adminNickName,begin,size};
            } else if (adminId == 0 && adminDepartmentId ==0 && adminNickName == null && adminPowerId != 0 && adminPositionId == 0) {
                sql = "select adminId,adminNickName ,adminDepartmentId,adminPowerId,adminPositionId from admins where adminPowerId = ? limit ?,?";
                int begin = Integer.parseInt(conditionMap.get("begin").toString());
                int size = Integer.parseInt(conditionMap.get("size").toString());
                objects = new Object[]{adminPowerId,begin,size};
            } else if (adminId == 0 && adminDepartmentId ==0 && adminNickName == null && adminPowerId == 0 && adminPositionId != 0) {
                sql = "select adminId,adminNickName ,adminDepartmentId,adminPowerId,adminPositionId from admins where adminPositionId = ? limit ?,?";
                int begin = Integer.parseInt(conditionMap.get("begin").toString());
                int size = Integer.parseInt(conditionMap.get("size").toString());
                objects = new Object[]{adminPositionId,begin,size};
            }
            adminList = getORMS(sql, objects, Admin.class);
        }
        return adminList;
    }

//    public List<Power> getAllAdminPowers(){
//        String sql ="select * from powers";
//        List<Power> list = new ArrayList<>();
//        Power power = null;
//        ResultSet rs = executeDBQuery(sql, null);
//
//
//        try {
//            while(rs.next()){
//                int powerId = rs.getInt("powerId");
//                String powerName =rs.getString("powerName");
//                int powerLevel = rs.getInt("powerLevel");
//                String powerIntro = rs.getString("powerIntro");
//
//                power = new Power(powerId,powerName,powerLevel,powerIntro);
//                list.add(power);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return list;
//    }

}
