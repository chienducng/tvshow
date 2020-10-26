package com.chienducng.tvshow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.chienducng.tvshow.R
import com.chienducng.tvshow.databinding.ActivityTVShowDetailsBinding
import com.chienducng.tvshow.viewmodes.TVShowDetailsViewModel

class TVShowDetailsActivity : AppCompatActivity() {
    private lateinit var tvShowDetailsViewModel: TVShowDetailsViewModel
    private lateinit var activityTVShowDetailsBinding: ActivityTVShowDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTVShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_t_v_show_details)
        doInitialization()
    }

    private fun doInitialization() {
        tvShowDetailsViewModel = ViewModelProvider(this).get(TVShowDetailsViewModel::class.java)
        getTVShowDetails()
    }

    private fun getTVShowDetails() {
        activityTVShowDetailsBinding.isLoading = true
        val tvShowId : String = intent.getIntExtra("id", -1).toString();
        tvShowDetailsViewModel.getTVShowDetails(tvShowId).observe(this, {
            activityTVShowDetailsBinding.isLoading = false
            Toast.makeText(applicationContext, it.TVShowDetails.url, Toast.LENGTH_SHORT).show()
        })
    }
}