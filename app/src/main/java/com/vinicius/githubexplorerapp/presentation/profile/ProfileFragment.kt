package com.vinicius.githubexplorerapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.FragmentProfileBinding
import com.vinicius.githubexplorerapp.domain.model.User
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

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
    }

    private fun setupProfile() {
        binding.btnLogout.setOnClickListener {
            logout()
        }

        Glide.with(binding.profileImg).load(user.avatarUrl)
            .into(binding.profileImg)
        binding.profileName.text = user.name
    }

    private fun logout() {
        requireContext().applicationContext.cacheDir.deleteRecursively()
        UserLogged.setUserLogged(User())
        UserLogged.setAuthToken("")
        findNavController().navigate(R.id.authenticationFragment)
    }
}