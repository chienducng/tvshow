package com.chienducng.tvshow.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chienducng.tvshow.network.TvShowApiService
import com.chienducng.tvshow.response.TVShowDetailsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowDetailsRepository {
    private val apiService : TvShowApiService by lazy {
        TvShowApiService.create()
    }

    fun getTVShowDetails(tvShowId: String) : LiveData<TVShowDetailsResponse> {
        var data : MutableLiveData<TVShowDetailsResponse> = MutableLiveData()
        apiService.getTVShowDetails(tvShowId).enqueue(object : Callback<TVShowDetailsResponse> {
            override fun onResponse(call: Call<TVShowDetailsResponse>, response: Response<TVShowDetailsResponse>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<TVShowDetailsResponse>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}