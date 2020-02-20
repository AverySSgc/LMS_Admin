package com.SS.library.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public abstract class DAO <T> {
	   

    protected void save(String sql, Object[] vals,Connection conn) throws SQLException {
        PreparedStatement  pstmt = conn.prepareStatement(sql);
        if(vals!=null){
            int index = 1;
            for(Object o : vals){
                pstmt.setObject(index,o);
                index++;
            }
        }
        pstmt.executeUpdate();
    }

    protected Integer saveRecieveKey(String sql, Object[] vals,Connection conn) throws SQLException {
        PreparedStatement  pstmt = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        if(vals!=null){
            int index = 1;
            for(Object o : vals){
                pstmt.setObject(index,o);
                index++;
            }
        }
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        while(rs.next()){
            return rs.getInt(1);
        }
        return null;
    }

    protected List<T> read(String sql, Object[] vals,Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (vals!=null) {
            int index = 1;
            for(Object o : vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractData(rs,conn);
    }
    protected List<T> readEssential(String sql, Object[] vals,Connection conn) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (vals!=null) {
            int index = 1;
            for(Object o : vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractEssentialData(rs,conn);
    }


    protected abstract List<T> extractData(ResultSet rs,Connection conn) throws SQLException;
    protected abstract List<T> extractEssentialData(ResultSet rs,Connection conn) throws SQLException;

    public abstract Integer add(T object, Connection conn)throws SQLException;
    public abstract void update(T object, Connection conn)throws SQLException;
    public abstract void delete(int id, Connection conn)throws SQLException;
    public abstract List<T> read(Connection conn) throws SQLException;

}
