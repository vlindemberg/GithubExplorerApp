package com.vinicius.githubexplorerapp.presentation.myRepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vinicius.githubexplorerapp.databinding.FragmentMyRepositoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyRepositoriesFragment : Fragment() {

    private lateinit var binding: FragmentMyRepositoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyRepositoriesBinding.inflate(layoutInflater)
        return binding.root
    }
}