package com.chienducng.tvshow.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chienducng.tvshow.network.TvShowApiService
import com.chienducng.tvshow.response.MostPopularShowResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTVShowRepository {
    private val tvShowApiService by lazy {
        TvShowApiService.create()
    }

    fun getMostPopularShows (page: Int): LiveData<MostPopularShowResponse> {
        var data: MutableLiveData<MostPopularShowResponse> = MutableLiveData()
        tvShowApiService.getMostPopularTVShows(page).enqueue(object : Callback<MostPopularShowResponse> {
            override fun onFailure(call: Call<MostPopularShowResponse>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<MostPopularShowResponse>, response: Response<MostPopularShowResponse>) {
                data.value = response.body()
            }
        })
        return data
    }
}