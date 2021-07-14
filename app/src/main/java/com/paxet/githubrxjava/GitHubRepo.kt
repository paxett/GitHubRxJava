package com.paxet.githubrxjava

data class GitHubRepo(
    val id: Int,
    val name: String,
    val url: String,
    val description: String,
    val language: String,
    val starCount: Int
    )