package com.ggeorgiev.simplegithubrepository.data

data class Repository(var name: String,
                      var owner_name: String,
                      var stargazers_count: Int,
                      var size : Int,
                      var forks : Int,
                      var language: String?)