package com.management.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.management.dbconfig.DbConfig;

public class Repository {
	private DbConfig config;
	private ResultSet rs = null;
	
	public Repository(DbConfig config) {
		this.config = config;
	}
	
	public DbConfig getConfig() {
		return config;
	}
	public void setConfig(DbConfig config) {
		this.config = config;
	}
	public PreparedStatement getPsmt() {
		return config.getPsmt();
	}

	public ResultSet executeQuerryRep(String querry) {
		try {
			rs = config.getSmt().executeQuery(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public int executeUpdateRep(String querry) {
		int ra = 0;
		try {
			ra = config.getSmt().executeUpdate(querry);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ra;

	}
	
	public ResultSet executePreparedStatement() {
		try {
			rs=config.getPsmt().executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public int updatePreparedStatement() {
		int ra=0;
		try {
			ra=config.getPsmt().executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ra;
	}

	public void SetQuerry(String psmtquerry) {
		// TODO Auto-generated method stub
		config.prepareStatement(psmtquerry);
	}
}
