package factory;

import ctrl.AdminCtrl;
import ctrl.EmployeeCtrl;
import ctrl.dao.AdminDao;
import ctrl.dao.EmployeeDao;

public class EmployeeFactory {
    private static EmployeeFactory employeeFactory;
    private EmployeeDao employeeDao;

    public static EmployeeFactory instance(){
        if(employeeFactory == null){
            EmployeeFactory employeeFactory = new EmployeeFactory();
        }
        return  employeeFactory;
    }

    public EmployeeDao getEmployeeDao(){
        if(employeeDao == null){
            EmployeeDao employeeDao = new EmployeeCtrl();
        }
        return employeeDao;
    }
}
