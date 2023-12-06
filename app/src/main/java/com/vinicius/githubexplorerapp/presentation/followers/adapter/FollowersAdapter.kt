package com.vinicius.githubexplorerapp.presentation.followers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vinicius.githubexplorerapp.databinding.FollowersListBinding
import com.vinicius.githubexplorerapp.domain.model.UserFollower

class FollowersAdapter(
    private var followersList: List<UserFollower>,
    private val onItemClickListener: (follower: UserFollower) -> Unit
) : RecyclerView.Adapter<FollowersAdapter.FollowersViewHolder>() {

    class FollowersViewHolder(val binding: FollowersListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowersViewHolder {
        val binding = FollowersListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowersViewHolder, position: Int) {
        val follower = followersList[position]
        holder.binding.apply {
            Glide.with(listProfileImg).load(follower.avatarUrl).into(listProfileImg)
            listName.text = follower.login
            this.root.setOnClickListener {
                onItemClickListener.invoke(follower)
            }
        }
    }

    override fun getItemCount(): Int = followersList.size
}