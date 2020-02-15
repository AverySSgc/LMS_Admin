package com.SS.library.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.SS.library.Utility.ConnectUtil;

public abstract class DAO <T> {
	
	@Autowired
	static
	ConnectUtil conUtil;

	public DAO() {conn =conUtil.getConnection();}
    protected static Connection conn;
   

    protected void save(String sql, Object[] vals) throws SQLException {
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

    protected Integer saveRecieveKey(String sql, Object[] vals) throws SQLException {
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

    protected List<T> read(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (vals!=null) {
            int index = 1;
            for(Object o : vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractData(rs);
    }
    protected List<T> readEssential(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        if (vals!=null) {
            int index = 1;
            for(Object o : vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        return extractEssentialData(rs);
    }


    protected abstract List<T> extractData(ResultSet rs) throws SQLException;
    protected abstract List<T> extractEssentialData(ResultSet rs) throws SQLException;

    public abstract Integer add(T object)throws SQLException;
    public abstract void update(T object)throws SQLException;
    public abstract void delete(T object)throws SQLException;
    public abstract List<T> read() throws SQLException;

}
