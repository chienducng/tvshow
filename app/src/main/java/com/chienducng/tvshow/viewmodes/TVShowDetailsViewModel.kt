package com.chienducng.tvshow.viewmodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.chienducng.tvshow.repositories.TVShowDetailsRepository
import com.chienducng.tvshow.response.TVShowDetailsResponse

class TVShowDetailsViewModel : ViewModel() {
    private var tvShowDetailsRepository : TVShowDetailsRepository = TVShowDetailsRepository()

    fun getTVShowDetails(tvShowId : String) : LiveData<TVShowDetailsResponse>{
        return tvShowDetailsRepository.getTVShowDetails(tvShowId)
    }
}