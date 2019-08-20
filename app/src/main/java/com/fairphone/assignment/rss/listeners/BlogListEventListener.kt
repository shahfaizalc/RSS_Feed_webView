package com.fairphone.assignment.rss.listeners

import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel

/**
 * Interface to notify the list view  event
 */
interface BlogListEventListener {

    /**
     * On Blog list item click
     * @param rssHomeViewModel : Rss home view model
     * @param position : item position
     */
    fun onClickBlogListItem(rssHomeViewModel : RssHomeViewModel, position: Int)

}