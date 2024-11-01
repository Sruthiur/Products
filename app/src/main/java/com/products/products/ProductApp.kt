package com.products.products

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ProductApp: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

    }
    companion object {
        var instance: ProductApp? = null
            private set

    }
}