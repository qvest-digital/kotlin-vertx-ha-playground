package de.tarent

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import java.util.logging.Logger

fun main(args: Array<String>) {

    val LOG = Logger.getLogger("main")
    val startVerticle = (System.getenv("START_VERTICLE") != null && System.getenv("START_VERTICLE").compareTo("true") == 0)
        LOG.info("starting kotlin vert.x lab")

    val options = VertxOptions()
    options.isHAEnabled = true
    Vertx.clusteredVertx(options) { cluster ->
        if (cluster.succeeded()) {
            val vertx = cluster.result()

            if (startVerticle) {
                val deployOptions = DeploymentOptions()
                deployOptions.isHa = true
                LOG.info(deployOptions.toJson().toString())
                vertx.deployVerticle(HaTestVerticle::class.qualifiedName, deployOptions)
            }
        }
    }

    LOG.info("exiting kotlin vert.x lab")
}