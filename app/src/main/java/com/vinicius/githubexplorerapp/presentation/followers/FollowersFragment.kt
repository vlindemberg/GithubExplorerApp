package com.vinicius.githubexplorerapp.presentation.followers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.databinding.FragmentFollowersBinding
import com.vinicius.githubexplorerapp.domain.model.UserFollower
import com.vinicius.githubexplorerapp.presentation.followers.adapter.FollowersAdapter
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowersFragment : Fragment() {

    private lateinit var binding: FragmentFollowersBinding
    private val viewModel: FollowersViewModel by viewModels()
    private lateinit var followersAdapter: FollowersAdapter
    private lateinit var userFollowerList: List<UserFollower>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfileAvatar()
        setupObservables()
    }

    private fun setupObservables() {
        lifecycleScope.launch {
            viewModel.userFollowersStates.collect {
                setupLoading(it.isLoading)
                loadUserFollowers(it.followersList)
                loadError(it.errorMessage)
            }
        }
    }

    private fun loadUserFollowers(followersList: List<UserFollower>) {
        userFollowerList = followersList
        followersAdapter = FollowersAdapter(followersList, onItemClickListener = {
            navigateToFollowerRepositories(it)
        })
        binding.followersList.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = followersAdapter
            isClickable = true
        }
    }

    private fun navigateToFollowerRepositories(follower: UserFollower) {
        TODO("Not yet implemented")
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.followersProgress.visibility = View.VISIBLE
        } else {
            binding.followersProgress.visibility = View.GONE
        }
    }

    private fun loadError(errorMessage: String) {
        if (errorMessage.isEmpty().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupProfileAvatar() {
        val user = UserLogged.getUserLogged()
        Glide.with(binding.followersProfileImg).load(user.avatarUrl)
            .into(binding.followersProfileImg)
    }
}