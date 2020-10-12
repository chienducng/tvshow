package com.chienducng.tvshow.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chienducng.tvshow.network.TvShowApiService
import com.chienducng.tvshow.response.MostPopularShow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTVShowRepository {
    private val tvShowApiService by lazy {
        TvShowApiService.create()
    }

    fun getMostPopularShows (page: Int): LiveData<MostPopularShow> {
        var data: MutableLiveData<MostPopularShow> = MutableLiveData()
        tvShowApiService.getMostPopularTVShows(page).enqueue(object : Callback<MostPopularShow> {
            override fun onFailure(call: Call<MostPopularShow>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(call: Call<MostPopularShow>, response: Response<MostPopularShow>) {
                data.value = response.body()
            }
        })
        return data
    }
}