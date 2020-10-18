package com.ggeorgiev.simplegithubrepository.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
class Release (var name : String, var tag_name : String, var author : SimpleUser) : Parcelable