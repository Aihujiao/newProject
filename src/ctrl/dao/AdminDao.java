package ctrl.dao;

import model.Admin;

import java.util.List;

public interface AdminDao {
    public Admin loginAdmin(Admin admin);

    //  超级管理员特殊功能选项
    public boolean registerAdmin(Admin admin);

    public boolean hadAdmin(String adminNickName);

    public String getAdminDepartmentNameById(int adminDepartmentId);

    public String getAdminStation(int adminStationId);

    public String getAdminPower(int adminPowerId);

    public boolean updateAdmin(Admin admin);

    public boolean deleteAdminById(int adminId);

    public Admin getAdminById(int adminId);

    //  超级管理员功能选项
    public List<Admin> getAllAdmins();

    public List<Admin> getAllLikeAdminsName(String adminLikeName);
}
