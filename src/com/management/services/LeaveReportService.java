package com.management.services;

import java.util.HashMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.management.dao.LeaveReportDao;
import com.management.exceptions.AppException;
import com.management.model.Leave;

public class LeaveReportService {
	LeaveReportDao lrd = new LeaveReportDao();
	ResponseBuilder respo;

	
	public  HashMap<Integer,Leave> getLeavesService(int emp_id) throws AppException {
		System.out.println("inside leave Serv");
		HashMap<Integer,Leave> Leaves= lrd.getLeavesDao(emp_id);
		if (Leaves==null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Leaves of employee you requested for employee with id " + emp_id + " was not found in the Leaves database");	
		}
		else return Leaves;
	}
	
	
	public Leave getLeaveService(int emp_id, int leave_id) throws AppException {
		Leave leave=lrd.getLeaveDao(emp_id,leave_id);;
		if (leave==null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Leaves of employee you requested for employee with id " + emp_id + " was not found in the Leaves database");	
		}
		else return leave;
	}

	
	public Leave postLeaveService(int emp_id, Leave l) throws AppException {
		Leave leave=lrd.postLeaveDao(emp_id,l);
		if (leave==null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Employee with id " + emp_id + " for which the Leave is being entered was not found in the Employee database");	
		}
		else return leave;
	}

	
	public Leave putLeaveService(int emp_id, int leave_id, Leave l) throws AppException {
		Leave leave=lrd.putLeaveDao(emp_id, leave_id, l);
		if (leave==null) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Employee with id " + emp_id + " of which the Leave was requested to be altered was not found in the Employee database");	
		}
		else return leave;
	}

	
	public boolean deleteLeaveService(int emp_id, int leave_id) throws AppException {
		boolean ra=lrd.deleteLeaveDao(emp_id,leave_id);

		if (!ra) {
			throw new AppException(Response.Status.NOT_FOUND.getStatusCode(),
					"The Leave of employee with id " + emp_id + " for which Deletion was requested was not found in the database");	
		}
		else return ra;
	}

}
