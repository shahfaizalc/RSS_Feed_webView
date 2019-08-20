package com.fairphone.assignment.rss.viewmodel

import android.content.res.Resources
import android.view.View
import com.fairphone.assignment.rss.utils.NetworkStateHandler
import com.fairphone.assignment.rss.view.WebViewActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class WebViewModelTest {

    lateinit var networkStateHandlerMock: NetworkStateHandler

    lateinit var rssHomeViewModel: WebViewModel;

    lateinit var rssHomeActivity: WebViewActivity

    lateinit var resourseMock: Resources

    @Before
    fun setUp() {
        networkStateHandlerMock = Mockito.mock(NetworkStateHandler::class.java)
        resourseMock = Mockito.mock(Resources::class.java)
        rssHomeActivity = Mockito.mock(WebViewActivity::class.java)
        rssHomeViewModel = WebViewModel()
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
    fun getMsg() {
        rssHomeViewModel.msg = "sampleText"
        assert(rssHomeViewModel.msg.equals("sampleText"))
    }
    @Test
    fun onSuccess(){
        rssHomeViewModel.onSuccess()
        assert(rssHomeViewModel.progressBarVisible == View.GONE)
    }

    @Test
    fun onError(){
        rssHomeViewModel.onError("Error")
        assert(rssHomeViewModel.progressBarVisible == View.GONE)
    }

    @Test
    fun onGetWebViewClient(){
        val webClient = rssHomeViewModel.getWebViewClient()
        Assert.assertNotNull(webClient)
    }
}
