package com.vinicius.githubexplorerapp.presentation.authentication

import android.annotation.SuppressLint
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.databinding.FragmentAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.json.JSONTokener
import java.io.OutputStreamWriter
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

@AndroidEntryPoint
class AuthenticationFragment : Fragment() {

    private lateinit var githubDialog: Dialog
    private lateinit var binding: FragmentAuthenticationBinding
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSingIn.setOnClickListener {
            setupGithubWebViewDialog(setupGitAuthUrl())
        }
        lifecycleScope.launch {
            viewModel.states.collect {
                setupLoading(it.isLoading)
                loadHome(it.success)
                loadError(it.errorMessage)
            }
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.loginProgress.visibility = View.VISIBLE
        } else {
            binding.loginProgress.visibility = View.GONE
        }
    }

    private fun loadHome(success: Boolean) {
        if (success) {
            findNavController().navigate(
                AuthenticationFragmentDirections.actionLoginFragmentToFollowingFragment()
            )
        }
    }

    private fun loadError(errorMessage: String) {
        if (errorMessage.isEmpty().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupGitAuthUrl(): String {
        return BuildConfig.AUTH_URL + "?client_id=" +
                BuildConfig.CLIENT_ID + "&scope=" +
                BuildConfig.SCOPE + "&redirect_uri=" +
                BuildConfig.REDIRECT_URI + "&state=" +
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun setupGithubWebViewDialog(url: String) {
        githubDialog = Dialog(requireContext())
        val webView = WebView(requireContext())
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = GithubWebViewClient()
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        githubDialog.setContentView(webView)
        githubDialog.show()
    }

    inner class GithubWebViewClient : WebViewClient() {
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
                viewModel.getAuthToken(githubCode)
            }
        }
    }
}
