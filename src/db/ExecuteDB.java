package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecuteDB extends DBUtil{
    private Connection con = null;
    private PreparedStatement pstmt = null;

    public boolean executeDBUpdate(String sql,Object objects[]){
        boolean b = false;
        con = getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            if(objects != null){
                for(int i =0;i<objects.length;i++){
                    pstmt.setObject(i+1,objects[i]);
                }
            }

            int row = pstmt.executeUpdate();
            if(row>0){
                b = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(null,pstmt,con);
        }

        return b;
    }

    public ResultSet executeDBQuery(String sql,Object objects[]){
        ResultSet rs = null;
        con = getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            if(objects != null){
                for(int i =0;i<objects.length;i++){
                    pstmt.setObject(i+1,objects[i]);
                }
            }

            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return rs;
    }

    public void close(ResultSet rs){
        close(rs,pstmt,con);
    }
}
