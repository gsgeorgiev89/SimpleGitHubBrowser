package com.ggeorgiev.simplegithubrepository.ui

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.User

class AccInfoActivity : AppCompatActivity() {
    lateinit var user: User
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_info)
        user = intent.getParcelableExtra("USER_OBJ")

        val f = UserScreenFragment();
        val bundle = Bundle()
        bundle.putParcelable("USER_OBJ", user)
        f.arguments = bundle
        selectFragment(f)

        searchView = findViewById(R.id.accountInfoSearchView)
        searchView.setOnCloseListener {
            val f = UserScreenFragment();
            val bundle = Bundle()
            bundle.putParcelable("USER_OBJ", user)

            f.arguments = bundle

            selectFragment(f)
            false
        }

        searchView.setOnSearchClickListener {

        }

        searchView.setOnClickListener {
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(qString: String): Boolean {
                return true
            }

            override fun onQueryTextSubmit(qString: String): Boolean {

                val f = SearchFragment()
                bundle.putString("USER_NAME", qString)
                f.arguments = bundle
                selectFragment(f)

                return true
            }
        })
    }


    fun selectFragment(f: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.AccountInfoFragment, f).addToBackStack(null)
        transaction.commit()
    }
}