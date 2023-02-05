package ctrl.factory;

import ctrl.implement.EmployeeImplement;
import ctrl.implement.dao.EmployeeDao;

public class StationFactory {
    private static StationFactory employeeFactory;
    private EmployeeDao employeeDao;

    public static StationFactory instance(){
        if(employeeFactory == null){
            employeeFactory = new StationFactory();
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
