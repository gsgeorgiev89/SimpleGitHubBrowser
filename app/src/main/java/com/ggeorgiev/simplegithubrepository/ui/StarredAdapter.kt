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

class StarredAdapter (val user: User, val listner : RepositoryAdapter.OnItemClickListener) : RecyclerView.Adapter<StarredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.repository_row, parent, false)
        return ViewHolder(view)
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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{
        fun bindingValues(get: Repository) {
            itemView.repositoryRowName.text = get.name
        }

        init{
            itemView.setOnClickListener (this)
        }

        override fun onClick(p0: View?) {
            val pos = adapterPosition
            if(pos != RecyclerView.NO_POSITION)
                listner.onItemClick(user.starred!![pos])
        }
    }
}