package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName


data class ProductionCompaniesResponse (

    @SerializedName("id") var id : Int,
    @SerializedName("logo_path") var logoPath : String,
    @SerializedName("name") var name : String,
    @SerializedName("origin_country") var originCountry : String

)