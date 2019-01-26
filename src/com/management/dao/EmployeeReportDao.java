package com.management.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import com.management.dbconfig.DbConfig;
import com.management.model.Employee;
import com.management.model.Manager;
import com.management.repo.Repository;

public class EmployeeReportDao {

	private DbConfig config = DbConfig.getInstance();
	private Repository repo = new Repository(config);
	private int ra = 0;

	// Return All Employees
	public HashMap<Integer, Manager> getEmployeesDao() {
		System.out.println("inside All Emp Dao ");
		HashMap<Integer, Manager> map_employee = null;
		String querry;
		ResultSet rs = null;
		querry = "select * from employees";
		try {
			rs = repo.executeQuerryRep(querry);
			if (rs.isBeforeFirst()) {
				map_employee = new HashMap<>();
			} else {
				map_employee = null;
			}

			while (rs.next()) {
				Manager employee = new Manager();
				employee.setEmp_id(rs.getInt(1));
				employee.setManager(rs.getInt(2));
				employee.setDept_id(rs.getString(3));
				employee.setFirst_name(rs.getString(4));
				employee.setLast_name(rs.getString(5));
				employee.setDob(rs.getDate(6).toLocalDate());
				employee.setGender(rs.getString(7));
				employee.setCountry(rs.getString(8));
				employee.setState(rs.getString(9));
				employee.setCity(rs.getString(10));
				employee.setAddress(rs.getString(11));
				employee.setPincode(rs.getString(12));
				employee.setEmail(rs.getString(13));
				employee.setMobile_no(rs.getString(14));
				employee.setDoj(rs.getDate(15).toLocalDate());
				repo.SetQuerry("select * from employees where Manager=?");
				repo.getPsmt().setInt(1, employee.getEmp_id());
				ResultSet repEmpRs = repo.executePreparedStatement();
				while (repEmpRs.next()) {
					Employee repEmp = new Employee();

					repEmp.setEmp_id(repEmpRs.getInt(1));
/*					
					repEmp.setManager(repEmpRs.getInt(2));;
					repEmp.setDept_id(repEmpRs.getString(3));
					repEmp.setFirst_name(repEmpRs.getString(4));
					repEmp.setLast_name(repEmpRs.getString(5));
					repEmp.setDob(repEmpRs.getDate(6).toLocalDate());
					repEmp.setGender(repEmpRs.getString(7));
					repEmp.setCountry(repEmpRs.getString(8));
					repEmp.setState(repEmpRs.getString(9));
					repEmp.setCity(repEmpRs.getString(10));
					repEmp.setAddress(repEmpRs.getString(11));
					repEmp.setPincode(repEmpRs.getString(12));
					repEmp.setEmail(repEmpRs.getString(13));
					repEmp.setMobile_no(repEmpRs.getString(14));
					repEmp.setDoj(repEmpRs.getDate(15).toLocalDate());
*/					
					employee.setReportingEmployee(repEmp);
				}
				repEmpRs.close();
				map_employee.put(employee.getEmp_id(), employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				config.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map_employee;
	}

	// Search for a particular employee
	public Manager getEmployeeDao(int id) {
		if(id == 0)
		{
			return null;
		}

		System.out.println("in dao" + id);
		StringBuilder querry = new StringBuilder("select * from employees where Employee_Id=");
		querry.append(id);
		Manager manager = null;
		Employee repEmp = null;
		ResultSet rs = null;
		ResultSet mng_rs = null;
		try {
			mng_rs = repo.executeQuerryRep(querry.toString());
			if (mng_rs.isBeforeFirst()) {
				manager = new Manager();
			} else {
				manager = null;
				repEmp = null;
			}
			while (mng_rs.next()) {
				manager.setEmp_id(mng_rs.getInt(1));
				manager.setManager(mng_rs.getInt(2));
				manager.setDept_id(mng_rs.getString(3));
				manager.setFirst_name(mng_rs.getString(4));
				manager.setLast_name(mng_rs.getString(5));
				manager.setDob(mng_rs.getDate(6).toLocalDate());
				manager.setGender(mng_rs.getString(7));
				manager.setCountry(mng_rs.getString(8));
				manager.setState(mng_rs.getString(9));
				manager.setCity(mng_rs.getString(10));
				manager.setAddress(mng_rs.getString(11));
				manager.setPincode(mng_rs.getString(12));
				manager.setEmail(mng_rs.getString(13));
				manager.setMobile_no(mng_rs.getString(14));
				manager.setDoj(mng_rs.getDate(15).toLocalDate());

			querry = new StringBuilder("select * from employees where Manager=").append(id);
			repo.SetQuerry("select * from employees where Manager=?");
			repo.getPsmt().setInt(1, manager.getEmp_id());
			ResultSet repEmpRs = repo.executePreparedStatement();

//			rs = repo.executeQuerryRep(querry.toString());
			while (repEmpRs.next()) {
				repEmp = new Employee();
				
				repEmp.setEmp_id(repEmpRs.getInt(1));
				repEmp.setManager(repEmpRs.getInt(2));;
				repEmp.setDept_id(repEmpRs.getString(3));
				repEmp.setFirst_name(repEmpRs.getString(4));
				repEmp.setLast_name(repEmpRs.getString(5));
				repEmp.setDob(repEmpRs.getDate(6).toLocalDate());
				repEmp.setGender(repEmpRs.getString(7));
				repEmp.setCountry(repEmpRs.getString(8));
				repEmp.setState(repEmpRs.getString(9));
				repEmp.setCity(repEmpRs.getString(10));
				repEmp.setAddress(repEmpRs.getString(11));
				repEmp.setPincode(repEmpRs.getString(12));
				repEmp.setEmail(repEmpRs.getString(13));
				repEmp.setMobile_no(repEmpRs.getString(14));
				repEmp.setDoj(repEmpRs.getDate(15).toLocalDate());

/*				
				repEmp.setEmp_id(rs.getInt(1));
				repEmp.setManager(rs.getInt(2));
				repEmp.setDept_id(rs.getString(3));
				repEmp.setFirst_name(rs.getString(4));
				repEmp.setLast_name(rs.getString(5));
				repEmp.setDob(rs.getDate(6).toLocalDate());
				repEmp.setGender(rs.getString(7));
				repEmp.setCountry(rs.getString(8));
				repEmp.setState(rs.getString(9));
				repEmp.setCity(rs.getString(10));
				repEmp.setAddress(rs.getString(11));
				repEmp.setPincode(rs.getString(12));
				repEmp.setEmail(rs.getString(13));
				repEmp.setMobile_no(rs.getString(14));
				repEmp.setDoj(rs.getDate(15).toLocalDate());
*/
				manager.setReportingEmployee(repEmp);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) {
				rs.close();
				mng_rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return manager;
	}

	// Enter a New employee ( new recruit)
	public Manager postEmployeeDao(Manager employee) {
		if(employee==null)
		{
			return null;
		}

		Manager employeeRet = new Manager();
		ResultSet rs = null;
		int ra = 0;
		StringBuilder sBq = new StringBuilder("insert into employees values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		try {
			repo.SetQuerry(sBq.toString());
			repo.getPsmt().setInt(1, employee.getEmp_id());
			repo.getPsmt().setInt(2, employee.getManager());
			repo.getPsmt().setString(3, employee.getDept_id());
			repo.getPsmt().setString(4, employee.getFirst_name());
			repo.getPsmt().setString(5, employee.getLast_name());
			repo.getPsmt().setDate(6, java.sql.Date.valueOf(employee.getDob()));
			repo.getPsmt().setString(7, employee.getGender());
			repo.getPsmt().setString(8, employee.getCountry());
			repo.getPsmt().setString(9, employee.getState());
			repo.getPsmt().setString(10, employee.getCity());
			repo.getPsmt().setString(11, employee.getAddress());
			repo.getPsmt().setString(12, employee.getPincode());
			repo.getPsmt().setString(13, employee.getEmail());
			repo.getPsmt().setString(14, employee.getMobile_no());
			repo.getPsmt().setDate(15, java.sql.Date.valueOf(employee.getDoj()));
			ra = repo.updatePreparedStatement();

			if (ra != 0) {
				employeeRet = employee;
				sBq = new StringBuilder(
						"select Employee_Id from employees where First_Name =? and Last_Name =? and DOB = ?");
				repo.SetQuerry(sBq.toString());
				repo.getPsmt().setString(1, employee.getFirst_name());
				repo.getPsmt().setString(2, employee.getLast_name());
				repo.getPsmt().setDate(3, java.sql.Date.valueOf(employee.getDob()));
				rs = repo.executePreparedStatement();
				while (rs.next()) {
					employeeRet.setEmp_id(rs.getInt(1));
				}
				for (Employee a : employee.getReportingEmployees().values()) {
					sBq = new StringBuilder("update employees set Manager =").append(employeeRet.getEmp_id())
							.append(" where Employee_Id=").append(a.getEmp_id());
					repo.executeUpdateRep(sBq.toString());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employeeRet;
	}

	// Altering Employee Details
	public Manager putEmployeeDao(int id, Manager employee_par) {
		if(id == 0 || employee_par==null)
		{
			return null;
		}
		StringBuilder querry = new StringBuilder("update employees set");
		Manager employeeRet = new Manager();
		ResultSet rs = null;

		if (employee_par.getEmp_id() != 0) {
			querry.append(" Employee_Id =").append(employee_par.getEmp_id()).append(",");
		}
		if (employee_par.getManager() != 0) {
			querry.append(" Manager =").append(employee_par.getManager()).append(",");
		}
		if (employee_par.getDept_id() != null) {
			querry.append(" Department_Id='").append(employee_par.getDept_id()).append("',");
		}
		if (employee_par.getFirst_name() != null) {
			querry.append(" First_Name='").append(employee_par.getFirst_name()).append("',");
		}
		if (employee_par.getLast_name() != null) {
			querry.append(" Last_Name='").append(employee_par.getLast_name()).append("',");
		}
		if (employee_par.getDob() != null) {
			querry.append(" DOB='").append(java.sql.Date.valueOf(employee_par.getDob())).append("',");
		}
		if (employee_par.getGender() != null) {
			querry.append(" Gender='").append(employee_par.getGender()).append("',");
		}
		if (employee_par.getCountry() != null) {
			querry.append(" Country='").append(employee_par.getCountry()).append("',");
		}
		if (employee_par.getState() != null) {
			querry.append(" State='").append(employee_par.getState()).append("',");
		}
		if (employee_par.getCity() != null) {
			querry.append(" City='").append(employee_par.getCity()).append("',");
		}
		if (employee_par.getAddress() != null) {
			querry.append(" Address='").append(employee_par.getAddress()).append("',");
		}
		if (employee_par.getPincode() != null) {
			querry.append(" Pincode='").append(employee_par.getPincode()).append("',");
		}
		if (employee_par.getEmail() != null) {
			querry.append(" Email='").append(employee_par.getEmail()).append("',");
		}
		if (employee_par.getMobile_no() != null) {
			querry.append(" Mobile_No='").append(employee_par.getMobile_no()).append("',");
		}
		if (employee_par.getDoj() == null) {
			querry.deleteCharAt(querry.length() - 1);
		} else {
			querry.append(" DOJ='").append(java.sql.Date.valueOf(employee_par.getDoj())).append("'");
		}
		if(querry.length()==19) {
			ra=1;
		}
		else {
			querry.append(" where Employee_Id=").append(id);
			ra = repo.executeUpdateRep(querry.toString());
		}
		querry = new StringBuilder("select * from employees where Employee_Id=").append(id);
		System.out.println(querry);
		try {
			rs = repo.executeQuerryRep(querry.toString());
			while (rs.next()) {
				employeeRet.setEmp_id(rs.getInt(1));
				employeeRet.setManager(rs.getInt(2));
				employeeRet.setDept_id(rs.getString(3));
				employeeRet.setFirst_name(rs.getString(4));
				employeeRet.setLast_name(rs.getString(5));
				employeeRet.setDob(rs.getDate(6).toLocalDate());
				employeeRet.setGender(rs.getString(7));
				employeeRet.setCountry(rs.getString(8));
				employeeRet.setState(rs.getString(9));
				employeeRet.setCity(rs.getString(10));
				employeeRet.setAddress(rs.getString(11));
				employeeRet.setPincode(rs.getString(12));
				employeeRet.setEmail(rs.getString(13));
				employeeRet.setMobile_no(rs.getString(14));
				employeeRet.setDoj(rs.getDate(15).toLocalDate());
			}
			for (Employee a : employee_par.getReportingEmployees().values()) {
				querry = new StringBuilder("update employees set Manager =").append(id)
						.append(" where Employee_Id=").append(a.getEmp_id());
				repo.executeUpdateRep(querry.toString());
			}
			repo.SetQuerry("select * from employees where Manager=?");
			repo.getPsmt().setInt(1, id);
			ResultSet repEmpRs = repo.executePreparedStatement();
			while (repEmpRs.next()) {
				Employee repEmp = new Employee();
				repEmp.setEmp_id(repEmpRs.getInt(1));
				employeeRet.setReportingEmployee(repEmp);
			}
			repEmpRs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (ra == 0) {
			return null;
		} else
			return employeeRet;
	}

	public int deleteEmployeeDao(int id) {
		if(id == 0)
		{
			return 0;
		}

		StringBuilder querry;
		querry = new StringBuilder("delete from employees where Employee_Id=").append(id);
		ra = repo.executeUpdateRep(querry.toString());
		if (ra != 0) {
			try {
				repo.SetQuerry("select Employee_Id from employees where Manager=?");
				repo.getPsmt().setInt(1, id);
				ResultSet repEmpRs = repo.executePreparedStatement();
				while (repEmpRs.next()) {
					querry = new StringBuilder("update employees set Manager =0").append(" where Employee_Id=")
							.append(repEmpRs.getInt(1));
					repo.executeUpdateRep(querry.toString());
				}
				repEmpRs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ra;
	}
}
