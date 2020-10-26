package com.chienducng.tvshow.listeners

import com.chienducng.tvshow.models.TVShow

interface TVShowsListener {
    fun onTVShowsClicked(tvShow : TVShow)
}