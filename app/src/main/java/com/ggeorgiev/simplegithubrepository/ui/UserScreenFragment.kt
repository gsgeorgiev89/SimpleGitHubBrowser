package com.ggeorgiev.simplegithubrepository.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_user_screen.*
import java.io.InputStream
import java.net.URL

class UserScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = arguments
        val user = args!!.getParcelable<User>("USER_OBJ")

        val view = inflater.inflate(R.layout.fragment_user_screen, container, false)
        val tvUsername = view.findViewById<TextView>(R.id.tvUsername)
        val tvFollowers = view.findViewById<TextView>(R.id.tvFollowers)
        val tvFollowing = view.findViewById<TextView>(R.id.tvFollowing)
        val imgAvatar = view.findViewById<ImageView>(R.id.imgAvatar)
        val rView = view.findViewById<RecyclerView>(R.id.rvRepositories)
        if (user != null) {
            tvUsername.text = user.login
            tvFollowers.setText(user.followers.toString())
            tvFollowing.setText(user.following.toString())
            Picasso.get().load(user.avatar_url).into(imgAvatar)


        }

        return view
    }
}