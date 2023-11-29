package com.odde.atddv2.myorder

import android.app.Application
import android.content.Context


class MyOrderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {
        var appContext: Context? = null
            private set
    }
}