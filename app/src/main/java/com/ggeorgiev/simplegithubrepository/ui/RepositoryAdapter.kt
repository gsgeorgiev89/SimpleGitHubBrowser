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

class RepositoryAdapter(val user: User, val listner : OnItemClickListener) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingValues(user.repos!!.get(position))
    }

    override fun getItemCount(): Int {
        if (user.repos == null) {
            return 0
        }
        return user.repos!!.size
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
                listner.onItemClick(user.repos!![pos])
        }
    }

    interface OnItemClickListener{
        fun onItemClick(repo : Repository)
    }
}