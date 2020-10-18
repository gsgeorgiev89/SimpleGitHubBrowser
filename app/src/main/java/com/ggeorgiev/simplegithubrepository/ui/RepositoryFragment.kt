package com.ggeorgiev.simplegithubrepository.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ggeorgiev.simplegithubrepository.R
import com.ggeorgiev.simplegithubrepository.data.*
import com.ggeorgiev.simplegithubrepository.viewmodel.*
import kotlinx.android.synthetic.main.fragment_repository.*

class RepositoryFragment : Fragment() {

    lateinit var repo : Repository

    val viewModelBranches : BranchViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(BranchViewModel::class.java)
    }

    val viewModelReleases : ReleaseViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(ReleaseViewModel::class.java)
    }

    val viewModelCommits : CommitViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(CommitViewModel::class.java)
    }

    val viewModelContributors : ContributorViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(ContributorViewModel::class.java)
    }

    val viewModelIssues : IssueViewModel by lazy{
        ViewModelProvider.AndroidViewModelFactory(activity!!.application).create(IssueViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_repository, container, false)

        val args = arguments
        repo = args!!.getParcelable("REPOSITORY")!!
        val login = repo!!.owner.login

        viewModelBranches.getBranches(login!!, repo!!.name).observe(this.viewLifecycleOwner,
            Observer {
                    t -> countBranches(t) })

        viewModelReleases.getReleases(login!!, repo!!.name).observe(this.viewLifecycleOwner,
            Observer {t -> countReleases(t) })

        viewModelCommits.getCommits(login!!, repo!!.name).observe(this.viewLifecycleOwner,
            Observer {t -> countCommits(t) })

        viewModelContributors.getContributors(login!!, repo!!.name).observe(this.viewLifecycleOwner,
            Observer {t -> countContributors(t) })

        viewModelIssues.getIssues(login!!, repo!!.name).observe(this.viewLifecycleOwner,
            Observer {t ->
                repo.issues = t
                showIssues(t) })

        val repositoryName = view.findViewById<TextView>(R.id.repositoryName)
        val repositoryFork = view.findViewById<TextView>(R.id.repositoryFork)
        val repositoryLanguages = view.findViewById<TextView>(R.id.repositoryLanguages)
        val repositoryStar = view.findViewById<TextView>(R.id.repositoryStar)

        if(repo != null) {
            repositoryFork.text = repo.forks_count.toString()
            repositoryLanguages.text = repo.language
            repositoryName.text = repo.name
            repositoryStar.text = repo.stargazers_count.toString()
        }

        val rgRepoType = view.findViewById<RadioGroup>(R.id.repositoryIssueTypes)
        rgRepoType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.repositoryAll -> showIssues(repo.issues!!)
                R.id.repositoryOpened -> showIssues(ArrayList(repo.issues!!.filter { it -> it.state == "open" }))
                R.id.repositoryClosed -> showIssues(ArrayList(repo.issues!!.filter { it -> it.state == "close" }))
            }
        }

        return view
    }

    private fun countBranches(branches : ArrayList<Branch>){
        repo.branches = branches
        repositoryBranches.text = repo.branches!!.size.toString()
    }

    private fun countReleases(releases : ArrayList<Release>){
        repo.releases = releases
        repositoryRelease.text = repo.releases!!.size.toString()
    }

    private fun countCommits(commits : ArrayList<Commit>){
        repo.commits = commits
        repositoryCommits.text = repo.commits!!.size.toString()
    }

    private fun countContributors(contributors : ArrayList<Contributor>){
        repo.contributors = contributors
        repositoryContributors.text = repo.contributors!!.size.toString()
    }

    private fun showIssues(issues : ArrayList<Issue>){
        repositoryIssuesList.layoutManager = LinearLayoutManager(context)
        repositoryIssuesList.adapter = IssueAdapter(issues)
    }
}