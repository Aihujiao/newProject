package ctrl.factory;

import ctrl.implement.AdminImplement;
import ctrl.dao.AdminDao;

/**
 *
 */
public class AdminFactory {
    private static AdminFactory adminFactory;
    private AdminDao adminDao = null;

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
}
