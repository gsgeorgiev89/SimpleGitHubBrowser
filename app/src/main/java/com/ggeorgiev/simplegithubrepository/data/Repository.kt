package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(var name: String,
                      var stargazers_count: Int,
                      var size : Int,
                      var forks : Int,
                      var language: String?) : Parcelable