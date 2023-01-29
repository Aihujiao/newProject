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
            //  这里可能存在问题
            System.out.println("执行到了要实例化Dao");
            adminDao = new AdminCtrl();
        }
        return adminDao;
    }
}
