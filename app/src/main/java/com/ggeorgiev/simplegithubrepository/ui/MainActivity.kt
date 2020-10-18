package com.ggeorgiev.simplegithubrepository.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.network.NetworkComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var etToken: EditText

    val mNetworkComponent by lazy {
        NetworkComponent.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etToken = findViewById(R.id.loginToken)
        etToken.setText("a1c4450bcea69b046a4b76a297d6bca0801f9f47")
    }

    fun onClick(view: View?) {
        login()
    }

    private fun login() {
        var call = mNetworkComponent.Login("Bearer " + etToken.text)

        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (!response.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "CODE: " + response.code() + " " + response.message(),
                        Toast.LENGTH_LONG
                    )
                        .show()
                    return
                } else {
                    val user = response.body()
                    if (user != null) {
                        val intent = Intent(baseContext, AccInfoActivity::class.java)
                        intent.putExtra("USER_OBJ", user)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}