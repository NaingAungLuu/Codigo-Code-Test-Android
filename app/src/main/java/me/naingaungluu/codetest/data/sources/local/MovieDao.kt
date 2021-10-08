package me.naingaungluu.codetest.data.sources.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.data.models.entities.*

@Dao
interface MovieDao {


    //Fetching
    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies() : Flow<List<PopularMovieEntityWithMovieItem>>

    @Query("SELECT * FROM upcoming_movies")
    fun getUpcomingMovies() : Flow<List<UpcomingMovieEntityWithMovieItem>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieDetails(movieId : Int) : Flow<Movie>


    //Insertion & Updating
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPopularMovies(items : List<PopularMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUpcomingMovies(items : List<UpcomingMovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieItems(items : List<Movie>)

    @Update(entity = Movie::class)
    fun insertMovie(item : MovieInfoEntity)

    @Query("UPDATE movies SET isFavourite = :isFavourite WHERE id = :movieId")
    fun setFavouriteMovie(movieId : Int , isFavourite : Boolean)


    //Deletion
    @Query("DELETE FROM movies")
    fun deleteAll()

}