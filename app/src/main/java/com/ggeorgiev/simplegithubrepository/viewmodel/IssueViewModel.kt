package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Branch
import com.ggeorgiev.simplegithubrepository.data.Issue
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class IssueViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: MutableLiveData<ArrayList<Issue>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getIssues(userName : String, repoName : String) : MutableLiveData<ArrayList<Issue>>{
        if(repo == null){
            repo = MutableLiveData()
            fetchIssues(userName, repoName)
        }

        return repo as MutableLiveData<ArrayList<Issue>>
    }

    private fun fetchIssues(userName: String, repoName: String){
        mNetworkComponent.getIssues(userName, repoName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                repo!!.postValue(it)
            },{it ->
                Log.d("error",  it.message)
            })
    }

}