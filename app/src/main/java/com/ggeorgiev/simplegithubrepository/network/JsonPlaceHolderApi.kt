package com.ggeorgiev.simplegithubrepository.network

import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface JsonPlaceHolderApi {
    @GET("user")
    fun Login(@Header("Authorization") token: String) : Call<User>

    @GET("users/{userName}/repos")
    fun getRepositories(@Path("userName") username : String): Call<Repository>
}