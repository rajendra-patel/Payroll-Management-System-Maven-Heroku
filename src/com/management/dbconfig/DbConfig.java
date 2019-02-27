package com.management.dbconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class DbConfig {

	private static DbConfig instance = null;

	private Connection con = null;
	private Statement smt = null;
	private PreparedStatement psmt = null;
	private Driver jdbc_driver=null;
	private DbConfig() {
		try {
			jdbc_driver=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(jdbc_driver);
			con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/rdb4pms", "rdb4pms", "pass4rdb4pms");
			smt = con.createStatement();
//			.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			psmt = null;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DbConfig getInstance() {
		if (instance == null) {
			return new DbConfig();
		} else {
			return instance;
		}
		
	}

	public Connection getCon() {
		return con;
	}

	public Statement getSmt() {
		return smt;
	}
	
	public PreparedStatement getPsmt() {
		return psmt;
	}
	
	public void prepareStatement(String querry) {
		try {
			psmt = con.prepareStatement(querry, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		 try {
			 	DriverManager.deregisterDriver(jdbc_driver);
			 	if(con!=null)
				con.close();
				if(smt!=null)
				smt.close();
				if(psmt!=null)
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
