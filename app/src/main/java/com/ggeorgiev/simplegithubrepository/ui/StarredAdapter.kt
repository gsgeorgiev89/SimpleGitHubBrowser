package com.ggeorgiev.simplegithubrepository.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.Repository
import com.ggeorgiev.simplegithubrepository.data.User
import kotlinx.android.synthetic.main.repository_row.view.*

class StarredAdapter (val user: User, val context: Context) : RecyclerView.Adapter<StarredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.repository_row, parent, false)
        return StarredAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingValues(user.starred!!.get(position))
    }

    override fun getItemCount(): Int {
        if(user.starred == null){
            return 0
        }
        return user.starred!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingValues(get: Repository) {
            itemView.tvRepositoryName.text = get.name
        }
    }
}