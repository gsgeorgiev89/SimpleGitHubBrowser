package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class SimpleUser(var login : String, var avatar_url : String):Parcelable