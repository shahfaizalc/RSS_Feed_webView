package com.fairphone.assignment.rss.injection.module

import com.fairphone.assignment.rss.utils.NetworkStateHandler
import dagger.Module
import dagger.Provides


/**
 * Network module
 */
@Module
 class NetworkStateModule() {

    @Provides
    fun provideApplicationContext(): NetworkStateHandler = NetworkStateHandler()

}