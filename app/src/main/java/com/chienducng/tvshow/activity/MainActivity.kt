package com.chienducng.tvshow.activity

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
import com.chienducng.tvshow.models.TvShow
import com.chienducng.tvshow.viewmodes.MostPopularTVShowViewModel

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private lateinit var mostPopularTVShowViewModel : MostPopularTVShowViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private var tvShows : ArrayList<TvShow> = ArrayList()
    private val tvShowsAdapter by lazy {
        TVShowsAdapter(tvShows)
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
                val oldCount = tvShows.size
                tvShows.addAll(it.tvShows)
                tvShowsAdapter.notifyItemRangeInserted(oldCount, tvShows.size)
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
}