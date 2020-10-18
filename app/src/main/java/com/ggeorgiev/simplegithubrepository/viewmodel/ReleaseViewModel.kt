package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Branch
import com.ggeorgiev.simplegithubrepository.data.Release
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ReleaseViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: MutableLiveData<ArrayList<Release>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getReleases(userName : String, repoName : String) : MutableLiveData<ArrayList<Release>>{
        if(repo == null){
            repo = MutableLiveData()
            fetchReleases(userName, repoName)
        }

        return repo as MutableLiveData<ArrayList<Release>>
    }

    private fun fetchReleases(userName: String, repoName : String){
        mNetworkComponent.getReleases(userName, repoName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                repo!!.postValue(it)
            },{it ->
                Log.d("error",  it.message)
            })
    }

}