package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Commit (var sha:  String, var commiter : SimpleUser) : Parcelable