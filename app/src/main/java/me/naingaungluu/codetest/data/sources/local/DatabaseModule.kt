package me.naingaungluu.codetest.data.sources.local

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): MovieDatabase {
       return MovieDatabase.getInstance(appContext)
    }

    @Provides
    fun provideMovieDao(appDatabase: MovieDatabase): MovieDao {
        return appDatabase.getMovieItemDao()
    }
}