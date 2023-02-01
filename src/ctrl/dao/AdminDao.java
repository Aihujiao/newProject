package ctrl.dao;

import model.Admin;

import java.util.List;

public interface AdminDao {
    public Admin loginAdmin(Admin admin);

    //  超级管理员特殊功能选项
    public boolean registAdmin(Admin admin);

    public boolean updateAdmin(Admin admin);

    public boolean deleteAdminById(int adminId);

    public Admin getAdminById(int adminId);

    //  超级管理员功能选项
    public List<Admin> getAllAdmins();
}
