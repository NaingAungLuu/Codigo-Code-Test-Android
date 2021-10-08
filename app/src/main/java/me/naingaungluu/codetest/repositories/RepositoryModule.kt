package me.naingaungluu.codetest.repositories

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.naingaungluu.codetest.data.sources.local.MovieDatabase
import me.naingaungluu.codetest.data.sources.remote.MovieRemoteDataSource
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieDatabase: MovieDatabase , movieRemoteDataSource: MovieRemoteDataSource) : MovieRepository {
        return  MovieRepositoryImpl(movieDatabase.getMovieItemDao() , movieRemoteDataSource)
    }
}