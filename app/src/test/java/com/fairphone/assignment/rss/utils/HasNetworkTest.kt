package com.fairphone.assignment.rss.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.*

class HasNetworkTest {


    lateinit var context: Context
    lateinit var connectivityManager: ConnectivityManager
    lateinit var networkInfo: NetworkInfo

    @Before
    fun setUp() {
        context = mock(Context::class.java)
        connectivityManager = mock(ConnectivityManager::class.java)
        networkInfo = mock(NetworkInfo::class.java)
        `when`(context.getSystemService(Context.CONNECTIVITY_SERVICE)).thenReturn(connectivityManager)
        `when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)

    }

    @Test
    fun testHasNetwork(){
        `when`(networkInfo.isConnected).thenReturn(true)
        assertTrue(HasNetwork.isConnected(context))
    }

    @Test
    fun testHasNetwork2(){
        `when`(networkInfo.isConnected).thenReturn(false)
        assertFalse(HasNetwork.isConnected(context))
    }

}