package ctrl.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ORMUtil extends CRUDUtil{
    public Object getORM(String sql,Object[] objects,Class classes) {
        Object object = null;
        //	获得结果集
        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if(rs.next()) {
                //	获得该类的所有自建方法
                Method[] methods = classes.getDeclaredMethods();
                //	实例化要获得的类对象
                object = classes.newInstance();
                //	获得数据集的 ResultMetaData类  元数据操作类
                ResultSetMetaData metaData = rs.getMetaData();
                int count = metaData.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    String columnName = metaData.getColumnName(i);
                    for (Method method : methods) {
                        String methodName = method.getName();
                        if(methodName.equalsIgnoreCase("set" + columnName)){
                            Object objectValue = rs.getObject(i);
                            method.invoke(object, objectValue);
                        }
                    }

                }
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return object ;
    }

    public List getORMS(String sql,Object[] objects,Class classes){
        List list = new ArrayList<>();
        Object object = null;
        //	获得结果集
        ResultSet rs = executeDBQuery(sql, objects);

        try {
            while(rs.next()) {
                //	初始化
                ResultSetMetaData metaData = null;
                int count = 0;

                if(metaData == null && count == 0) {
                    metaData = rs.getMetaData();
                    count = metaData.getColumnCount();
                }

                //	获得该类的所有自建方法
                Method[] methods = classes.getDeclaredMethods();
                //	实例化要获得的类对象
                object = classes.newInstance();
                //	获得数据集的 ResultMetaData类  元数据操作类
                for (int i = 1; i <= count; i++) {
                    //	依次获取列名
                    String columnName = metaData.getColumnName(i);
                    for (Method method : methods) {
                        //	获得方法名
                        String methodName = method.getName();
                        //	对比判断是否匹配set方法
                        if(methodName.equalsIgnoreCase("set" + columnName)){
                            //	获取匹配列的值并在下一行被用于赋值
                            Object objectValue = rs.getObject(i);
                            method.invoke(object, objectValue);
                        }
                    }

                }
                list.add(object);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list ;
    }

    public void setORMforDB(Object object) {
        //  获得 ==>  项目包.文件名
        String objectName = object.getClass().getName();
        //  数据库表名均需要在末尾加s
        String tableName = object.getClass().getSimpleName().toLowerCase()+"s";
        String sql = "select * from ? limit 1";
        Object[] objects = {tableName};
        System.out.println("获得的数据表名为"+tableName);
        //  获取对象类的Class对象
        //  之后用于实例化空的对应类对象以便得到该类的各种方法
        Class objectClass = object.getClass();
        //  始终获取该对象对应表的第一条数据(数据可为空)
        ResultSet rs = executeDBQuery(sql, objects);

        //  如果传入的对象不为空会进行之后操作
        //  早做判断，避免资源浪费
        try {
            if(object != null && rs.next()){
                ResultSetMetaData metaData = rs.getMetaData();
                //  获取表中的列数
                int dataColumnNum = metaData.getColumnCount();
                System.out.println("获得的数据表列名数量为"+dataColumnNum);
                int setMethodNum = 0;

                //  提前准备列名和方法名
                String columnName = null;
                String methodName = null;
                //  重用对象 objects
                objects = null;

                Method[] methods = objectClass.getDeclaredMethods();

                for(int i = 1;i <= dataColumnNum;i++){
                    //  获得列名
                    columnName = metaData.getColumnName(i);
                    for (Method method: methods) {
                        //  获取方法名
                        methodName = method.getName();
                        //  对比方法名与列名，如果相等就调用对应的set方法
                        if (methodName.equals("set"+columnName)){
                            setMethodNum ++;

                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //  String objectName = object.getClass().getSimpleName();
        //  获得 ==>  文件名

    }
}
