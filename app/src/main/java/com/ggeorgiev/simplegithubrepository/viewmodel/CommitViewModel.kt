package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Branch
import com.ggeorgiev.simplegithubrepository.data.Commit
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CommitViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: MutableLiveData<ArrayList<Commit>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getCommits(userName : String, repoName : String) : MutableLiveData<ArrayList<Commit>>{
        if(repo == null){
            repo = MutableLiveData()
            fetchCommits(userName, repoName)
        }

        return repo as MutableLiveData<ArrayList<Commit>>
    }

    private fun fetchCommits(userName: String, repoName: String){
        mNetworkComponent.getCommits(userName, repoName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                repo!!.postValue(it)
            },{it ->
                Log.d("error",  it.message)
            })
    }

}