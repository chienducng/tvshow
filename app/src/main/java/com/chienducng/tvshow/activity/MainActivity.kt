package com.chienducng.tvshow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chienducng.tvshow.R
import com.chienducng.tvshow.adapters.TVShowsAdapter
import com.chienducng.tvshow.databinding.ActivityMainBinding
import com.chienducng.tvshow.models.TvShow
import com.chienducng.tvshow.viewmodes.MostPopularTVShowViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mostPopularTVShowViewModel : MostPopularTVShowViewModel
    private lateinit var activityMainBinding: ActivityMainBinding
    private var tvShows : ArrayList<TvShow> = ArrayList()
    private val tvShowsAdapter by lazy {
        TVShowsAdapter(tvShows)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        doInitialization()
    }

    private fun doInitialization(){
        activityMainBinding.tvShowsRecyclerView.setHasFixedSize(true)
        mostPopularTVShowViewModel = ViewModelProvider(this).get(MostPopularTVShowViewModel::class.java)
        activityMainBinding.tvShowsRecyclerView.adapter = tvShowsAdapter
        getMostPopularTVShows()
    }

    private fun getMostPopularTVShows() {
        activityMainBinding.isLoading = true
        mostPopularTVShowViewModel.getMostPopularTVShows(0).observe(this, Observer {
            activityMainBinding.isLoading = false
            if (it != null) {
                tvShows.addAll(it.tvShows)
                tvShowsAdapter.notifyDataSetChanged()
            }
        })
    }
}