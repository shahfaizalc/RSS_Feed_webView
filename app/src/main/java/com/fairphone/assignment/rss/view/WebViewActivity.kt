package com.fairphone.assignment.rss.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fairphone.assignment.rss.R
import com.fairphone.assignment.rss.databinding.ActivityWebviewBinding
import com.fairphone.assignment.rss.injection.Application
import com.fairphone.assignment.rss.utils.Constants.Companion.BUNDLE_KEY_URL
import com.fairphone.assignment.rss.utils.NetworkStateHandler
import com.fairphone.assignment.rss.viewmodel.WebViewModel
import javax.inject.Inject


/**
 * the activity to show the blog article on web view based on the user selection.
 */

class WebViewActivity : AppCompatActivity(), NetworkStateHandler.NetworkStateListener {

    //TAG: Class name
    private val TAG = "WebViewActivity"

    //To handle the network state change
    @Inject
    lateinit var networkStateHandler: NetworkStateHandler

    //Binding
    @Transient
    lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Application.component.inject(this)
        val blogUrl = intent.getStringExtra(BUNDLE_KEY_URL)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview);
        binding.webViewData = WebViewModel()
        binding.webViewData!!.webViewUrl = blogUrl
        binding.executePendingBindings()
    }

    override fun onResume() {
        super.onResume()
        registerListeners()
    }

    override fun onStop() {
        super.onStop()
        unRegisterListeners()
    }

    /**
     * Register network state handler
     */
     fun registerListeners() {
        networkStateHandler.registerNetWorkStateBroadCast(this)
        networkStateHandler.setNetworkStateListener(this)
    }

    /**
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
        Log.d(TAG, "onNetWorkStateReceived :$online")
        with(binding.webViewData!!) {
            when (online) {
                true -> {
                    msgView = android.view.View.GONE
                }
                false -> {
                    msgView = android.view.View.VISIBLE
                    msg = applicationContext.resources.getString(com.fairphone.assignment.rss.R.string.network_ErrorMsg)
                }
            }
        }
    }
}
