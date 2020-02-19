package com.SS.library.Utility;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;


class ConnectUtilTest {

//	@Test
//	void test() {	
//		ConnectUtil connectUtil;
//		try {
//			connectUtil = new ConnectUtil();
//			assertNotNull(connectUtil);	
//		} catch (ClassNotFoundException | SQLException e) {
//			// TODO Auto-generated catch block
//			fail();
//		}	
//	}

	@Test
	void testGetConnection() {
		try {
			ConnectUtil connectUtil = new ConnectUtil();
			Connection conn = connectUtil.getConnection();
			assertNotNull(conn);
			assertNotNull(connectUtil);	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			fail();
		}	
	}
	
}
