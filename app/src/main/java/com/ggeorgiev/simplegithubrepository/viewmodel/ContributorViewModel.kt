package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Branch
import com.ggeorgiev.simplegithubrepository.data.Contributor
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ContributorViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: MutableLiveData<ArrayList<Contributor>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getContributors(userName : String, repoName : String) : MutableLiveData<ArrayList<Contributor>>{
        if(repo == null){
            repo = MutableLiveData()
            fetchContributors(userName, repoName)
        }

        return repo as MutableLiveData<ArrayList<Contributor>>
    }

    private fun fetchContributors(userName: String, repoName: String){
        mNetworkComponent.getContributors(userName, repoName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                repo!!.postValue(it)
            },{it ->
                Log.d("error",  it.message)
            })
    }

}