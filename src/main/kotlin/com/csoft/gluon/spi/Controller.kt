package com.csoft.gluon.spi

import com.csoft.gluon.model.ExchangeMapper
import com.csoft.gluon.model.Request
import com.csoft.gluon.model.Response
import com.sun.net.httpserver.HttpHandler
import mu.KotlinLogging

abstract class Controller {

    private val logger = KotlinLogging.logger {}

    private val exchangeMapper = ExchangeMapper()

    fun toHandler() = HttpHandler {
        run {
            logger.info("Access: method=[{}], source=[{}], path=[{}]",
                    it.requestMethod,
                    it.remoteAddress.hostString,
                    it.httpContext.path)
            val response = handle(exchangeMapper.extractRequest(it))
            exchangeMapper.passResponse(it, response)
        }
    }

    abstract fun handle(request: Request): Response

}
