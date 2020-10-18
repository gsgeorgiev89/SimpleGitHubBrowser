package com.ggeorgiev.simplegithubrepository.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.Issue
import com.ggeorgiev.simplegithubrepository.data.SimpleUser
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.issue_row.view.*
import kotlinx.android.synthetic.main.user_row.view.*

class IssueAdapter(val issue: ArrayList<Issue>) : RecyclerView.Adapter<IssueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.issue_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindingValues(issue.get(position))
    }

    override fun getItemCount(): Int {
        return issue.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindingValues(get: Issue) {
            itemView.issueRowName.text = get.title
            itemView.issueRowCreatedAt.text = get.created_at
        }
    }
}