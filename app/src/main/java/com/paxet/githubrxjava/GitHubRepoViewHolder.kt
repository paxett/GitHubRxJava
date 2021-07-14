package com.paxet.githubrxjava

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GitHubRepoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val repoName: TextView = itemView.findViewById(R.id.text_repo_name)
    private val repoDescription : TextView = itemView.findViewById(R.id.text_repo_description)
    private val repoLanguage : TextView = itemView.findViewById(R.id.text_repo_language)
    private val starsCount : TextView = itemView.findViewById(R.id.text_stars)

    fun bind(gitHubRepo: GitHubRepo) {
        repoName.text = gitHubRepo.name
        repoDescription.text = gitHubRepo.description
        repoLanguage.text = gitHubRepo.language
        starsCount.text = gitHubRepo.starCount.toString()
    }
}