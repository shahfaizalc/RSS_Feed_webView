package com.fairphone.assignment.rss.listeners

import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel

/**
 * Interface to notify share event
 */
interface ShareEventListener {

    /**
     * On Blog list item share clicked
     * @param rssHomeViewModel : Rss home view model
     * @param position : item position
     */

    fun onClickShareItem(listModel: RssHomeViewModel, position: Int)

}