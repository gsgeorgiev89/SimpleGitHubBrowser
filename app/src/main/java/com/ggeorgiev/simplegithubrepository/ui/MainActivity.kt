package com.ggeorgiev.simplegithubrepository.ui

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.network.JsonPlaceHolderApi
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    lateinit var jsonPlaceHolderApi: JsonPlaceHolderApi

    lateinit var etToken: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etToken = findViewById(R.id.etToken)
        etToken.setText("bd95113083ed706709a2284156ea9f406409cad9")

    }

    fun onClick(view: View?) {
        val networkComponent = NetworkComponent()
        jsonPlaceHolderApi = networkComponent.getRetrofit().create(JsonPlaceHolderApi::class.java)

        login()
    }

    private fun login() {
        var call = jsonPlaceHolderApi.Login("Bearer " + etToken.text)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (!response.isSuccessful) {
                    Toast.makeText(baseContext, "CODE: " + response.code(), Toast.LENGTH_LONG)
                        .show()
                    return
                } else {
                    val user = response.body()
                    if (user != null) {
                        val intent = Intent(baseContext, AccInfoActivity::class.java)
                        intent.putExtra("USER_OBJ",  user)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}