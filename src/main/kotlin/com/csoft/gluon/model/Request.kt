package com.csoft.gluon.model

import java.net.URI
import java.util.*

data class Request(val method: String,
              val requestURI: URI,
              val headers: Map<String, List<String>>,
              val params: Map<String, List<String>>,
              val body: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Request
        if (method != other.method) return false
        if (requestURI != other.requestURI) return false
        if (headers != other.headers) return false
        if (params != other.params) return false
        if (!Arrays.equals(body, other.body)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = method.hashCode()
        result = 31 * result + requestURI.hashCode()
        result = 31 * result + headers.hashCode()
        result = 31 * result + params.hashCode()
        result = 31 * result + Arrays.hashCode(body)
        return result
    }
}
