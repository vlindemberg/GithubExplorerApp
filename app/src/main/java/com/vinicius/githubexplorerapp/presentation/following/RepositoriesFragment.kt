package com.vinicius.githubexplorerapp.presentation.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinicius.githubexplorerapp.databinding.FragmentFollowersRepositoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentFollowersRepositoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersRepositoriesBinding.inflate(layoutInflater)
        return binding.root
    }
}