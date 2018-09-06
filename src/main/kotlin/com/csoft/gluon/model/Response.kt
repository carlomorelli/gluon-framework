package com.csoft.gluon.model

import java.util.Arrays
import java.util.Objects

data class Response(val status: Int,
                    val headers: Map<String, List<String>>,
                    val body: ByteArray) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Response
        if (status != other.status) return false
        if (headers != other.headers) return false
        if (!Arrays.equals(body, other.body)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = status
        result = 31 * result + headers.hashCode()
        result = 31 * result + Arrays.hashCode(body)
        return result
    }

}
