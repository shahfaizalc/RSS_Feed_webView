package com.fairphone.assignment.rss.injection

import com.fairphone.assignment.rss.adapters.BlogListRecyclerViewAdapter
import com.fairphone.assignment.rss.handlers.RecyclerLoadItemsBlogHandler
import com.fairphone.assignment.rss.injection.module.AndroidModule
import com.fairphone.assignment.rss.injection.module.NetworkStateModule
import com.fairphone.assignment.rss.injection.module.RestHandlerModule
import dagger.Component
import com.fairphone.assignment.rss.view.RssHomeActivity
import com.fairphone.assignment.rss.view.WebViewActivity
import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel
import com.fairphone.assignment.rss.viewmodel.WebViewModel
import javax.inject.Singleton

/**
 * Dagger application component
 */
@Singleton
@Component(modules = arrayOf(AndroidModule::class, NetworkStateModule::class, RestHandlerModule::class))
interface ApplicationComponent {
    fun inject(recyclerLoadItemsBlogHandler: RecyclerLoadItemsBlogHandler)
    fun inject(rssHomeViewModel: RssHomeViewModel)
    fun inject(rssHomeActivity: RssHomeActivity)
    fun inject(blogListRecyclerViewAdapter: BlogListRecyclerViewAdapter)
    fun inject(webViewModel: WebViewModel)
    fun inject(webViewActivity: WebViewActivity)
}
