package com.fairphone.assignment.rss.view

import android.content.Context
import android.content.res.Resources
import com.fairphone.assignment.rss.databinding.ActivityWebviewBinding
import com.fairphone.assignment.rss.injection.Application
import com.fairphone.assignment.rss.injection.ApplicationComponent
import com.fairphone.assignment.rss.utils.NetworkStateHandler
import com.fairphone.assignment.rss.viewmodel.WebViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class WebViewActivityTest {

    lateinit var networkStateHandlerMock: NetworkStateHandler

    lateinit var fragmentRssHomeBinding : ActivityWebviewBinding

    lateinit var applicationComponentMock: ApplicationComponent

    lateinit var rssHomeViewModel: WebViewModel;

    lateinit var rssHomeActivity: WebViewActivity

    @Mock
    lateinit var contextMock: Context

    lateinit var resourseMock: Resources


    @Before
    fun setUp() {
        val application = Application()
        application.onCreate()
        applicationComponentMock = Mockito.mock(ApplicationComponent::class.java)
        networkStateHandlerMock = Mockito.mock(NetworkStateHandler::class.java)
        contextMock = Mockito.mock(Context::class.java)
        resourseMock = Mockito.mock(Resources::class.java)
        fragmentRssHomeBinding = Mockito.mock(ActivityWebviewBinding::class.java)
        Application.component = applicationComponentMock;
        rssHomeActivity = WebViewActivity()

        rssHomeViewModel = WebViewModel()
        rssHomeActivity.networkStateHandler = networkStateHandlerMock
        //  rssHomeActivity.baseContext = contextMock
        rssHomeActivity.binding =  fragmentRssHomeBinding

        Mockito.`when`(rssHomeActivity.binding.webViewData).thenReturn(rssHomeViewModel)
    }

    @Test
    fun testonNetworkStateReceivedOn() {
        rssHomeActivity.onNetworkStateReceived(true)
        Assert.assertEquals(rssHomeViewModel.msgView, 0x00000008)
    }

    @Test
    fun testregisterListeners() {
        rssHomeActivity.registerListeners()

        Mockito.verify(networkStateHandlerMock).registerNetWorkStateBroadCast(rssHomeActivity)
        Mockito.verify(networkStateHandlerMock).setNetworkStateListener(rssHomeActivity)
    }


    @Test
    fun testunRegisterListeners() {
        rssHomeActivity.unRegisterListeners()
        Mockito.verify(networkStateHandlerMock).unRegisterNetWorkStateBroadCast(rssHomeActivity)

    }

}