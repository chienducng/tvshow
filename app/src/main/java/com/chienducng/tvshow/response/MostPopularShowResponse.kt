package com.chienducng.tvshow.response


import com.chienducng.tvshow.models.TVShow
import com.google.gson.annotations.SerializedName

data class MostPopularShowResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val TVShows: List<TVShow>
)