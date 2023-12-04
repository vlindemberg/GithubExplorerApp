package com.vinicius.githubexplorerapp.presentation.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.databinding.FragmentFollowingBinding
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfileAvatar()
    }

    private fun setupProfileAvatar() {
        val user = UserLogged.getUserLogged()
        Glide.with(binding.followingProfileImg).load(user.avatarUrl)
            .into(binding.followingProfileImg)
    }
}