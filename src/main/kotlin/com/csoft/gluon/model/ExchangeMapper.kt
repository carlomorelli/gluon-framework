package com.csoft.gluon.model

import com.sun.net.httpserver.HttpExchange
import org.apache.commons.io.IOUtils

class ExchangeMapper {

    fun extractRequest(httpExchange: HttpExchange): Request {
        return Request(
                httpExchange.requestMethod,
                httpExchange.requestURI,
                httpExchange.requestHeaders.entries.map { it.key to it.value }.toMap(),
                emptyMap(), //TODO use OkHttp HttpUrl::parse
                IOUtils.toByteArray(httpExchange.requestBody)
        )
    }

    fun passResponse(httpExchange: HttpExchange, response: Response) {
        val os = httpExchange.responseBody
        httpExchange.sendResponseHeaders(response.status, response.body.size.toLong())
        os.write(response.body)
        os.close()
    }

}
