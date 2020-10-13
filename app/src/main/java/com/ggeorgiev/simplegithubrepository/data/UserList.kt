package com.ggeorgiev.simplegithubrepository.data

import com.google.gson.annotations.SerializedName

class UserList(@SerializedName("items") var users : List<SimpleUser>, var total_count : Int)