package ctrl.factory;

import ctrl.implement.EmployeeImplement;
import ctrl.implement.dao.EmployeeDao;

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
            employeeDao = new EmployeeImplement();
        }
        return employeeDao;
    }
}
