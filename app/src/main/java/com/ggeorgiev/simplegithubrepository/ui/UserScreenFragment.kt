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
import com.ggeorgiev.simplegithubrepository.viewmodel.OwnedRepositoriesViewModel
import com.ggeorgiev.simplegithubrepository.viewmodel.StarredReposViewModel
import com.squareup.picasso.Picasso

class UserScreenFragment : Fragment() , RepositoryAdapter.OnItemClickListener{
    lateinit var rvRepos : RecyclerView
    lateinit var user : User
    private val viewModelOwned: OwnedRepositoriesViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(OwnedRepositoriesViewModel::class.java)
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
        val tvUsername = view.findViewById<TextView>(R.id.userScreenUsername)
        val tvFollowers = view.findViewById<TextView>(R.id.userScreenFollowers)
        val tvFollowing = view.findViewById<TextView>(R.id.userScreenFollowing)
        val imgAvatar = view.findViewById<ImageView>(R.id.userScreenAvatar)
        val llFollowers = view.findViewById<LinearLayout>(R.id.llFollowers)
        llFollowers.setOnClickListener {
            val acc = activity as AccInfoActivity
            val f = SearchFragment()
            val bundle = Bundle()
            bundle.putString("LOGIN", user.login)
            bundle.putString("USER_NAME", "followers:")
            f.arguments = bundle
            acc.selectFragment(f)
        }

        val llFollowing = view.findViewById<LinearLayout>(R.id.llFollowing)
        llFollowing.setOnClickListener {
            val acc = activity as AccInfoActivity
            val f = SearchFragment()
            val bundle = Bundle()
            bundle.putString("LOGIN", user.login)
            bundle.putString("USER_NAME", "following:")
            f.arguments = bundle
            acc.selectFragment(f)
        }

        rvRepos = view.findViewById(R.id.userScreenRepositories)
        rvRepos.layoutManager = LinearLayoutManager(context)
        viewModelOwned.getOwnedRepositories(user!!.login).observe(viewLifecycleOwner, Observer { t ->
                setDataInRecyclerViewOwned(t, RepositoryAdapter(user, this))
        })

        val rgRepoType = view.findViewById<RadioGroup>(R.id.userScreenRepositoryTypes)
        rgRepoType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.userScreenOwnedRepos -> viewModelOwned.getOwnedRepositories(user!!.login)
                    .observe(viewLifecycleOwner, Observer { t ->
                        setDataInRecyclerViewOwned(t, RepositoryAdapter(user, this))
                    })
                R.id.userScreenStarredRepos -> viewModelStarred.getStarredRepositories(user!!.login)
                    .observe(viewLifecycleOwner, Observer { t ->
                        setDataInRecyclerViewStarred(t, StarredAdapter(user, this))
                    })
            }
        }

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

    override fun onItemClick(repo : Repository) {
        val acc = activity as AccInfoActivity
        val f = RepositoryFragment()
        val bundle = Bundle()
        bundle.putParcelable("REPOSITORY", repo)
        f.arguments = bundle
        acc.selectFragment(f)
    }
}