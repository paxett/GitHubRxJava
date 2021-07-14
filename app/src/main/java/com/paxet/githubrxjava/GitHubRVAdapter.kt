package com.paxet.githubrxjava

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GitHubRVAdapter : RecyclerView.Adapter<GitHubRepoViewHolder>() {
    var gitHubRepos: MutableList<GitHubRepo> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubRepoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GitHubRepoViewHolder(
            inflater.inflate(
                R.layout.github_repo_viewholder,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GitHubRepoViewHolder, position: Int) {
        holder.bind(gitHubRepos.get(position))
    }

    override fun getItemCount(): Int {
        return gitHubRepos.size
    }

    fun setGitHubRepoList(repos: List<GitHubRepo>?) {
        if (repos == null) {
            return
        }
        gitHubRepos.clear()
        gitHubRepos.addAll(repos)
        notifyDataSetChanged()
    }
}