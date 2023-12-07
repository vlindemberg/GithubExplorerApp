package com.vinicius.githubexplorerapp.presentation.myRepositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.githubexplorerapp.databinding.RepositoriesListBinding
import com.vinicius.githubexplorerapp.domain.model.UserRepo

class MyReposAdapter(
    private var userRepos: List<UserRepo>,
) : RecyclerView.Adapter<MyReposAdapter.MyReposViewHolder>() {

    class MyReposViewHolder(val binding: RepositoriesListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReposViewHolder {
        val binding = RepositoriesListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyReposViewHolder, position: Int) {
        val userRepo = userRepos[position]
        holder.binding.apply {
            repoName.text = userRepo.name
            repoDescription.text = userRepo.description
        }
    }

    override fun getItemCount(): Int = userRepos.size
}
