package com.management.services;

import java.util.HashMap;
import javax.ws.rs.core.Response;
import com.management.dao.SalaryReportDao;
import com.management.exceptions.AppException;
import com.management.model.NetSalary;

public class SalaryReportService {
	SalaryReportDao srd = new SalaryReportDao();
	
	
	public HashMap<Integer, NetSalary> findSalaries() throws AppException {
		HashMap<Integer, NetSalary> map_ns;
		map_ns =srd.getSalaries();
		if (map_ns==null) {
			System.out.println("Null HashMap returned");
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Salary of employees you requested were not found in the database");	
		}
		else return map_ns;
	}

	public NetSalary findSalary(int sal_id) throws AppException {
		NetSalary ns;
		ns=srd.getSalary(sal_id);
		if (ns==null) {
			System.out.println("Null NetSalary returned");
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Salary of employee requested for employee with id " + sal_id + " was not found in the database");	
		}
		else return ns;
	}
	
	public NetSalary enterSalary(int emp_id, NetSalary netsalary) throws AppException {
		NetSalary ns;
		ns=srd.postSalary(emp_id,netsalary);
		if (ns==null) {
			System.out.println("Null NetSalary Returned");
			throw new AppException(Response.Status.BAD_REQUEST.getStatusCode(),
					"The Salary of employee with id " + emp_id + " which was requested to be entered is already present in the database");	
		}
		else return ns;
	}
	
	public NetSalary alterSalary(int emp_id, NetSalary netsalary) throws AppException {
		NetSalary ns;
		ns=srd.putSalary(emp_id,netsalary);
		if (ns==null) {
			System.out.println("Null NetSalary Returned");
			throw new AppException(Response.Status.NOT_MODIFIED.getStatusCode(),
					"The employee with id " + emp_id + " for which the Salary was requested to be altered was not found in the database");	
		}
		else return ns;		
	}

	public int removeSalary(int emp_id) throws AppException {
		int ra=srd.deleteSalary(emp_id);
		if (ra==0) {
			System.out.println("Rows affected 0");
			throw new AppException(Response.Status.NOT_MODIFIED.getStatusCode(),
					"The Salary of employee with id " + emp_id + " for which Remaval was requested was not found in the database");	
		}
		else return ra;
	}
	
}
