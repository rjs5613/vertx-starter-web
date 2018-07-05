package com.rajesh.app;

import com.rajesh.lib.VertxApplication;
import com.rajesh.lib.annotation.VertxApp;

import io.vertx.core.Vertx;

@VertxApp(isWebApp=true)
public class VertxPOCApp {
	
	private static Vertx vertx;
	
	public static void main(String... args) {
		vertx = VertxApplication.run(VertxPOCApp.class, args);
	}
	
	public static Vertx getVertxInstance() {
		return vertx;
	}

}
