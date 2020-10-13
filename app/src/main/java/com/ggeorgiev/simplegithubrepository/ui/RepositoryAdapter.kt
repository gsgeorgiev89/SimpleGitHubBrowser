package com.ggeorgiev.simplegithubrepository.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.SimpleUser
import com.ggeorgiev.simplegithubrepository.data.User
import com.ggeorgiev.simplegithubrepository.data.UserList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_row.view.*
import kotlinx.android.synthetic.main.user_row.view.*

class RepositoryAdapter(val user: User, val context: Context) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.repository_row, parent, false)
        return RepositoryAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingValues(user.repos!!.get(position))
    }

    override fun getItemCount(): Int {
        return user.repos!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingValues(get: Repository) {
            itemView.tvRepositoryName.text = get.name
        }
    }
}