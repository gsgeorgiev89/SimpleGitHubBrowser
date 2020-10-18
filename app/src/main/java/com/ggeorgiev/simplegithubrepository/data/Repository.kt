package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import android.widget.SimpleExpandableListAdapter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(var name: String,
                      var stargazers_count: Int,
                      var forks_count : Int,
                      var language: String?,
                      var contributors : ArrayList<Contributor>?,
                      var branches : ArrayList<Branch>?,
                      var commits : ArrayList<Commit>?,
                      var releases : ArrayList<Release>?,
                      var issues : ArrayList<Issue>?,
                    var owner: SimpleUser) : Parcelable