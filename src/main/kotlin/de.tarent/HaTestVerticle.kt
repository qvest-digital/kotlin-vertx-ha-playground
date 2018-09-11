package de.tarent

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import java.util.logging.Logger

class HaTestVerticle : AbstractVerticle() {
    val LOG = Logger.getLogger("HaTestVerticle")

    override fun start(future: Future<Void>) {
        LOG.warning("HA verticle started")
        vertx.setPeriodic(2000) { _ ->
            LOG.warning("Hello from HA verticle")
        }
        val server = vertx.createHttpServer()
        server.requestHandler { request ->
            val response = request.response()
            response.putHeader("content-type", "text/plain")
            response.end("Hello World!")
        }
        server.listen(8080)
        LOG.warning("HA verticle ending")
    }

}