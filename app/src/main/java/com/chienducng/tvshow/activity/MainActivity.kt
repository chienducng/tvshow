package com.chienducng.tvshow.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.chienducng.tvshow.R
import com.chienducng.tvshow.viewmodes.MostPopularTVShowViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mostPopularTVShowViewModel : MostPopularTVShowViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mostPopularTVShowViewModel = ViewModelProvider(this).get(MostPopularTVShowViewModel::class.java)
        getMostPopularTVShows()
    }

    private fun getMostPopularTVShows() {
        mostPopularTVShowViewModel.getMostPopularTVShows(0).observe(this, Observer {
            Toast.makeText(this, "Total pages: ${it.pages}", Toast.LENGTH_SHORT).show()
        })
    }
}