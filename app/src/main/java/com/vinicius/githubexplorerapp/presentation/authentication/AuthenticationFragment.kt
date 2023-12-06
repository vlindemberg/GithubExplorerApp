package com.vinicius.githubexplorerapp.presentation.authentication

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vinicius.githubexplorerapp.BuildConfig
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.FragmentAuthenticationBinding
import com.vinicius.githubexplorerapp.util.GithubWebViewClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

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
            viewModel.authStates.collect {
                setupLoading(it.isLoading)
                loadUser(it.success, it.token)
                loadError(it.errorMessage)
            }
        }
        lifecycleScope.launch {
            viewModel.userStates.collect {
                setupLoading(it.isLoading)
                loadHome(it.success)
                loadError(it.errorMessage)
            }
        }
    }

    private fun loadHome(success: Boolean) {
        if (success) {
            findNavController().navigate(
                AuthenticationFragmentDirections.actionLoginFragmentToFollowingFragment()
            )
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.loginProgress.visibility = View.VISIBLE
            binding.btnSingIn.text = ""
            binding.btnSingIn.isClickable = false
        } else {
            binding.loginProgress.visibility = View.GONE
            binding.btnSingIn.text = resources.getString(R.string.sing_in_button)
            binding.btnSingIn.isClickable = true
        }
    }

    private fun loadUser(success: Boolean, token: String) {
        if (success)
            viewModel.getUserWithToken(token)
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
        val webView = setupWebView(url, githubDialog)
        githubDialog.setContentView(webView)
        githubDialog.show()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url: String, githubDialog: Dialog): WebView {
        val webView = WebView(requireContext())
        webView.isVerticalScrollBarEnabled = false
        webView.isHorizontalScrollBarEnabled = false
        webView.webViewClient = GithubWebViewClient(githubDialog) { code ->
            viewModel.getAuthToken(code)
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        return webView
    }
}
