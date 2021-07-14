package com.paxet.githubrxjava

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    private var subscription: Subscription? = null
    private val gitHubRVAdapter = GitHubRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.rv_github_repo_list).run {
            adapter = gitHubRVAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
        val editTextUsername: TextView = findViewById(R.id.edit_text_username)
        val buttonSearch: Button =  findViewById(R.id.button_search)
        buttonSearch.setOnClickListener {
            val username: String = editTextUsername.getText().toString()
            if(username.isNotBlank())
                getStarredRepos(username)
        }
    }

    override fun onDestroy() {
        if (subscription != null && !subscription!!.isUnsubscribed) {
            subscription!!.unsubscribe()
        }
        super.onDestroy()
    }

    private fun getStarredRepos(username: String) {
        subscription = GitHubClient.getStarredRepos(username)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<List<GitHubRepo>> {
                override fun onCompleted() {
                    Log.d(TAG, "In onCompleted()");
                }

                override fun onError(e: Throwable?) {
                    e?.printStackTrace();
                    Log.d(TAG, "In onError()");
                }

                override fun onNext(gitHubRepoList: List<GitHubRepo>?) {
                    Log.d(TAG, "In onNext()");
                    gitHubRVAdapter.setGitHubRepoList(gitHubRepoList)
                }

            })
    }
}