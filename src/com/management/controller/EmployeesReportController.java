package com.management.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.management.exceptions.AppException;
import com.management.model.Employee;
import com.management.model.Manager;
import com.management.services.EmployeeReportService;

@Path("/employees")
public class EmployeesReportController {
	EmployeeReportService emp_ser = new EmployeeReportService();
	@Context UriInfo uriinfo;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEmployees() throws AppException {
		System.out.println("inside annotation");
		return Response.ok(emp_ser.getEmployeesService()).location(uriinfo.getAbsolutePath()).build();
	}
	
	@GET
	@Path("/{emp-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response findEmployee(@PathParam ("emp-id") int emp_id) throws AppException {
		Manager c_emp=null;
		System.out.println(emp_id);
			c_emp=emp_ser.getEmployeeService(emp_id);
			System.out.println(c_emp.getReportingEmployees().size());
			if(c_emp.getReportingEmployees().isEmpty()) {
				Employee emp = new Employee(c_emp);
				System.out.println(emp.toString());
				return Response.ok(emp).location(uriinfo.getAbsolutePath()).build();
			}
		return Response.ok(c_emp).location(uriinfo.getAbsolutePath()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response enterEmployee(Manager emp) throws AppException {
		Manager c_emp=null;
		c_emp=emp_ser.postEmployeeService(emp);
		String cemp_id= String.valueOf(c_emp.getEmp_id());
		return Response.created(uriinfo.getAbsolutePathBuilder().path(cemp_id).build()).entity(c_emp).build();
	}
	
	@PUT
	@Path("/{emp-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterEmployee(@PathParam ("emp-id") int id, Manager emp) throws AppException {
		Manager c_emp=null;
		c_emp=emp_ser.putEmployeeService(id,emp);
		return Response.ok(c_emp).location(uriinfo.getAbsolutePath()).build(); //service 
	}
	
	@DELETE
	@Path("/{emp-id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeEmployee(@PathParam ("emp-id") int emp_id) throws AppException {
		emp_ser.deleteEmployeeService(emp_id);
		return Response.noContent().location(uriinfo.getAbsolutePath()).build();
	}
	
	@Path("/salary")
	public SalaryReportController getSalarySubResource() throws AppException {
		System.out.println("Inside redirect");
		return new SalaryReportController();
	}
	
	@Path("/{emp-id}/leaves")
	public LeaveReportController getLeaveSubResource() throws AppException {
		System.out.println("Inside leave redirect");
		return new LeaveReportController();
	}


}
