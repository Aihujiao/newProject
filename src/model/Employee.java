package model;

public class Employee {
    private int employeeId;
    private String employeePassword;
    private String employeeName;
    private int employeeGender;
    private int employeeAge;
    private String employeeProfile;
    private int departmentId;
    private String employeePosition;
    private int employeeStation;

    public Employee() {
    }

    public Employee(int employeeId, String employeePassword, String employeeName, int employeeGender, int employeeAge, String employeeProfile, int departmentId, String employeePosition, int employeeStation) {
        this.employeeId = employeeId;
        this.employeePassword = employeePassword;
        this.employeeName = employeeName;
        this.employeeGender = employeeGender;
        this.employeeAge = employeeAge;
        this.employeeProfile = employeeProfile;
        this.departmentId = departmentId;
        this.employeePosition = employeePosition;
        this.employeeStation = employeeStation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeGender() {
        return employeeGender;
    }

    public void setEmployeeGender(int employeeGender) {
        this.employeeGender = employeeGender;
    }

    public int getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(int employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getEmployeeProfile() {
        return employeeProfile;
    }

    public void setEmployeeProfile(String employeeProfile) {
        this.employeeProfile = employeeProfile;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public int getEmployeeStation() {
        return employeeStation;
    }

    public void setEmployeeStation(int employeeStation) {
        this.employeeStation = employeeStation;
    }
}
