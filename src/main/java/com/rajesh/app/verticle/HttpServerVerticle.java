package com.rajesh.app.verticle;

import com.rajesh.lib.annotation.DeployableVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * 
 * @author rrajeshkumar
 *
 */
@DeployableVerticle
public class HttpServerVerticle extends AbstractVerticle {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);

	@Override
	public void start(Future<Void> startFuture) throws Exception {
		LOGGER.info("Verticle Started");
		super.start(startFuture);
	}

	@Override
	public void stop(Future<Void> stopFuture) throws Exception {
		super.stop(stopFuture);
	}

}
