package com.management.controller;

import java.util.HashMap;
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
import com.management.model.NetSalary;
import com.management.services.SalaryReportService;

@Path("/")
public class SalaryReportController {
	
	NetSalary c_ns;
	String c_nsid;

	SalaryReportService srs = new SalaryReportService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSalaries(@Context UriInfo uriinfo) throws AppException {
		System.out.println("Reached Parent Get of Salary");
		HashMap<Integer, NetSalary> hc_ns = null;
			hc_ns = srs.findSalaries();
		return Response.ok(hc_ns).location(uriinfo.getAbsolutePath()).build();


	}

	@GET
	@Path("/{emp-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findSalary(@PathParam("emp-id") int emp_id, @Context UriInfo uriinfo) throws AppException {

		System.out.println("Reached withing GET Salary of Employee :" + emp_id);
			c_ns= srs.findSalary(emp_id);
		return Response.ok(c_ns).location(uriinfo.getAbsolutePath()).build();
	}

	@POST
	@Path("/{emp-id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response enterSalary(@PathParam("emp-id") int emp_id, NetSalary ns, @Context UriInfo uriinfo) throws AppException {

		System.out.println("Reached inside Post of Salary of Employee :" + emp_id);
			c_ns= srs.enterSalary(emp_id, ns);
		String cemp_id= String.valueOf(c_ns.getSal_emp_id());
		return Response.created(uriinfo.getAbsolutePathBuilder().path(cemp_id).build()).entity(c_ns).build();
	}

	@PUT
	@Path("/{emp-id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response alterSalary(@PathParam("emp-id") int emp_id, NetSalary ns, @Context UriInfo uriinfo) throws AppException {

		System.out.println("Reached inside PUT of Salary of Employee :" + emp_id);
			c_ns= srs.alterSalary(emp_id, ns);
		return Response.ok(c_ns).location(uriinfo.getAbsolutePath()).build(); //service 
	}

	@DELETE
	@Path("/{emp-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeSalary(@PathParam("emp-id") int emp_id, @Context UriInfo uriinfo) throws AppException {

		System.out.println("Reached inside DELETE of Salary of Employee " + emp_id);
		srs.removeSalary(emp_id);
		return Response.noContent().location(uriinfo.getAbsolutePath()).build();
	}

}