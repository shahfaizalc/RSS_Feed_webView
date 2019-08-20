package com.fairphone.assignment.rss.utils

import android.content.Context
import android.net.http.SslError
import android.webkit.*
import com.fairphone.assignment.rss.listeners.WebViewCallback
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MyWebViewClientTest {

    lateinit var myWebViewClient: MyWebViewClient;

    @Mock
    lateinit var webViewCallback: WebViewCallback;

    @Mock
    lateinit var webView: WebView

    @Mock
    lateinit var contextMock: Context

    @Mock
    lateinit var webResourceRequest: WebResourceRequest

    @Mock
    lateinit var error: WebResourceError

    @Mock
    lateinit var errorResponse: WebResourceResponse;

    @Mock
    lateinit var handler: SslErrorHandler

    @Mock
    lateinit var sslError: SslError


    @Before
    fun setUp() {

        webResourceRequest = mock(WebResourceRequest::class.java)
        webViewCallback = mock(WebViewCallback::class.java)
        myWebViewClient = MyWebViewClient(webViewCallback)
        contextMock = mock(Context::class.java)
        webView = (WebView(contextMock))
        error = mock(WebResourceError::class.java)

        errorResponse = mock(WebResourceResponse::class.java)
        handler = mock(SslErrorHandler::class.java)
        sslError = mock(SslError::class.java)
        Mockito.`when`(sslError.toString()).thenReturn("loading failed")
        Mockito.`when`(error.toString()).thenReturn("loading failed")

    }


    @Test
    fun testonPageFinished() {
        myWebViewClient.onPageFinished(webView, "")
        verify(webViewCallback).onSuccess()

    }

    @Test
    fun testonReceivedError() {

        myWebViewClient.onReceivedHttpError(webView, webResourceRequest, errorResponse)
        verify(webViewCallback).onError("Error Code " +errorResponse.statusCode)

    }

    @Test
    fun testonReceivedHttpError() {
        myWebViewClient.onReceivedSslError(webView, handler, sslError)
        verify(webViewCallback).onError("loading failed")

    }

    @Test
    fun testonReceivedSslError() {
        myWebViewClient.onReceivedError(webView, webResourceRequest, error)
        verify(webViewCallback).onError("loading failed")

    }

    @Test
    fun testshouldOverrideUrlLoading() {
      val response =   myWebViewClient.shouldOverrideUrlLoading(webView, webResourceRequest)
      assertEquals(response,true)

    }
}