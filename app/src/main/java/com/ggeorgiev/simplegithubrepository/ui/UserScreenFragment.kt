package com.ggeorgiev.simplegithubrepository.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.viewmodel.RepositoriesViewModel
import com.ggeorgiev.simplegithubrepository.viewmodel.StarredReposViewModel
import com.squareup.picasso.Picasso

class UserScreenFragment : Fragment() {
    lateinit var rvRepos : RecyclerView
    lateinit var user : User
    private val viewModel: RepositoriesViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(RepositoriesViewModel::class.java)
    }

    private val viewModelStarred: StarredReposViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(StarredReposViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = arguments
        user = args!!.getParcelable<User>("USER_OBJ")!!

        val view = inflater.inflate(R.layout.fragment_user_screen, container, false)
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val tvFollowers = view.findViewById<TextView>(R.id.tvFollowers)
        val tvFollowing = view.findViewById<TextView>(R.id.tvFollowing)
        val imgAvatar = view.findViewById<ImageView>(R.id.imgAvatar)
        rvRepos = view.findViewById(R.id.rvRepositories)
        rvRepos.layoutManager = LinearLayoutManager(context)
        viewModel.getRepositories(user!!.login).observe(viewLifecycleOwner, Observer { t ->
                setDataInRecyclerViewOwned(t, RepositoryAdapter(user, context!!))
        })

        val rgRepoType = view.findViewById<RadioGroup>(R.id.rgRepoType)
        rgRepoType.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.rbOwnedRepos ->viewModel.getRepositories(user!!.login).observe(viewLifecycleOwner, Observer { t ->
                    setDataInRecyclerViewOwned(t, RepositoryAdapter(user, context!!))
                })
                R.id.rbStarredRepos -> viewModelStarred.getStarredRepositories(user!!.login).observe(viewLifecycleOwner, Observer { t->
                    setDataInRecyclerViewStarred(t, StarredAdapter(user, context!!))
                })
            }
        })

        tvUsername.text = user.login
        tvFollowers.text = user.followers.toString()
        tvFollowing.text = user.following.toString()
        Picasso.get().load(user.avatar_url).into(imgAvatar)

        return view
    }

    private fun setDataInRecyclerViewOwned(it: ArrayList<Repository>?, adapter : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>) {
        user.repos = it
        rvRepos.adapter = adapter
    }

    private fun setDataInRecyclerViewStarred(it: ArrayList<Repository>?, adapter : RecyclerView.Adapter<StarredAdapter.ViewHolder>) {
        user.starred = it
        rvRepos.adapter = adapter
    }
}