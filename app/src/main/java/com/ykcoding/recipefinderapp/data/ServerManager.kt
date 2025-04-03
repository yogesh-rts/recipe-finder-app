package com.ykcoding.recipefinderapp.data

import com.ykcoding.recipefinderapp.BuildConfig

object ServerManager {

    private const val HTTP_SECURE = "https://"
    private const val HTTP = "http://"


    object UseSsl {
        const val DEFAULT = true
    }

    private const val BASEURL = BuildConfig.BASE_URL
    private const val SSL = UseSsl.DEFAULT

    val url: String
        get() = when(SSL) {
            true -> "$HTTP_SECURE$BASEURL/"
            false -> "$HTTP$BASEURL/"
        }
}