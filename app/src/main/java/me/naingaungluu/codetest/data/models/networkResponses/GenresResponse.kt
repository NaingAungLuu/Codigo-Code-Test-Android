package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName

data class GenresResponse (
    @SerializedName("id") var id : Int,
    @SerializedName("name") var name : String
)