package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contributor (var contributions : Int, var user : SimpleUser) : Parcelable