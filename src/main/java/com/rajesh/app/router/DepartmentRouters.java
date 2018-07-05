package com.rajesh.app.router;

import com.rajesh.lib.annotation.RouteMapping;
import com.rajesh.lib.annotation.SubRouter;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;


@SubRouter(path="/departments")
public class DepartmentRouters {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRouters.class);

	@RouteMapping(httpMethod = HttpMethod.GET, path = "/")
	public void getDepartments(RoutingContext routingContext) {
		LOGGER.info("Getting Departments");
		routingContext.response().end("Getting Departments");
	}

	@RouteMapping(httpMethod = HttpMethod.GET, path = "/:id")
	public void getDepartmentById(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Getting Department By Id " + pathParam);
		context.response().end("Getting Department By Id " + pathParam);
	}

	@RouteMapping(httpMethod = HttpMethod.POST, path = "/")
	public void createDepartment(RoutingContext context) {
		LOGGER.info("Creating Department");
		context.response().end("Creating Department");
	}

	@RouteMapping(httpMethod = HttpMethod.PUT, path = "/:id")
	public void updateDepartment(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Updating Department By Id " + pathParam);
		context.response().end("Updating Department By Id " + pathParam);
	}

	@RouteMapping(httpMethod = HttpMethod.DELETE, path = "/:id")
	public void deleteDepartmentById(RoutingContext context) {
		String pathParam = context.pathParam("id");
		LOGGER.info("Deleting Department By Id " + pathParam);
		context.response().end("Deleting Department By Id " + pathParam);
	}
	

}
