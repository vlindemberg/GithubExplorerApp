package com.vinicius.githubexplorerapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.FragmentProfileBinding
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = UserLogged.getUserLogged()
        setupProfile()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout(UserLogged.getAuthToken(), UserLogged.getAuthTokenWithoutBearer())
        }
        lifecycleScope.launch {
            viewModel.logoutStates.collect {
                setupLoading(it.isLoading)
                logout(it.success)
                loadError(it.errorMessage)
            }
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.logoutProgress.visibility = View.VISIBLE
            binding.btnLogout.text = ""
            binding.btnLogout.isClickable = false
        } else {
            binding.logoutProgress.visibility = View.GONE
            binding.btnLogout.text = resources.getString(R.string.profile_logout)
            binding.btnLogout.isClickable = true
        }
    }

    private fun setupProfile() {
        Glide.with(binding.profileImg).load(user.avatarUrl)
            .into(binding.profileImg)
        binding.profileName.text = user.name
    }

    private fun loadError(errorMessage: String) {
        if (errorMessage.isEmpty().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun logout(success: Boolean) {
        if (success) {
            requireContext().applicationContext.cacheDir.deleteRecursively()
            UserLogged.setUserLogged(User())
            UserLogged.setAuthToken("")
            UserLogged.setAuthTokenWithoutBearer("")
            findNavController().navigate(R.id.authenticationFragment)
        }
    }
}