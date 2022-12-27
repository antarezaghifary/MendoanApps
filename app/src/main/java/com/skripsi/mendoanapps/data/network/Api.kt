package com.skripsi.mendoanapps.data.network

import com.oratakashi.viewbinding.core.tools.retrofit.createOkHttpClient
import com.oratakashi.viewbinding.core.tools.retrofit.createReactiveService

object Api {
    fun getApi(): MyApi {
        return createReactiveService(
            MyApi::class.java,
            createOkHttpClient(
                interceptors = arrayOf(),
                authenticator = null,
                showDebugLog = true,
                pinner = null
            ),
            /**
             * URL TERPAKAI
             */
            baseURl = "http://103.189.234.18:9966/api/"
        )
    }
}