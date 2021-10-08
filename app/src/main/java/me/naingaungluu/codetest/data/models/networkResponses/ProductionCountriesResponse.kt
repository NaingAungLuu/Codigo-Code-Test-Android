package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName

data class ProductionCountriesResponse (

    @SerializedName("iso_3166_1") var iso31661 : String,
    @SerializedName("name") var name : String

)