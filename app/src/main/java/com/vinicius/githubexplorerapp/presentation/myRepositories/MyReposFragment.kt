package com.vinicius.githubexplorerapp.presentation.myRepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.FragmentMyRepositoriesBinding
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import com.vinicius.githubexplorerapp.presentation.myRepositories.adapter.MyReposAdapter
import com.vinicius.githubexplorerapp.util.UserLogged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MyReposFragment : Fragment() {

    private lateinit var binding: FragmentMyRepositoriesBinding
    private val viewModel: MyReposViewModel by viewModels()
    private lateinit var myReposAdapter: MyReposAdapter
    private lateinit var userRepos: List<UserRepo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyRepositoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProfileAvatar()
        setupObservables()
    }

    private fun setupObservables() {
        binding.btnCreateRepo.setOnClickListener {
            showCreateRepoBottomSheet()
        }
        lifecycleScope.launch {
            viewModel.userReposStates.collect {
                setupLoading(it.isLoading)
                loadUserRepos(it.reposList)
                loadError(it.errorMessage)
            }
        }
    }

    private fun showCreateRepoBottomSheet() {
        val bottomSheetFragment = CreateRepoBottomSheetFragment { repo ->
            viewModel.createRepo(UserLogged.getAuthToken(), repo)
        }
        bottomSheetFragment.setStyle(
            DialogFragment.STYLE_NORMAL,
            R.style.FullScreenBottomSheetDialogTheme
        )
        activity?.supportFragmentManager?.let {
            bottomSheetFragment.show(
                it,
                bottomSheetFragment.tag
            )
        }
    }

    private fun loadUserRepos(reposList: List<UserRepo>) {
        userRepos = reposList
        myReposAdapter = MyReposAdapter(reposList)
        binding.userReposList.run {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myReposAdapter
            isClickable = false
        }
    }

    private fun setupLoading(loading: Boolean) {
        if (loading) {
            binding.userReposProgress.visibility = View.VISIBLE
        } else {
            binding.userReposProgress.visibility = View.GONE
        }
    }

    private fun loadError(errorMessage: String) {
        if (errorMessage.isEmpty().not()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun setupProfileAvatar() {
        val user = UserLogged.getUserLogged()
        Glide.with(binding.followingProfileImg).load(user.avatarUrl)
            .into(binding.followingProfileImg)
    }
}