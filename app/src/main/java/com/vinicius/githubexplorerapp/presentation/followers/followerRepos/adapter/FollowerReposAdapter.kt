package com.vinicius.githubexplorerapp.presentation.followers.followerRepos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vinicius.githubexplorerapp.databinding.RepositoriesListBinding
import com.vinicius.githubexplorerapp.domain.model.UserRepo

class FollowerReposAdapter(
    private var followerReposList: List<UserRepo>,
) : RecyclerView.Adapter<FollowerReposAdapter.FollowerReposViewHolder>() {

    class FollowerReposViewHolder(val binding: RepositoriesListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerReposViewHolder {
        val binding = RepositoriesListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowerReposViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerReposViewHolder, position: Int) {
        val followerRepo = followerReposList[position]
        holder.binding.apply {
            repoName.text = followerRepo.name
            repoDescription.text = followerRepo.description
        }
    }

    override fun getItemCount(): Int = followerReposList.size
}
