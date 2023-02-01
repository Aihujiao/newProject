package factory;

import ctrl.AdminCtrl;
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

    public AdminDao getAdminDao(){
        if(adminDao == null){
            adminDao = new AdminCtrl();
        }
        return adminDao;
    }
}
