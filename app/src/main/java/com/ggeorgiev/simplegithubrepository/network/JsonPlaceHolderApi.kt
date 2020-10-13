package com.ggeorgiev.simplegithubrepository.network

import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.data.UserList
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface JsonPlaceHolderApi {
    @GET("user")
    fun Login(@Header("Authorization") token: String) : Call<User>

    @GET("users/{userName}/repos")
    fun getRepositories(@Path("userName") username : String): Observable<ArrayList<Repository>>

    @GET("users/{userName}/starred")
    fun getStarredRepositories(@Path("userName") username : String): Observable<ArrayList<Repository>>

    @GET("search/users")
    fun getUsers(@Query("q") username : String) : Observable<UserList>


}