package com.rajesh.lib;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Strings;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;
import com.rajesh.lib.annotation.DeployableVerticle;
import com.rajesh.lib.annotation.RouteMapping;
import com.rajesh.lib.annotation.SubRouter;
import com.rajesh.lib.annotation.VertxApp;

import io.vertx.core.Handler;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

/**
 * 
 * @author rrajeshkumar
 * 
 */
public class VertxApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(VertxApplication.class);

	/**
	 * Runs the Vertx Application
	 * 
	 * @param clazz
	 * @param args
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Vertx run(Class<?> clazz, String[] args) {
		Vertx vertx = Vertx.vertx();
		if (clazz.isAnnotationPresent(VertxApp.class)) {
			VertxApp annotation = (VertxApp) clazz.getAnnotation(VertxApp.class);
			String basePackage = annotation.basePackage();
			if (Strings.isNullOrEmpty(basePackage)) {
				basePackage = clazz.getPackage().getName();
			}
			boolean isWebApp = annotation.isWebApp();

			ClassLoader classLoader = clazz.getClassLoader();
			try {
				ClassPath classpath = ClassPath.from(classLoader);
				Router router = Router.router(vertx);
				for (ClassInfo classInfo : classpath.getTopLevelClassesRecursive(basePackage)) {
					Class currentClass = Class.forName(classInfo.getName());
					deployVerticle(vertx, currentClass);
					registerRouters(vertx, currentClass, router, isWebApp);
				}
				createHttpSerever(vertx, router, isWebApp);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return vertx;

	}

	private static void createHttpSerever(Vertx vertx, Router router, boolean isWebApp) {
		if (isWebApp) {
			HttpServer httpServer = vertx.createHttpServer();
			int portNumber = 8080;
			LOGGER.info("Port : " + portNumber);
			httpServer.requestHandler(router::accept).listen(portNumber, asyncResult -> {
				if (asyncResult.succeeded()) {
					LOGGER.info("SuccessFully creted the http servers");
				} else {
					throw new RuntimeException("Error Creating HttpServer due to {0}", asyncResult.cause());
				}
			});
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void deployVerticle(Vertx vertx, Class currentClass) {
		if (currentClass.isAnnotationPresent(DeployableVerticle.class)) {
			try {
				vertx.deployVerticle((Verticle) currentClass.newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void registerRouters(Vertx vertx, Class currentClass, Router router, boolean isWebApp) {
		if (isWebApp && currentClass.isAnnotationPresent(SubRouter.class)) {
			SubRouter annotation = (SubRouter) currentClass.getAnnotation(SubRouter.class);
			Method[] declaredMethods = currentClass.getDeclaredMethods();
			Router subRouter = Router.router(vertx);
			try {
				Object newInstance = currentClass.newInstance();
				for (Method method : declaredMethods) {
					if (method.isAnnotationPresent(RouteMapping.class)) {
						RouteMapping mapping = method.getAnnotation(RouteMapping.class);
						subRouter.route(mapping.httpMethod(), mapping.path()).handler(getHandler(newInstance, method));
					}

				}
				router.mountSubRouter(annotation.path(), subRouter);
			} catch (InstantiationException e) {
				LOGGER.error("Error Occured while registering Router due to {0}",e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				LOGGER.error("Error Occured while registering Router due to {0}",e);
			}

		}
	}

	private static Handler<RoutingContext> getHandler(Object object, Method method) {
		Handler<RoutingContext> handler = rc -> {
			try {
				method.invoke(object, rc);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				rc.response().end(e.getMessage());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				rc.response().end(e.getMessage());
			} catch (InvocationTargetException e) {
				e.printStackTrace();
				rc.response().end(e.getMessage());
			}
		};
		return handler;
	}

}
