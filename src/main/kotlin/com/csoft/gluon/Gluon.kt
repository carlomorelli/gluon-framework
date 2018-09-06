package com.csoft.gluon

import com.csoft.gluon.spi.Router
import com.sun.net.httpserver.HttpServer
import mu.KotlinLogging
import java.net.InetSocketAddress

class Gluon(port: Int) {

    private val logger = KotlinLogging.logger {}

    private var httpServer: HttpServer? = null

    private var router: Router? = null

    init {
            httpServer = HttpServer.create(InetSocketAddress(port), 0)
            httpServer!!.executor = null
            logger.info("Gluon started on port [{}].", port)

    }

    fun start() {
        val map = router!!.mapOfControllers()
        map.forEach { path, setOfControllers ->
            setOfControllers
                    .iterator()
                    .forEachRemaining { controller -> httpServer!!.createContext(path, controller.toHandler()) }
        }
        httpServer!!.start()
    }

    fun stop() {
        httpServer!!.stop(0)
    }

    fun withRouter(router: Router): Gluon {
        this.router = router
        return this
    }

}
