package com.SS.library.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ConnectUtil {
    private String driver= "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.1.12/library?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "root";
    private String password = "password";
//    private Connection conn =null;
//    private static ConnectUtil instance;

//    public ConnectUtil() throws SQLException, ClassNotFoundException {
//        Class.forName(driver);
//        Connection conn = DriverManager.getConnection(url,username,password);
//        conn.setAutoCommit(false);
//    }

//    public static ConnectUtil getInstance() throws SQLException, ClassNotFoundException {
//        if(instance==null){
//            instance = new ConnectUtil();
//        }
//        return instance;
//    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url,username,password);
        conn.setAutoCommit(false);
        return conn;
    }

//    public void deConsruct() throws SQLException {
//        if(instance!=null) {
//            instance.conn.close();
//            instance.conn=null;
//            instance = null;
//        }
//    }
}

