package model;

public class Department {
    private int departmentId;
    private int departmentAdminId;
    private String departmentName;
    private String departmentIntro;

    public Department() {
    }

    public Department(int departmentId, int departmentAdminId, String departmentName, String departmentIntro) {
        this.departmentId = departmentId;
        this.departmentAdminId = departmentAdminId;
        this.departmentName = departmentName;
        this.departmentIntro = departmentIntro;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentAdminId() {
        return departmentAdminId;
    }

    public void setDepartmentAdminId(int departmentAdminId) {
        this.departmentAdminId = departmentAdminId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentIntro() {
        return departmentIntro;
    }

    public void setDepartmentIntro(String departmentIntro) {
        this.departmentIntro = departmentIntro;
    }
}
