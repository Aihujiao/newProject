package model;

public class Admin {
    private int adminId;
    private String adminPassword;
    private String adminNickName;
    private String adminProfile;
    private int adminDepartmentId;
    private int adminStation;

    public Admin() {
    }

    public Admin(int adminId, String adminNickName,String adminPassword,String adminProfile, int adminDepartmentId, int adminStation) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminNickName = adminNickName;
        this.adminProfile = adminProfile;
        this.adminDepartmentId = adminDepartmentId;
        this.adminStation = adminStation;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminNickName() {
        return adminNickName;
    }

    public void setAdminNickName(String adminNickName) {
        this.adminNickName = adminNickName;
    }

    public String getAdminProfile() {
        return adminProfile;
    }

    public void setAdminProfile(String adminProfile) {
        this.adminProfile = adminProfile;
    }

    public int getAdminDepartmentId() {
        return adminDepartmentId;
    }

    public void setAdminDepartmentId(int adminDepartmentId) {
        this.adminDepartmentId = adminDepartmentId;
    }

    public int getAdminStation() {
        return adminStation;
    }

    public void setAdminStation(int adminStation) {
        this.adminStation = adminStation;
    }
}
