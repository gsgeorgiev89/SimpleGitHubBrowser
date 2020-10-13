package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(var login : String,
           var email : String?,
           var id : Int,
           var node_id : String,
           var avatar_url : String,
           var followers : Int,
           var following : Int,
           var company : String?,
           var repos : ArrayList<Repository>?,
           var starred : ArrayList<Repository>?) : Parcelable