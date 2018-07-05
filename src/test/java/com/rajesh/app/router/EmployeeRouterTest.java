package com.rajesh.app.router;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.rajesh.app.VertxPOCApp;

import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;

@RunWith(VertxUnitRunner.class)
public class EmployeeRouterTest {
	
	private static Vertx vertx;
	
	@BeforeClass
	public static void  setUp() {
		VertxPOCApp.main("");
		vertx = VertxPOCApp.getVertxInstance();
	}

	@Test
	public void testGetEmployees(TestContext context) {
		final Async async = context.async();
		vertx.createHttpClient().get("/employees").handler(response->{
			response.handler(buffer->{
				assertTrue(buffer.toString().contains("Getting Employee"));
				async.complete();
			});
		});
	}

	@Test
	public void testGetEmployeeById() {
		vertx.createHttpClient().get("/employees/1").handler(response->{
			response.handler(buffer->{
				assertTrue(buffer.toString().contains("Getting Employee"));
				assertTrue(buffer.toString().contains("by id"));
			});
		});
	}

	@Test
	public void testCreateEmployee() {
		vertx.createHttpClient().post("/employees").handler(response->{
			response.handler(buffer->{
				assertTrue(buffer.toString().contains("Creating Employee"));
			});
		});
	}

	@Test
	public void testUpdateEmployee() {
		vertx.createHttpClient().put("/employees/1").handler(response->{
			response.handler(buffer->{
				assertTrue(buffer.toString().contains("Updating Employee"));
			});
		});
	}

	@Test
	public void testDeleteEmployeeById() {
		vertx.createHttpClient().delete("/employees").handler(response->{
			response.handler(buffer->{
				assertTrue(buffer.toString().contains("Deleting Employee"));
			});
		});
	}

}
