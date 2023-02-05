package ctrl.factory;

import ctrl.implement.StationImplement;
import ctrl.dao.StationDao;


public class StationFactory {
    private static StationFactory stationFactory;
    private StationDao stationDao;

    public static StationFactory instance(){
        if(stationFactory == null){
            stationFactory = new StationFactory();
        }
        return  stationFactory;
    }

    public StationDao getStationCtrl(){
        if(stationDao == null){
            stationDao = new StationImplement();
        }
        return stationDao;
    }
}
