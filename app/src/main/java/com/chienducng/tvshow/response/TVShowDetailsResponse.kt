package com.chienducng.tvshow.response


import com.chienducng.tvshow.models.TVShowDetails
import com.google.gson.annotations.SerializedName

data class TVShowDetailsResponse(
    @SerializedName("tvShow")
    val TVShowDetails: TVShowDetails
)