package ctrl.factory;

import ctrl.implement.EmployeeImplement;
import ctrl.implement.dao.EmployeeDao;

public class PowerFactory {
    private static PowerFactory employeeFactory;
    private EmployeeDao employeeDao;

    public static PowerFactory instance(){
        if(employeeFactory == null){
            employeeFactory = new PowerFactory();
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
