package com.management.services;

import com.management.dao.EmployeeReportDao;
import com.management.exceptions.AppException;
import java.util.HashMap;
import javax.ws.rs.core.Response;
import com.management.model.Manager;

public class EmployeeReportService {
	EmployeeReportDao erd = new EmployeeReportDao();

	public HashMap<Integer, Manager> getEmployeesService() throws AppException {
		HashMap<Integer, Manager> map_emp;
		map_emp = erd.getEmployeesDao();
		if(map_emp == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"There are no employees yet in the database");
		}
		else return map_emp;
	}

	public Manager getEmployeeService(int id) throws AppException {
		Manager employee;
		employee = erd.getEmployeeDao(id);
		if(employee == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The employee you requested with id " + id + " was not found in the database");
		}
		else return employee;
	}
	public Manager postEmployeeService(Manager emp) throws AppException {
		Manager employee;
		employee = erd.postEmployeeDao(emp);
		if(employee == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The requested post did not contain correct parameters");
		}
		else return employee;
	}
	
	public Manager putEmployeeService(int id, Manager emp) throws AppException {
		Manager employee;
		employee = erd.putEmployeeDao(id, emp);
		if(employee == null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The employee with id " + id + " which was requested to be altered was not found in the database");
		}
		else return employee;
	}
	
	public int deleteEmployeeService(int id) throws AppException {
		int ra=erd.deleteEmployeeDao(id);
		if(ra==0) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The employee with id " + id + " for which Deletion was requested not found in the database");
		}
		else return ra;
	}
}