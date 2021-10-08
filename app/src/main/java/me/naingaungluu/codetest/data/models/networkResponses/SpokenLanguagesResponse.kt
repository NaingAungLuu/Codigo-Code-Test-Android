package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName

data class SpokenLanguagesResponse (

    @SerializedName("english_name") var englishName : String,
    @SerializedName("iso_639_1") var iso6391 : String,
    @SerializedName("name") var name : String

)