package com.fairphone.assignment.rss.injection.module

import com.fairphone.assignment.rss.communication.RestHandler
import dagger.Module
import dagger.Provides


/**
 * Rest module
 */
@Module
class RestHandlerModule() {

    @Provides
    fun provideRestHandler(): RestHandler = RestHandler()

}