package ctrl.factory;

import ctrl.dao.SplitDao;
import ctrl.implement.DepartmentImplement;
import ctrl.dao.DepartmentDao;
import ctrl.implement.SplitImplement;

public class DepartmentFactory {
    private static DepartmentFactory departmentFactory;
    private DepartmentDao departmentDao;
    private SplitDao splitDao;

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

    public SplitDao getSplitCtrl(){
        if(splitDao ==null){
            splitDao = new SplitImplement();
        }
        return splitDao;
    }
}
