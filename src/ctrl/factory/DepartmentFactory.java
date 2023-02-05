package ctrl.factory;

import ctrl.implement.DepartmentImplement;
import ctrl.dao.DepartmentDao;

public class DepartmentFactory {
    private static DepartmentFactory departmentFactory;
    private DepartmentDao departmentDao;

    public static DepartmentFactory instance(){
        if(departmentFactory == null){
            departmentFactory = new DepartmentFactory();
        }
        return  departmentFactory;
    }

    public DepartmentDao getDepartmentCtrl(){
        if(departmentDao == null){
            departmentDao = new DepartmentImplement();
        }
        return departmentDao;
    }
}
