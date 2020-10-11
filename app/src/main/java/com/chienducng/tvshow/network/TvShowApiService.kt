package com.chienducng.tvshow.network

import com.chienducng.tvshow.response.MostPopularShow
import com.chienducng.tvshow.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApiService {

    @GET("most-popular")
    fun getMostPopularTVShows(@Query("page") page: Int) : Call<MostPopularShow>


    companion object {
        fun create() : TvShowApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(TvShowApiService::class.java)
        }
    }
}