package com.ggeorgiev.simplegithubrepository.data


data class User(
    var  login : String,
    var email : String,
    var id : Int,
    var node_id : String,
    var avatar_url : String,
    var url : String,
    var followers : Int,
    var following : Int,
    var followers_url : String,
    var following_url : String,
    var starred_url : String,
    var repos_url : String,
    var company : String)