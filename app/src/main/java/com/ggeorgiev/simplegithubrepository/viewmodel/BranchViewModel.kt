package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.Branch
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class BranchViewModel(application: Application) : AndroidViewModel(application) {

    private var repo: MutableLiveData<ArrayList<Branch>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getBranches(userName : String, repoName : String) : MutableLiveData<ArrayList<Branch>>{
        if(repo == null){
            repo = MutableLiveData()
            fetchBranches(userName, repoName)
        }

        return repo as MutableLiveData<ArrayList<Branch>>
    }

    private fun fetchBranches(userName: String, repoName: String){
        mNetworkComponent.getBranches(userName, repoName).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                repo!!.postValue(it)
            },{it ->
                Log.d("error",  it.message)
            })
    }

}