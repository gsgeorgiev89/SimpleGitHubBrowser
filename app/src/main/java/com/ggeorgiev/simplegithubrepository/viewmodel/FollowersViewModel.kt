package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.SimpleUser
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FollowersViewModel(application: Application) : AndroidViewModel(application) {

    private var userList: MutableLiveData<ArrayList<SimpleUser>>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getFollowers(name : String) : MutableLiveData<ArrayList<SimpleUser>> {
        if(userList == null){
            userList = MutableLiveData()
            fetchFollowers(name)
        }

        return userList as MutableLiveData<ArrayList<SimpleUser>>
    }

    private fun fetchFollowers(name : String){
        mNetworkComponent.getFollowers(name).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userList!!.postValue(it)
            },{it ->
                Log.d("error", it.message)
            })
    }
}