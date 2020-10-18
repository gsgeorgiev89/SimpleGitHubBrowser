package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class OwnedRepositoriesViewModel(application: Application) : AndroidViewModel(application){

    var reposList: MutableLiveData<ArrayList<Repository>>? = null

    val mNetworkComponent by lazy {
        NetworkComponent.create();
    }
    fun getOwnedRepositories(name : String) : MutableLiveData<ArrayList<Repository>> {
        if (reposList == null){
            reposList = MutableLiveData()
            fetchRepositories(name);
        }
        return reposList as MutableLiveData<ArrayList<Repository>>
    }

    private fun fetchRepositories(name : String) {
        mNetworkComponent.getOwnedRepositories(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ it ->
                reposList!!.postValue(it)
            }, { it ->
                Log.d("error", it.message)
            })
    }
}