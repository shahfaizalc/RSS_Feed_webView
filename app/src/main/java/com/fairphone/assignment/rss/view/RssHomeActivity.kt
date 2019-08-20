/*
 * Copyright 2019 Fairphone B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fairphone.assignment.rss.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.fairphone.assignment.rss.R
import com.fairphone.assignment.rss.databinding.ActivityRssHomeBinding
import com.fairphone.assignment.rss.injection.Application
import com.fairphone.assignment.rss.utils.Constants.Companion.BUNDLE_KEY_URL
import com.fairphone.assignment.rss.utils.NetworkStateHandler
import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel
import javax.inject.Inject

/**
 * Rss home Activity to show list of blog articles
 */

class RssHomeActivity : AppCompatActivity(), NetworkStateHandler.NetworkStateListener {

    /**
     * TAG: Class name
     */
    private val TAG = "RssHomeActivity"

    /**
     * Binding
     */
    @Transient
    lateinit var binding: ActivityRssHomeBinding

    /**
     * To handle the network state change
     */
    @Inject
    lateinit var networkStateHandler: NetworkStateHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.component.inject(this)
        bindView()
    }

    private fun bindView() {
        val areaViewModel = ViewModelProviders.of(this).get(RssHomeViewModel::class.java)
        areaViewModel.getBlogArticleLink().observe(this, Observer { text -> startActivityWebView(text) })
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rss_home)
        binding.viewModelData = areaViewModel
    }

    override fun onResume() {
        super.onResume()
        registerListeners()
    }

    override fun onStop() {
        super.onStop()
        unRegisterListeners()
    }

    /*
     * Register network state handler
     */
    fun registerListeners() {
        networkStateHandler.registerNetWorkStateBroadCast(this)
        networkStateHandler.setNetworkStateListener(this)
    }

    /*
     * To Unregister network state handler
     */
    fun unRegisterListeners() {
        networkStateHandler.unRegisterNetWorkStateBroadCast(this)
    }

    /**
     * To handle on network state change received.
     * @param online: network state
     */
    override fun onNetworkStateReceived(online: Boolean) {
        Log.d(TAG, "onNetWorkStateReceivedM:$online")
        with(binding.viewModelData!!) {
            when (online) {
                true -> {
                    msgView = View.GONE
                    if (onLoadErrorMsgVisibility == View.VISIBLE) notifyChange()
                }
                false -> {
                    msgView = View.VISIBLE
                    msg = applicationContext.resources.getString(R.string.network_ErrorMsg)
                }
            }
        }
    }

    /**
     * To start a webview activity
     * @param url : blog article url
     */
    fun startActivityWebView(url: String) {
        val intentNext = Intent(this, WebViewActivity::class.java)
        intentNext.putExtra(BUNDLE_KEY_URL, url)
        startActivity(intentNext)
    }
}