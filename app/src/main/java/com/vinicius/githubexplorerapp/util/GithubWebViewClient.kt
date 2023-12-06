package com.vinicius.githubexplorerapp.util

import android.app.Dialog
import android.net.Uri
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.vinicius.githubexplorerapp.BuildConfig

class GithubWebViewClient(
    private val githubDialog: Dialog,
    private val getAuthToken: (code: String) -> Unit
) : WebViewClient() {
    override fun shouldOverrideUrlLoading(
        view: WebView?,
        request: WebResourceRequest?
    ): Boolean {
        if (request!!.url.toString().startsWith(BuildConfig.REDIRECT_URI)) {
            handleUrl(request.url.toString())
            if (request.url.toString().contains("code=")) {
                githubDialog.dismiss()
            }
            return true
        }
        return false
    }

    private fun handleUrl(url: String) {
        val uri = Uri.parse(url)
        if (url.contains("code")) {
            val githubCode = uri.getQueryParameter("code") ?: ""
            getAuthToken.invoke(githubCode)
        }
    }
}