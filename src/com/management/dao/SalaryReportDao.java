package com.management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.core.Response;

import com.management.dbconfig.DbConfig;
import com.management.exceptions.AppException;
import com.management.model.NetSalary;
import com.management.repo.Repository;

public class SalaryReportDao {
	DbConfig config= DbConfig.getInstance();
	String querry=null;
	Repository er = new Repository(config);	
	public HashMap<Integer, NetSalary> getSalaries() {
		System.out.println("Inside Dao");
		HashMap<Integer, NetSalary> h_nsal =null;
		NetSalary ns=null;
		querry = "select * from salary";
		ResultSet rs=null;

			try {
				rs= er.executeQuerryRep(querry);

				if (rs.isBeforeFirst()) {
					h_nsal = new HashMap<>();
					ns = new NetSalary();
				} else {
					ns = null;
					h_nsal=null;
				}

				while (rs.next()) {

					ns.setSal_emp_id(rs.getInt(1));
					ns.setBasicsal(rs.getDouble(2));

					ns.setHra(rs.getDouble(3));
					ns.setDa(rs.getDouble(4));
					ns.setCca(rs.getDouble(5));
					ns.setTa(rs.getDouble(6));
					ns.setMed(rs.getDouble(7));

					ns.setPf(rs.getDouble(8));
					ns.setPt(rs.getDouble(9));
					ns.setLoan(rs.getDouble(10));
					ns.setIns(rs.getDouble(11));

					h_nsal.put(ns.getSal_emp_id(), ns);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					config.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return h_nsal;
	}

	public NetSalary getSalary(int emp_id) {
		querry="select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=" + String.valueOf(emp_id);
		NetSalary ns=null;
		ResultSet rs=null;
			try {
				rs= er.executeQuerryRep(querry);
				if (rs.isBeforeFirst()) {
					ns = new NetSalary();
				} else {
					ns = null;
				}

				while (rs.next()) {

					ns.setSal_emp_id(rs.getInt(1));
					ns.setBasicsal(rs.getDouble(2));

					ns.setHra(rs.getDouble(3));
					ns.setDa(rs.getDouble(4));
					ns.setCca(rs.getDouble(5));
					ns.setTa(rs.getDouble(6));
					ns.setMed(rs.getDouble(7));

					ns.setPf(rs.getDouble(8));
					ns.setPt(rs.getDouble(9));
					ns.setLoan(rs.getDouble(10));
					ns.setIns(rs.getDouble(11));
					
					System.out.println(ns.toString());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					config.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return ns;
	}

	public NetSalary postSalary(int emp_id, NetSalary ns) throws AppException {
			System.out.println(emp_id);
			int ra=0;
			querry = "insert into salary values(";
//			psmt.setInt(1,
			querry=querry+String.valueOf(emp_id); // emp_id=sal_emp_id
//			psmt.setDouble(2,
			querry=querry+", "+String.valueOf(ns.getBasicsal());
//			psmt.setDouble(3,
			querry=querry+", "+String.valueOf(ns.getHra());
//			psmt.setDouble(4,
			querry=querry+", "+String.valueOf(ns.getDa());
//			psmt.setDouble(5,
			querry=querry+", "+String.valueOf(ns.getCca());
//			psmt.setDouble(6,
			querry=querry+", "+String.valueOf(ns.getTa());
//			psmt.setDouble(7,
			querry=querry+", "+String.valueOf(ns.getMed());
//			psmt.setDouble(8,
			querry=querry+", "+String.valueOf(ns.getPf());
//			psmt.setDouble(9,
			querry=querry+", "+String.valueOf(ns.getPt());
//			psmt.setDouble(10,
			querry=querry+", "+String.valueOf(ns.getLoan());
//			psmt.setDouble(11,
			querry=querry+", "+String.valueOf(ns.getIns())+")";
			System.out.println(querry);
			StringBuffer sbq=new StringBuffer("select Employee_Id from employees where Employee_Id=");
			sbq.append(emp_id);
			ResultSet rs=null;
			ResultSet rsid=null;

			NetSalary net_sal=null;
			try {
				rsid=er.executeQuerryRep(sbq.toString());
				if(rsid.getInt(1)!=emp_id) {
					throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
							"The employee with id " + emp_id + " for which the Salary was requested to be entered was not found in the employee database");	

				}
				
				ra = er.executeUpdateRep(querry);
				
				if (ra==0) {
					return null;
				}
					querry="select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=" + String.valueOf(emp_id);
					rs=er.executeQuerryRep(querry);
					if (rs.isBeforeFirst()) {
						net_sal = new NetSalary();
					} else {
						net_sal = null;
					}

					while (rs.next()) {
					
						net_sal.setSal_emp_id(rs.getInt(1));
						net_sal.setBasicsal(rs.getDouble(2));
						net_sal.setHra(rs.getDouble(3));
						net_sal.setDa(rs.getDouble(4));
						net_sal.setCca(rs.getDouble(5));
						net_sal.setTa(rs.getDouble(6));
					net_sal.setMed(rs.getDouble(7));
					net_sal.setPf(rs.getDouble(8));
					net_sal.setPt(rs.getDouble(9));
					net_sal.setLoan(rs.getDouble(10));
					net_sal.setIns(rs.getDouble(11));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					config.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return net_sal;
	}

	public NetSalary putSalary(int emp_id, NetSalary ns) {
			StringBuffer cmd = new StringBuffer("set");
			
			if (ns.getSal_emp_id() != 0) {
				cmd = cmd.append(" Employee_Id =" + "'" + ns.getSal_emp_id() + "',");
			}
			if (ns.getBasicsal() != 0) {
				cmd = cmd.append(" Basic=" + "'" + ns.getBasicsal() + "',");
			}
			if (ns.getHra() != 0) {
				cmd = cmd.append(" HRA='" + ns.getHra() + "',");
			}
			if (ns.getDa() != 0) {
				cmd = cmd.append(" DA='" + ns.getDa() + "',");

			}

			if (ns.getCca() != 0) {
				cmd = cmd.append(" CCA='" + ns.getCca() + "',");

			}

			if (ns.getTa() != 0) {
				cmd = cmd.append(" TA='" + ns.getTa() + "',");

			}

			if (ns.getMed() != 0) {
				cmd = cmd.append(" Medical='" + ns.getMed() + "',");

			}

			if (ns.getPf() != 0) {
				cmd = cmd.append(" PF='" + ns.getPf() + "',");

			}

			if (ns.getPt() != 0) {
				cmd = cmd.append(" PT='" + ns.getPt() + "',");

			}

			if (ns.getLoan() != 0) {
				cmd = cmd.append(" Loan='" + ns.getLoan() + "',");

			}

			if (ns.getIns() == 0)
			{
				cmd.deleteCharAt(cmd.length() - 1);

			} else {
				cmd = cmd.append(" Insurance='" + ns.getIns() + "',");

			}
			int ra=0;
			ResultSet rs=null;
			NetSalary net_sal=null;
			try {
				querry="update salary " + cmd + " where Employee_Id=" + String.valueOf(emp_id);
				ra=er.executeUpdateRep(querry);
				if (ra==0) {
					return null;
				}
				querry="select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=" + String.valueOf(emp_id);

				rs=er.executeQuerryRep(querry);
				if (rs.isBeforeFirst()) {
					net_sal = new NetSalary();
				} else {
					net_sal = null;
				}

				while (rs.next()) {
					
					net_sal.setSal_emp_id(rs.getInt(1));
					net_sal.setBasicsal(rs.getDouble(2));
					net_sal.setHra(rs.getDouble(3));
					net_sal.setDa(rs.getDouble(4));
					net_sal.setCca(rs.getDouble(5));
					net_sal.setTa(rs.getDouble(6));
					net_sal.setMed(rs.getDouble(7));
					net_sal.setPf(rs.getDouble(8));
					net_sal.setPt(rs.getDouble(9));
					net_sal.setLoan(rs.getDouble(10));
					net_sal.setIns(rs.getDouble(11));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 finally {
					try {
						rs.close();
						config.closeConnection();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		return net_sal;
	}

	public int deleteSalary(int emp_id) {
		querry="DELETE from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id="+String.valueOf(emp_id);
		int ra=0;
		try {
			ra=er.executeUpdateRep(querry);
		}
			 finally {
					config.closeConnection();
			}
		return ra;
	}

}
