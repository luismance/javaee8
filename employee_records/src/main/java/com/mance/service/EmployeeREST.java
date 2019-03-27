package com.mance.service;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mance.dao.EmployeeDAO;

@Path("/employee")
public class EmployeeREST {

	@Inject
	private EmployeeDAO emd;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {

		return Response.ok(emd.list()).build();
	}
}
