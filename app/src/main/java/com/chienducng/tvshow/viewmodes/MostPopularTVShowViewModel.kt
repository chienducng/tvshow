package com.chienducng.tvshow.viewmodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chienducng.tvshow.repositories.MostPopularTVShowRepository
import com.chienducng.tvshow.response.MostPopularShowResponse

class MostPopularTVShowViewModel : ViewModel() {
    private var mostPopularTVShowRepository : MostPopularTVShowRepository = MostPopularTVShowRepository()

    fun getMostPopularTVShows(page: Int) : LiveData<MostPopularShowResponse> {
        return mostPopularTVShowRepository.getMostPopularShows(page)
    }

}