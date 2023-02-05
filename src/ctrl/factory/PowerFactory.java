package ctrl.factory;

import ctrl.implement.PowerImplement;
import ctrl.dao.PowerDao;

public class PowerFactory {
    private static PowerFactory powerFactory;
    private PowerDao powerDao;

    public static PowerFactory instance(){
        if(powerFactory == null){
            powerFactory = new PowerFactory();
        }
        return  powerFactory;
    }

    public PowerDao getPowerCtrl(){
        if(powerDao == null){
            powerDao = new PowerImplement();
        }
        return powerDao;
    }
}
