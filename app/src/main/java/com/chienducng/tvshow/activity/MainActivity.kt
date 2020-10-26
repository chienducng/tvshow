package com.chienducng.tvshow.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.chienducng.tvshow.R
import com.chienducng.tvshow.adapters.TVShowsAdapter
import com.chienducng.tvshow.databinding.ActivityMainBinding
import com.chienducng.tvshow.listeners.TVShowsListener
import com.chienducng.tvshow.models.TVShow
import com.chienducng.tvshow.viewmodes.MostPopularTVShowViewModel

class MainActivity : AppCompatActivity(), TVShowsListener {
    private val TAG: String = "MainActivity"
    private lateinit var mostPopularTVShowViewModel : MostPopularTVShowViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private var TVShows : ArrayList<TVShow> = ArrayList()
    private val tvShowsAdapter by lazy {
        TVShowsAdapter(TVShows, this)
    }
    private var currentPage = 1;
    private var totalAvailablePage = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        doInitialization()
    }

    private fun doInitialization(){
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true)
        mostPopularTVShowViewModel = ViewModelProvider(this).get(MostPopularTVShowViewModel::class.java)
        activityMainBinding.tvShowsRecyclerView.adapter = tvShowsAdapter
        activityMainBinding.tvShowsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!activityMainBinding.tvShowsRecyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePage) {
                        currentPage += 1;
                        getMostPopularTVShows()
                    }
                }
            }
        })
        getMostPopularTVShows()
    }

    private fun getMostPopularTVShows() {
        toggleLoading()
        mostPopularTVShowViewModel.getMostPopularTVShows(currentPage).observe(this, Observer{
            toggleLoading()
            if (it != null) {
                totalAvailablePage = it.pages
                Log.i(TAG, "total: $totalAvailablePage")
                val oldCount = TVShows.size
                TVShows.addAll(it.TVShows)
                tvShowsAdapter.notifyItemRangeInserted(oldCount, TVShows.size)
            }
        })
    }

    private fun toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.isLoading = !activityMainBinding.isLoading
        } else {
            activityMainBinding.isLoadingMore = !activityMainBinding.isLoadingMore
        }
    }

    override fun onTVShowsClicked(tvShow: TVShow) {
        val intent = Intent(applicationContext, TVShowDetailsActivity::class.java)
        intent.putExtra("id", tvShow.id)
        intent.putExtra("name", tvShow.name)
        intent.putExtra("startDate", tvShow.startDate)
        intent.putExtra("country", tvShow.country)
        intent.putExtra("network", tvShow.network)
        intent.putExtra("status", tvShow.status)
        startActivity(intent)
    }
}