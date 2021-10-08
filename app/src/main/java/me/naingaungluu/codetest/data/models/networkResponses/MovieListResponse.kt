package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    val page : Int = 0,

    @SerializedName("results")
    val results : List<MovieResponse>,

    @SerializedName("total_pages")
    val totalPages : Int = 0,

    @SerializedName("total_results")
    val totalResults : Int = 0

) {
}