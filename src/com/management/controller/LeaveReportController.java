package com.management.controller;

import com.management.exceptions.AppException;
import com.management.model.Leave;
import com.management.services.LeaveReportService;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

public class LeaveReportController {

	LeaveReportService lrs = new LeaveReportService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLeaves(@PathParam ("emp-id") int emp_id, @Context UriInfo uriinfol)	throws AppException {
		System.out.println("Reached Inside GET of Leaves");
		ArrayList<Leave> ret;
		ret = (ArrayList<Leave>)lrs.getLeavesService(emp_id).values();
		return Response.ok(ret).location(uriinfol.getAbsolutePath()).build();

	}
	
	@GET
	@Path("/{l-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findLeave(@PathParam ("emp-id") int emp_id, @PathParam ("l-id") int leave_id, @Context UriInfo uriinfol) throws AppException {
		Leave l_ret=null;
		System.out.println("Reached Inside GET of Leaves "+leave_id+" of employee :"+emp_id+"   "+uriinfol.getPath());
		l_ret= lrs.getLeaveService(emp_id,leave_id);
		ResponseBuilder response=Response.ok(l_ret);
		return response.contentLocation(uriinfol.getAbsolutePath()).build(); 
		
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response enterLeave(@PathParam ("emp-id") int emp_id, Leave l, @Context UriInfo uriinfol) throws AppException {
		System.out.println("Reached Inside POST of Leaves of Employee :"+emp_id);
		Leave l_ret=null;
		String error = "";
		int l_id;
		try {
			l_ret = lrs.postLeaveService(emp_id,l);
			l_id=l_ret.getLeave_id();
			
			System.out.println("Found Leave id :"+l_id);
			ResponseBuilder rb=Response.ok(l_ret).location(uriinfol.getAbsolutePathBuilder().path(String.valueOf(l_id)).build());
			return rb.build();

		} catch (Exception e) {
			error = error.concat(e.getMessage());
			System.out.println(error);
			return Response.serverError().entity(error).build();
		}
		
	}

	@PUT
	@Path("/{l-id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response findLeaves(@PathParam ("emp-id") int emp_id, @PathParam ("l-id") int leave_id, Leave leave, @Context UriInfo uriinfol) throws AppException {
		System.out.println("Reached Inside PUT of Leaves "+leave_id+" of Employee "+emp_id);
		Leave l_ret=null;
		try {
			l_ret= lrs.putLeaveService(emp_id,leave_id,leave);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.ok(l_ret).location(uriinfol.getAbsolutePath()).build();
		
	}

	@DELETE
	@Path("/{l-id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeLeaves(@PathParam ("emp-id") int emp_id, @PathParam ("l-id") int leave_id, @Context UriInfo uriinfol) throws AppException {
		System.out.println("Reached Inside DELETE of Leaves "+leave_id+" of Employee "+emp_id);
		boolean ra=false;
			ra = lrs.deleteLeaveService(emp_id,leave_id);
		if(ra) {
		return Response.noContent().location(uriinfol.getAbsolutePath()).build();
		}else
		return Response.notModified().build();		
	}

}
