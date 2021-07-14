package com.paxet.githubrxjava

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable


object GitHubClient {
    private val GITHUB_BASE_URL = "https://api.github.com/"
    private var gitHubService: GitHubService? = null

    init {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val httpClient = OkHttpClient
            .Builder()

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
        gitHubService = retrofit.create(GitHubService::class.java)
    }

    fun getStarredRepos(userName: String): Observable<List<GitHubRepo>>? {
        return gitHubService?.getStarredRepositories(userName)
    }
}