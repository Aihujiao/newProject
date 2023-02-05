package ctrl.factory;

import ctrl.implement.EmployeeImplement;
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

    public EmployeeDao getEmployeeCtrl(){
        if(employeeDao == null){
            employeeDao = new EmployeeImplement();
        }
        return employeeDao;
    }
}
