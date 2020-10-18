package com.ggeorgiev.simplegithubrepository.network

import com.ggeorgiev.simplegithubrepository.data.*
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
    fun getOwnedRepositories(@Path("userName") username : String): Observable<ArrayList<Repository>>

    @GET("users/{userName}/starred")
    fun getStarredRepositories(@Path("userName") username : String): Observable<ArrayList<Repository>>

    @GET("users/{userName}/followers")
    fun getFollowers(@Path("userName") userName: String) : Observable<ArrayList<SimpleUser>>

    @GET("users/{userName}/following")
    fun getFollowing(@Path("userName") userName: String) : Observable<ArrayList<SimpleUser>>

    @GET("search/users")
    fun getUsers(@Query("q") username : String) : Observable<UserList>

    @GET("repos/{userName}/{repoName}/branches")
    fun getBranches(@Path("userName") username: String, @Path("repoName") repoName: String) : Observable<ArrayList<Branch>>

    @GET("repos/{userName}/{repoName}/commits")
    fun getCommits(@Path("userName") username: String, @Path("repoName") repoName: String) : Observable<ArrayList<Commit>>

    @GET("repos/{userName}/{repoName}/issues")
    fun getIssues(@Path("userName") username: String, @Path("repoName") repoName: String) : Observable<ArrayList<Issue>>

    @GET("repos/{userName}/{repoName}/releases")
    fun getReleases(@Path("userName") username: String, @Path("repoName") repoName: String) : Observable<ArrayList<Release>>

    @GET("repos/{userName}/{repoName}/contributors")
    fun getContributors(@Path("userName") username: String, @Path("repoName") repoName: String) : Observable<ArrayList<Contributor>>
}