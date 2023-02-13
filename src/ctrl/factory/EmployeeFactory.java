package ctrl.factory;

import ctrl.dao.PositionDao;
import ctrl.dao.SplitDao;
import ctrl.dao.StationDao;
import ctrl.implement.EmployeeImplement;
import ctrl.dao.EmployeeDao;
import ctrl.implement.PositionImplement;
import ctrl.implement.SplitImplement;
import ctrl.implement.StationImplement;

public class EmployeeFactory {
    private static EmployeeFactory employeeFactory;
    private EmployeeDao employeeDao;
    private PositionDao positionDao;
    private StationDao stationDao;
    private SplitDao splitDao;

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

    public PositionDao getPositionCtrl(){
        if(positionDao == null){
            positionDao = new PositionImplement();
        }
        return positionDao;
    }

    public StationDao getStationCtrl(){
        if(stationDao == null){
            stationDao = new StationImplement();
        }
        return stationDao;
    }

    public SplitDao getSplitCtrl(){
        if(splitDao ==null){
            splitDao = new SplitImplement();
        }
        return splitDao;
    }
}
