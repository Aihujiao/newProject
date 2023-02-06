package ctrl.factory;

import ctrl.dao.*;
import ctrl.implement.*;

/**
 *
 */
public class AdminFactory {
    private static AdminFactory adminFactory;
    private AdminDao adminDao = null;
    private DepartmentDao departmentDao = null;
    private PositionDao positionDao = null;
    private StationDao stationDao = null;
    private PowerDao powerDao = null;



    public static AdminFactory instance(){
        if(adminFactory == null){
            adminFactory = new AdminFactory();
        }
        return  adminFactory;
    }

    public AdminDao getAdminCtrl(){
        if(adminDao == null){
            adminDao = new AdminImplement();
        }
        return adminDao;
    }

    public DepartmentDao getDepartmentCtrl(){
        if (departmentDao == null){
            departmentDao = new DepartmentImplement();
        }
        return departmentDao;
    }

    public PositionDao getPositionCtrl(){
        if (positionDao == null){
            positionDao = new PositionImplement();
        }
        return positionDao;
    }

    public StationDao getStationCtrl(){
        if (stationDao == null){
            stationDao = new StationImplement();
        }
        return stationDao;
    }

    public PowerDao getPowerCtrl(){
        if (powerDao == null){
            powerDao = new PowerImplement();
        }
        return powerDao;
    }
}
