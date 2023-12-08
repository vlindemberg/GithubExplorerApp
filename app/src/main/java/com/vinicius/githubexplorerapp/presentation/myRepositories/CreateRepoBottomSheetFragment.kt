package com.vinicius.githubexplorerapp.presentation.myRepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.vinicius.githubexplorerapp.R
import com.vinicius.githubexplorerapp.databinding.FragmentCreateRepoBottomSheetBinding
import com.vinicius.githubexplorerapp.domain.model.UserRepo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRepoBottomSheetFragment(
    private val createRepoListener: (repoName: UserRepo) -> Unit
) : DialogFragment() {

    private lateinit var binding: FragmentCreateRepoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreateRepoBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.btnCreateRepo.backgroundTintList =
            ContextCompat.getColorStateList(binding.btnCreateRepo.context, R.color.white)
        binding.btnCreateRepo.setOnClickListener {
            val repoName = binding.repoName.text.toString()
            createRepoListener.invoke(
                UserRepo(
                    name = repoName,
                    description = "test",
                    homepage = "https://github.com"
                )
            )
            dismiss()
        }
        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}