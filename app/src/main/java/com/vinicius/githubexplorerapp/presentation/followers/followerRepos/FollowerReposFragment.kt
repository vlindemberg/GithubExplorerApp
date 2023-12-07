package com.vinicius.githubexplorerapp.presentation.followers.followerRepos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.databinding.FragmentFollowerReposBinding
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.presentation.followers.followerRepos.adapter.FollowerReposAdapter
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FollowerReposFragment : Fragment() {

    private lateinit var binding: FragmentFollowerReposBinding
    private val viewModel: FollowerReposViewModel by viewModels()
    private lateinit var followerRepoAdapter: FollowerReposAdapter
    private lateinit var followerReposList: List<UserRepo>
    private val args: FollowerReposFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerReposBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchFollowerRepoData()
        setupProfileAvatar()
        setupObservables()
    }

    private fun fetchFollowerRepoData() {
        args.followerUser.login?.let { username ->
            viewModel.getUserFollowerRepos(UserLogged.getAuthToken(), username)
        }
    }

    private fun setupObservables() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        lifecycleScope.launch {
            viewModel.userFollowerReposStates.collect {
                setupLoading(it.isLoading)
                loadUserFollowers(it.followerReposList)
                loadError(it.errorMessage)
            }
        }
    }

    private fun loadUserFollowers(followerRepoList: List<UserRepo>) {
        followerReposList = followerRepoList
        followerRepoAdapter = FollowerReposAdapter(followerRepoList)
        binding.followerRepoList.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = followerRepoAdapter
            isClickable = false
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.followerRepoProgress.visibility = View.VISIBLE
        } else {
            binding.followerRepoProgress.visibility = View.GONE
        }
    }

    private fun loadError(errorMessage: String) {
        if (errorMessage.isEmpty().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupProfileAvatar() {
        binding.followingName.text = args.followerUser.login
        Glide.with(binding.followingProfileImg).load(args.followerUser.avatarUrl)
            .into(binding.followingProfileImg)
    }
}