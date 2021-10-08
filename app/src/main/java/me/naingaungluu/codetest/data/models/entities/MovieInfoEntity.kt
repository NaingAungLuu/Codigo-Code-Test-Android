package me.naingaungluu.codetest.data.models.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Movie info entity
 *
 * This entity class contains the same properties as Movie Class except for [isFavorite] field
 * I created a separate class so that I can save network responses without effecting isFavourite information
 */
@Entity
data class MovieInfoEntity(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var title : String = "",
    var coverImageUrl : String = "",
    var rating : Double = 0.0,
    var releaseDate : String = "",
    var genres : String = "",
    var description : String = ""
) {

}