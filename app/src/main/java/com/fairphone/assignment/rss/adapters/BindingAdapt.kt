package com.fairphone.assignment.rss.adapters

import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fairphone.assignment.rss.R
import com.fairphone.assignment.rss.handlers.RecyclerLoadItemsBlogHandler
import com.fairphone.assignment.rss.model.BlogItemModel
import com.fairphone.assignment.rss.utils.Constants
import com.fairphone.assignment.rss.viewmodel.RssHomeViewModel
import com.fairphone.assignment.rss.viewmodel.WebViewModel


/**
 * To handle the Article list recyclerview and search query
 * @param searchView : search view to search specific article from the list
 * @param rssHomeViewModel : View model of the home class
 * @param recyclerView : Blog articles recycler view
 */
@BindingAdapter("app:searchAdapter", "app:searchRecycler")
fun adapter(searchView: SearchView, rssHomeViewModel: RssHomeViewModel, recyclerView: RecyclerView) {

    // Empty string used in search query on clear
    val emptySting = "";

    // Linear Layout managger
    val linearLayoutManager = LinearLayoutManager(recyclerView.context)

    //Articles receycler view adapter
    val listAdapter = BlogListRecyclerViewAdapter(rssHomeViewModel)

    // Recycler view load more items
    val bindingAdapter = RecyclerLoadItemsBlogHandler(rssHomeViewModel, listAdapter)

    recyclerView.layoutManager = linearLayoutManager as RecyclerView.LayoutManager
    recyclerView.adapter = listAdapter
    bindingAdapter.scrollListener(recyclerView, linearLayoutManager)
    bindingAdapter.initRequest(recyclerView, Constants.PATH_FEED)

    // Search view clear query
    val searchClear = searchView.findViewById<View>(R.id.search_close_btn)
    searchClear.setOnClickListener({ searchView.setQuery(emptySting, true) })

    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(s: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(query: String): Boolean {
            if (!query.isEmpty()) {
                //Kotlin filter to filter the query results
                val model =
                    rssHomeViewModel.blogArticlesListModel.filter {
                        it.title.toLowerCase().startsWith(query.toLowerCase())
                    }
                val arrayList = ObservableArrayList<BlogItemModel>()
                arrayList.addAll(model)
                rssHomeViewModel.blogArticlesFilteredListModel = arrayList
                bindingAdapter.clearRecycleView(recyclerView)
                bindingAdapter.notifyAdapter(recyclerView, listAdapter.itemCount)

            } else {
                //reset the recycleview upon clear query
                rssHomeViewModel.blogArticlesFilteredListModel = rssHomeViewModel.blogArticlesListModel
                bindingAdapter.clearRecycleView(recyclerView)
                bindingAdapter.resetRecycleView(recyclerView)
            }
            return false
        }
    })
}

/**
 * To set webview client
 * @param webView : Article web view
 * @param client : web view client
 */
@BindingAdapter("setWebViewClient")
fun setWebViewClient(webView: WebView, client: WebViewClient) {
        webView.webViewClient = client
        WebView.setWebContentsDebuggingEnabled(false)
        webView.settings.javaScriptEnabled
        webView.settings.loadsImagesAutomatically
}

/**
 * To load the webview
 * @param webView : Article web view
 * @param webViewModel: webview viewmodel class
 */
@BindingAdapter("loadUrl")
fun loadUrl(webView: WebView, webViewModel: WebViewModel) {
    with(webViewModel) {
        when (webViewUrl) {
            "" -> {
                msgView = View.VISIBLE
                msg = webView.context.resources.getString(R.string.rssblog_web_error)
                progressBarVisible = View.GONE
            }
            else -> webView.loadUrl(webViewUrl)
        }
    }
}
