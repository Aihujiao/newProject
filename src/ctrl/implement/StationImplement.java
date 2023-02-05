package ctrl.implement;

import ctrl.db.ExecuteDB;
import ctrl.dao.StationDao;
import model.Station;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationImplement extends ExecuteDB implements StationDao {
    //  添加部门
    public boolean registerStation(Station station){
        String sql = "insert into stations value (null,?,?)";
        String stationName = station.getStationName();
        String stationIntro = station.getStationIntro();
        Object objects[] = {stationName,stationIntro};

        boolean added = executeDBUpdate(sql, objects);

        return  added;
    }

    //  修改部门信息
    public boolean updateStation(Station station){
        String sql = "update stations set stationName =?,stationIntro = ? where stationId = ?";
        int stationId = station.getStationId();
        String stationName = station.getStationName();
        String stationIntro = station.getStationIntro();

        Object objects[] = {stationName,stationIntro,stationId};
        boolean updated = executeDBUpdate(sql, objects);

        return updated;
    }

    //  查询部门信息
    public Station getStationById(int stationId){
        Station station;
        String sql = "select * from stations where stationId = ?";

        Object objects[] = {stationId};
        ResultSet rs = executeDBQuery(sql, objects);

        try {
            if (rs.next()){
                String stationName = rs.getString("stationName");
                String stationIntro = rs.getString("stationIntro");
                station = new Station(stationId, stationName, stationIntro);

                return station;
            }else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //  查询所有部门信息
    public List<Station> getAllStations(){
        List<Station> stationList =new ArrayList();
        Station station = null;
        String sql = "select * from stations";

        ResultSet rs = executeDBQuery(sql, null);

        try {
            while (rs.next()){
                int stationId = rs.getInt("stationId");
                String stationName = rs.getString("stationName");
                String stationIntro = rs.getString("stationIntro");
                station = new Station(stationId,stationName,stationIntro);
                stationList.add(station);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return stationList;
    }

    //  超级管理员
    //  删除部门信息
    public boolean deleteStationById(int stationId){
        boolean isEmpty = false;
        boolean deleted =false;
        String sql = "select * from employees where employeeStationId = ?";
        Object[] objects = {stationId};
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
            sql = "delete from stations where stationId = ?";
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
