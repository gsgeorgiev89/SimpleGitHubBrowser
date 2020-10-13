package com.ggeorgiev.simplegithubrepository.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.ggeorgiev.simplegithubrepository.viewmodel.UsersViewModel

class SearchFragment : Fragment() {
    lateinit var rvUsers : RecyclerView
    private val viewModel: UsersViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(UsersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val args = arguments
        val userName = args!!.getString("USER_NAME")

        rvUsers = view.findViewById(R.id.rvSearchedUsers)


        if(userName != null) {
            viewModel.getUsers(userName).observe(this.viewLifecycleOwner, Observer { t ->
                setDataInRecyclerView(t)
            })
        }

        return view
    }

    private fun setDataInRecyclerView(it: UserList?) {
        rvUsers.layoutManager = LinearLayoutManager(context)
        rvUsers.adapter = context?.let { it1 -> UserAdapter(it!!, it1) }
    }
}