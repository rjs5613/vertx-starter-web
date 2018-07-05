package com.rajesh.app.router;

import com.rajesh.lib.annotation.RouteMapping;
import com.rajesh.lib.annotation.SubRouter;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;


@SubRouter(path="/employees")
public class EmployeeRouters {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRouters.class);

	@RouteMapping(httpMethod = HttpMethod.GET, path = "/")
	public void getEmployees(RoutingContext routingContext) {
		LOGGER.info("Getting Employees");
		routingContext.response().end("Getting Employees");
	}

	@RouteMapping(httpMethod = HttpMethod.GET, path = "/:id")
	public void getEmployeeById(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Getting Employee By Id " + pathParam);
		context.response().end("Getting Employee By Id " + pathParam);
	}

	@RouteMapping(httpMethod = HttpMethod.POST, path = "/")
	public void createEmployee(RoutingContext context) {
		LOGGER.info("Creating Employee");
		context.response().end("Creating Employee");
	}

	@RouteMapping(httpMethod = HttpMethod.PUT, path = "/:id")
	public void updateEmployee(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Updating Employee By Id " + pathParam);
		context.response().end("Updating Employee By Id " + pathParam);
	}

	@RouteMapping(httpMethod = HttpMethod.DELETE, path = "/:id")
	public void deleteEmployeeById(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Deleting Employee By Id " + pathParam);
		context.response().end("Deleting Employee By Id " + pathParam);
	}

}
