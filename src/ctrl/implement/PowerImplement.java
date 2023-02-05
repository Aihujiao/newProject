package ctrl.implement;

import ctrl.db.ExecuteDB;
import ctrl.dao.PowerDao;
import model.Power;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PowerImplement extends ExecuteDB implements PowerDao {
    @Override
    public boolean hadPower(String powerName) {
        String sql = "select powerId from powers where powerName =?";
        Object[] objects = {powerName};
        boolean exist = false;

        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if(rs.next()){
                exist = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rs);
        }
        return exist;
    }

    //  添加部门
    public boolean registerPower(Power power){
        String sql = "insert into powers value (null,?,?)";
        String powerName = power.getPowerName();
        String powerIntro = power.getPowerIntro();
        Object objects[] = {powerName,powerIntro};

        boolean added = executeDBUpdate(sql, objects);

        return  added;
    }

    //  修改部门信息
    public boolean updatePower(Power power){
        String sql = "update powers set powerName = ?,powerLevel = ?,powerIntro = ? where powerId = ?";
        int powerId = power.getPowerId();
        String powerName = power.getPowerName();
        int powerLevel = power.getPowerLevel();
        String powerIntro = power.getPowerIntro();

        Object objects[] = {powerName,powerLevel,powerIntro,powerId};
        boolean updated = executeDBUpdate(sql, objects);

        return updated;
    }

    //  查询部门信息
    public Power getPowerById(int powerId){
        Power power = new Power();
        String sql = "select * from powers where powerId = ?";

        Object objects[] = {powerId};
        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if (rs.next()){
                String powerName = rs.getString("powerName");
                int powerLevel = rs.getInt("powerLevel");
                String powerIntro = rs.getString("powerIntro");
                power = new Power(powerId, powerName,powerLevel, powerIntro);

                return power;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  查询所有部门信息
    public List<Power> getAllPowers(){
        List<Power> list =new ArrayList();
        Power power = null;
        String sql = "select * from powers";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            while (rs.next()){
                int powerId = rs.getInt("powerId");
                String powerName = rs.getString("powerName");
                int powerLevel = rs.getInt("powerLevel");
                String powerIntro = rs.getString("powerIntro");
                power = new Power(powerId,powerName,powerLevel,powerIntro);
                list.add(power);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public List<Power> getAllOptions() {
        List<Power> powerList = null;
        Power power = null;
        String sql = "select powerName,powerLevel from powers";
        ResultSet rs = executeDBQuery(sql, null);

        try {
            while(rs.next()){
                String powerName = rs.getString("powerName");
                int powerLevel = rs.getInt("powerLevel");

                power = new Power(0,powerName,powerLevel,null);
                powerList.add(power);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return powerList;
    }

    //  超级管理员
    //  删除部门信息
    public boolean deletePowerById(int powerId){
        boolean isEmpty;
        boolean deleted =false;
        String sql = "select powerName from powers where powerId = ?";
        Object[] objects = {powerId};
        ResultSet rs = executeDBQuery(sql, objects);
        try {
            if (!rs.next()){
                System.out.println("此时部门员工为空");
                isEmpty = true;
            }else {
                //  如果部门里还有员工，则不能执行下方删除部门的*关键代码*
                return false;
            }
            //  前端做限制，先读取管理员的部门编号，再传值执行该方法
            //  删除部门的 *关键代码*
            sql = "delete from powers where powerId = ?";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //  如果为空就可以删除该部门
        if(isEmpty){
            deleted = executeDBUpdate(sql, objects);
        }
        return deleted;
    }
}
