package de.tarent

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.ext.shell.ShellService
import io.vertx.ext.shell.ShellServiceOptions
import io.vertx.ext.shell.term.TelnetTermOptions
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

            val service = ShellService.create(vertx,
                    ShellServiceOptions().setTelnetOptions(
                            TelnetTermOptions().setPort(4000)
                    )
            )
            service.start()

            if (startVerticle) {
                val deployOptions = DeploymentOptions()
                deployOptions.isHa = true
                vertx.deployVerticle(HaTestVerticle::class.qualifiedName, deployOptions)
            }
        }
    }

    LOG.info("exiting kotlin vert.x lab")
}