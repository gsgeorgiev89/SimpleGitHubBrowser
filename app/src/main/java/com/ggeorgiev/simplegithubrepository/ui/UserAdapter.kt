package com.ggeorgiev.simplegithubrepository.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.SimpleUser
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_row.view.*

class UserAdapter(val users: UserList, val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_row, parent, false)
        return UserAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingValues(users.users.get(position))
    }

    override fun getItemCount(): Int {
        return users.users.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingValues(get: SimpleUser) {
            itemView.tvName.text = get.login
            Picasso.get().load(get.avatar_url).into(itemView.imgSearchUserAvatar)
        }
    }
}