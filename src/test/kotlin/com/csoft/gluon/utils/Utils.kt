package com.csoft.gluon.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object Utils {

    fun sendHttpRequest(url: String): Response {
        val request = Request.Builder()
                .url(url)
                .build()
        return OkHttpClient().newCall(request).execute()
    }
}
