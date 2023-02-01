package factory;

import ctrl.EmployeeCtrl;
import ctrl.dao.EmployeeDao;

public class EmployeeFactory {
    private static EmployeeFactory employeeFactory;
    private EmployeeDao employeeDao;

    public static EmployeeFactory instance(){
        if(employeeFactory == null){
            employeeFactory = new EmployeeFactory();
        }
        return  employeeFactory;
    }

    public EmployeeDao getEmployeeDao(){
        if(employeeDao == null){
            employeeDao = new EmployeeCtrl();
        }
        return employeeDao;
    }
}
