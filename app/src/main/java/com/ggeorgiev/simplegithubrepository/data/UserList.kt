package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class UserList(@SerializedName("items") var users : ArrayList<SimpleUser>, var total_count : Int) : Parcelable