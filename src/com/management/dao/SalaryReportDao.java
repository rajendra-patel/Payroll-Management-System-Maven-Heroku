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
	DbConfig config = DbConfig.getInstance();
	StringBuilder querry = null;
	Repository repo = new Repository(config);

	public HashMap<Integer, NetSalary> getSalaries() {
		System.out.println("Within GET Salary Dao");
		HashMap<Integer, NetSalary> salaries = null;
		NetSalary salary = null;
		querry = new StringBuilder("select * from salary");

		try (ResultSet rs = repo.executeQuerryRep(querry.toString())) {
			if (rs.isBeforeFirst()) {
				salaries = new HashMap<>();
				salary = new NetSalary();
			} else {
				salary = null;
				salaries = null;
				System.out.println("Exiting Salary Dao with no Records in DB");
				return null;
			}

			while (rs.next()) {
				System.out.println("Found at least one record in salary database");

				salary.setSal_emp_id(rs.getInt(1));
				salary.setBasicsal(rs.getDouble(2));
				salary.setHra(rs.getDouble(3));
				salary.setDa(rs.getDouble(4));
				salary.setCca(rs.getDouble(5));
				salary.setTa(rs.getDouble(6));
				salary.setMed(rs.getDouble(7));
				salary.setPf(rs.getDouble(8));
				salary.setPt(rs.getDouble(9));
				salary.setLoan(rs.getDouble(10));
				salary.setIns(rs.getDouble(11));

				salaries.put(salary.getSal_emp_id(), salary);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting Salary Dao successfully");
		config.closeConnection();
		return salaries;
	}

	public NetSalary getSalary(int emp_id) {
		querry = new StringBuilder("select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=")
				.append(String.valueOf(emp_id));
		NetSalary salary = null;
		try (ResultSet rs = repo.executeQuerryRep(querry.toString())) {
			if (rs.isBeforeFirst()) {
				salary = new NetSalary();
			} else {
				salary = null;
				System.out.println("Exiting Salary Dao with no record of employee with id "+emp_id+" in salary database");
			}

			while (rs.next()) {

				salary.setSal_emp_id(rs.getInt(1));
				salary.setBasicsal(rs.getDouble(2));

				salary.setHra(rs.getDouble(3));
				salary.setDa(rs.getDouble(4));
				salary.setCca(rs.getDouble(5));
				salary.setTa(rs.getDouble(6));
				salary.setMed(rs.getDouble(7));

				salary.setPf(rs.getDouble(8));
				salary.setPt(rs.getDouble(9));
				salary.setLoan(rs.getDouble(10));
				salary.setIns(rs.getDouble(11));

				System.out.println("Found record of employee's salary in database");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting Salary Dao successfully");
		config.closeConnection();
		return salary;
	}

	public NetSalary postSalary(int emp_id, NetSalary ns) throws AppException {
		System.out.println("Within POST salary dao with Emlpoyee id "+emp_id);
		querry = new StringBuilder("insert into salary values(");
//			psmt.setInt(1,
		querry.append(emp_id); // emp_id=sal_emp_id
//			psmt.setDouble(2,
		querry.append(", ").append(ns.getBasicsal());
//			psmt.setDouble(3,
		querry.append(", ").append(ns.getHra());
//			psmt.setDouble(4,
		querry.append(", ").append(ns.getDa());
//			psmt.setDouble(5,
		querry.append(", ").append(ns.getCca());
//			psmt.setDouble(6,
		querry.append(", ").append(ns.getTa());
//			psmt.setDouble(7,
		querry.append(", ").append(ns.getMed());
//			psmt.setDouble(8,
		querry.append(", ").append(ns.getPf());
//			psmt.setDouble(9,
		querry.append(", ").append(ns.getPt());
//			psmt.setDouble(10,
		querry.append(", ").append(ns.getLoan());
//			psmt.setDouble(11,
		querry.append(", ").append(ns.getIns()).append(")");
//		System.out.println(querry);
		NetSalary net_sal = null;
		try (ResultSet rsid = repo.executeQuerryRep("select Employee_Id from employees where Employee_Id="+emp_id)) {
			rsid.absolute(1);
			if (rsid.getInt(1) != emp_id) {
				throw new AppException(Response.Status.NOT_FOUND.getStatusCode(), "The employee with id " + emp_id
						+ " for which the Salary was requested to be entered was not found in the employee database");
			}
			
			int ra = repo.executeUpdateRep(querry.toString());
			if (ra == 0) {
				System.out.println("Exiting Salary Dao with error : Cannot Insert record in database");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			querry.delete(0, querry.length());
			querry.append("select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=")
					.append(emp_id);
			try (ResultSet rs = repo.executeQuerryRep(querry.toString())) {
			if (rs.isBeforeFirst()) {
				net_sal = new NetSalary();
			} else {
				net_sal = null;
				System.out.println("Exiting Salary Dao with error : No record found of employee in salary database");
				return null;
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
		System.out.println("Exiting POST Salary Dao successfully");
		config.closeConnection();
		return net_sal;
	}

	public NetSalary putSalary(int empId, NetSalary salary) {
		StringBuilder cmd = new StringBuilder("set");

		if (salary.getSal_emp_id() != 0) {
			cmd = cmd.append(" Employee_Id =").append(salary.getSal_emp_id()).append(",");
		}
		if (salary.getBasicsal() != 0) {
			cmd = cmd.append(" Basic=").append(salary.getBasicsal()).append(",");
		}
		if (salary.getHra() != 0) {
			cmd = cmd.append(" HRA=").append(salary.getHra()).append(",");
		}
		if (salary.getDa() != 0) {
			cmd = cmd.append(" DA=").append(salary.getDa()).append(",");
		}
		if (salary.getCca() != 0) {
			cmd = cmd.append(" CCA=").append(salary.getCca()).append(",");
		}
		if (salary.getTa() != 0) {
			cmd = cmd.append(" TA=").append(salary.getTa()).append(",");
		}
		if (salary.getMed() != 0) {
			cmd = cmd.append(" Medical=").append(salary.getMed()).append(",");
		}
		if (salary.getPf() != 0) {
			cmd = cmd.append(" PF=").append(salary.getPf()).append(",");
		}
		if (salary.getPt() != 0) {
			cmd = cmd.append(" PT=").append(salary.getPt()).append(",");
		}

		if (salary.getLoan() != 0) {
			cmd = cmd.append(" Loan=").append(salary.getLoan()).append(",");
		}

		if (salary.getIns() == 0) {
			cmd.deleteCharAt(cmd.length() - 1);

		} else {
			cmd = cmd.append(" Insurance=").append(salary.getIns()).append(",");
		}
		int ra = 0;
		NetSalary retSalary = null;
			querry = new StringBuilder("update salary ").append(cmd).append(" where Employee_Id=").append(empId);
			ra = repo.executeUpdateRep(querry.toString());
			if (ra == 0) {
				System.out.println("Exiting Salary Dao with Error : Could not alter salary");
				return null;
			}
			querry.delete(0, querry.length());
			querry.append("select * from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=")
					.append(empId);
			try (ResultSet rs = repo.executeQuerryRep(querry.toString())) {
			if (rs.isBeforeFirst()) {
				retSalary = new NetSalary();
			} else {
				retSalary = null;
				System.out.println("Exiting Salary Dao with error : No record found of employee in salary database");
				return null;
			}

			while (rs.next()) {

				retSalary.setSal_emp_id(rs.getInt(1));
				retSalary.setBasicsal(rs.getDouble(2));
				retSalary.setHra(rs.getDouble(3));
				retSalary.setDa(rs.getDouble(4));
				retSalary.setCca(rs.getDouble(5));
				retSalary.setTa(rs.getDouble(6));
				retSalary.setMed(rs.getDouble(7));
				retSalary.setPf(rs.getDouble(8));
				retSalary.setPt(rs.getDouble(9));
				retSalary.setLoan(rs.getDouble(10));
				retSalary.setIns(rs.getDouble(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Exiting PUT Salary Dao successfully");
		config.closeConnection();
		return retSalary;
	}

	public int deleteSalary(int emp_id) {
		querry = new StringBuilder("DELETE from salary s INNER join employees e on s.Employee_Id=e.Employee_Id where s.Employee_Id=")
				.append(emp_id);
		int ra = 0;
		ra = repo.executeUpdateRep(querry.toString());
		System.out.println("Exiting Delete Salary Dao "+((ra!=0)?"successfully":"with no records deleted"));
		config.closeConnection();
		return ra;
	}
}
