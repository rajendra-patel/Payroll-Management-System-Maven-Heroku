package com.management.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.management.dbconfig.DbConfig;
import com.management.repo.*;

public class LoginDao {
	private DbConfig config = DbConfig.getInstance();
	private Repository rp = new Repository(config);

	public String AuthenticateLogin(String user, String pass) {
		ResultSet loginTokenRs=null;
		
		String psmtQuerry = "select Token from login where Username=? and Password=?";
			rp.SetQuerry(psmtQuerry);
			try {
				rp.getPsmt().setString(1, user);
				rp.getPsmt().setString(2, pass);
				loginTokenRs = rp.executePreparedStatement();
				if (loginTokenRs.isBeforeFirst()) {
					loginTokenRs.next();
					if (loginTokenRs.first()) {
						String token = UUID.randomUUID().toString();
						loginTokenRs.updateString(1, token);
						loginTokenRs.updateRow();
						return token;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					loginTokenRs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
	}
}
