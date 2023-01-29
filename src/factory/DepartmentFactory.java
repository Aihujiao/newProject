package factory;

import ctrl.DepartmentCtrl;
import ctrl.dao.DepartmentDao;

public class DepartmentFactory {
    private static DepartmentFactory departmentFactory;
    private DepartmentDao departmentDao;

    public static DepartmentFactory instance(){
        if(departmentFactory == null){
            DepartmentFactory departmentFactory = new DepartmentFactory();
        }
        return  departmentFactory;
    }

    public DepartmentDao getDepartmentDaoDao(){
        if(departmentDao == null){
            DepartmentDao departmentDao = new DepartmentCtrl();
        }
        return departmentDao;
    }
}
