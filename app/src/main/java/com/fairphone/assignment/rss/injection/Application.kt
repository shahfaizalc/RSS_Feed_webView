package com.fairphone.assignment.rss.injection

import android.app.Application
import com.fairphone.assignment.rss.injection.module.AndroidModule
import com.fairphone.assignment.rss.injection.module.NetworkStateModule
import com.fairphone.assignment.rss.injection.module.RestHandlerModule

/**
 * Application class
 */
class Application : Application() {
    
    companion object {
        @JvmStatic
        lateinit var component: ApplicationComponent
    }
    
    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .networkStateModule(NetworkStateModule())
                .restHandlerModule(RestHandlerModule())
                .build()
    }
}