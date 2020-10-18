package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Issue (var title : String, var state : String, var created_at : String): Parcelable