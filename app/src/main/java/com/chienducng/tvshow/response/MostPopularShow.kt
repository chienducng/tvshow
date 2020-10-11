package com.chienducng.tvshow.response


import com.google.gson.annotations.SerializedName

data class MostPopularShow(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val tvShows: List<TvShow>
)