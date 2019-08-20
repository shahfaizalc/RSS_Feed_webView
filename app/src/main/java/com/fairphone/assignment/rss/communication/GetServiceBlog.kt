package com.fairphone.assignment.rss.communication

import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * interface get service
 */
interface GetServiceBlog {

    /**
     * Retrofit service
     * @param url : path
     */
    @GET
    fun retrieveBlogArticles(@Url url:String): Deferred<String>

}