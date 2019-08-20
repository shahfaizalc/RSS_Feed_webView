package com.fairphone.assignment.rss.handlers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fairphone.assignment.rss.adapters.BlogListRecyclerViewAdapter
import com.fairphone.assignment.rss.injection.Application
import com.fairphone.assignment.rss.injection.ApplicationComponent
import com.fairphone.assignment.rss.utils.EndlessRecyclerViewScrollListener
import com.fairphone.assignment.rss.view.RssHomeActivity
import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.Mockito.verify

class RecyclerLoadItemsBlogHandlerTest{


    lateinit var filterViewModel: RssHomeViewModel

    lateinit var filterActivityMock: RssHomeActivity
    lateinit var recyclerLoadItemsHandler: RecyclerLoadItemsBlogHandler
    lateinit var artistBlogListRecyclerViewAdapter: BlogListRecyclerViewAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var applicationComponentMock: ApplicationComponent
    lateinit var linearLayoutManager: LinearLayoutManager


    @Before
    fun setUp() {

        applicationComponentMock = Mockito.mock(ApplicationComponent::class.java)
        filterActivityMock = Mockito.mock(RssHomeActivity::class.java)
        Application.component = applicationComponentMock;
        artistBlogListRecyclerViewAdapter = Mockito.mock(BlogListRecyclerViewAdapter::class.java)
        recyclerView = Mockito.mock(RecyclerView::class.java)
        linearLayoutManager= Mockito.mock(LinearLayoutManager::class.java)
        filterViewModel = RssHomeViewModel();
        recyclerLoadItemsHandler = RecyclerLoadItemsBlogHandler(filterViewModel, artistBlogListRecyclerViewAdapter)

    }

    @Test
    fun testscrolllistener() {
        recyclerLoadItemsHandler.scrollListener(recyclerView, linearLayoutManager)
        verify(recyclerView).addOnScrollListener(ArgumentMatchers.isA(EndlessRecyclerViewScrollListener::class.java))
    }

}