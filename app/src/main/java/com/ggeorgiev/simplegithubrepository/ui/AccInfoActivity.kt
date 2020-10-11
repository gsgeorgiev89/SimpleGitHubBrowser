package com.ggeorgiev.simplegithubrepository.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.User


class AccInfoActivity : AppCompatActivity() {
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_info)
        user = intent.getParcelableExtra("USER_OBJ")

        var f = UserScreenFragment();
        val bundle = Bundle()
        bundle.putParcelable("USER_OBJ", user)
        f.arguments = bundle
        selectFragment(f)


    }

    fun selectFragment(f: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.fragment, f).addToBackStack(null)
        transaction.commit()
    }
}