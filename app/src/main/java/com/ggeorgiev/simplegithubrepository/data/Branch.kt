package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Branch (var name : String , var protectedStatus : Boolean) : Parcelable