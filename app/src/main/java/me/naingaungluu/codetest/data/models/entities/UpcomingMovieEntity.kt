package me.naingaungluu.codetest.data.models.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import me.naingaungluu.codetest.data.models.domain.Movie

/**
 * Upcoming Movie Entity
 *
 * @property movieId - Movie Id
 */
@Entity(tableName = "upcoming_movies")
data class UpcomingMovieEntity (
    @PrimaryKey(autoGenerate = true)
    var movieId : Int = 0,
){
}

/**
 * Upcoming movie entity with movie item
 *
 * This binds the upcoming movie's id to the movie object
 *
 * @property entity [UpcomingMovieEntity]
 * @property movie [Movie]
 */
data class UpcomingMovieEntityWithMovieItem(
    @Embedded
    var entity : UpcomingMovieEntity,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    var movie : Movie? = Movie()
) {

}