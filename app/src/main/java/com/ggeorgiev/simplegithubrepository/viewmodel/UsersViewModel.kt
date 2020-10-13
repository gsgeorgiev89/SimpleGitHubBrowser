package com.ggeorgiev.simplegithubrepository.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private var userList: MutableLiveData<UserList>? = null
    private val mNetworkComponent by lazy{
        NetworkComponent.create()
    }

    fun getUsers(name : String) : MutableLiveData<UserList>{
        if(userList == null){
            userList = MutableLiveData()
            fetchUsers(name)
        }

        return userList as MutableLiveData<UserList>
    }

    private fun fetchUsers(name : String){
        mNetworkComponent.getUsers(name).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                userList!!.postValue(it)
            },{it ->
                Log.d("error", "errors")
            })
    }
}