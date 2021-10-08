package me.naingaungluu.codetest.data.sources.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.data.models.entities.MovieInfoEntity
import me.naingaungluu.codetest.data.models.entities.PopularMovieEntity
import me.naingaungluu.codetest.data.models.entities.UpcomingMovieEntity

@Database(
    version = 1,
    entities = [
        Movie::class,
        PopularMovieEntity::class,
        UpcomingMovieEntity::class,
        MovieInfoEntity::class
    ]
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieItemDao() : MovieDao

    companion object {
        private const val DB_NAME = "MovieDatabase.db"
        private var databaseInstance : MovieDatabase? = null

        fun getInstance(context : Context) : MovieDatabase = synchronized(this) {
            if(databaseInstance == null) databaseInstance = initDatabase(context)
            return  databaseInstance!!
        }

        private fun initDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .enableMultiInstanceInvalidation()
                .build()
    }

}