package com.fairphone.assignment.rss.viewmodel

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.fairphone.assignment.rss.injection.Application
import com.fairphone.assignment.rss.injection.ApplicationComponent
import com.fairphone.assignment.rss.model.BlogItemModel
import com.fairphone.assignment.rss.utils.NetworkStateHandler
import com.fairphone.assignment.rss.view.RssHomeActivity
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*



class RssHomeViewModelTest {

    lateinit var networkStateHandlerMock: NetworkStateHandler

    lateinit var applicationComponentMock: ApplicationComponent

    lateinit var rssHomeViewModel: RssHomeViewModel;

    lateinit var rssHomeActivity: RssHomeActivity

    @Mock
    lateinit var contextMock:Context

    lateinit var resourseMock:Resources

    @Mock
    lateinit var  weblinkBlogArticle: MutableLiveData<String>

    @Before
    fun setUp() {
        val application= Application()
        application.onCreate()
        applicationComponentMock = mock(ApplicationComponent::class.java)
        networkStateHandlerMock = mock(NetworkStateHandler::class.java)
        contextMock = mock(Context::class.java)
        resourseMock = mock(Resources::class.java)
        Application.component = applicationComponentMock;
        rssHomeActivity = mock(RssHomeActivity::class.java)
        rssHomeViewModel = RssHomeViewModel()
    }


    @Test
    fun getMsgView() {
        rssHomeViewModel.msgView = View.GONE
        assert(rssHomeViewModel.msgView == View.GONE)

    }

    @Test
    fun getProgressBarVisible() {
        rssHomeViewModel.progressBarVisible = View.GONE
        assert(rssHomeViewModel.progressBarVisible == View.GONE)
    }


    @Test
    fun getBlogRetry() {
        rssHomeViewModel.onLoadErrorMsgText = "sampleText"
        assert(rssHomeViewModel.onLoadErrorMsgText.equals( "sampleText"))
    }

    @Test
    fun getMsg() {
        rssHomeViewModel.msg = "sampleText"
        assert(rssHomeViewModel.msg.equals( "sampleText"))
    }

    @Test
    fun getScreenHint() {
        rssHomeViewModel.onLoadErrorMsgVisibility = View.GONE
        assert(rssHomeViewModel.onLoadErrorMsgVisibility == View.GONE)
    }

}