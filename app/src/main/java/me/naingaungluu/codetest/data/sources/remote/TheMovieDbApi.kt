package me.naingaungluu.codetest.data.sources.remote

import me.naingaungluu.codetest.data.models.networkResponses.MovieDetailResponse
import me.naingaungluu.codetest.data.models.networkResponses.MovieListResponse
import me.naingaungluu.codetest.data.models.networkResponses.MovieResponse
import me.naingaungluu.codetest.utils.API_KEY
import retrofit2.Response
import retrofit2.http.*

/**
 * Retrofit Api Service
 *
 */
interface TheMovieDbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String = API_KEY) : Response<MovieListResponse>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") apiKey : String = API_KEY) : Response<MovieListResponse>

    @GET("movie/{movie_id}?api_key=$API_KEY")
    suspend fun getMovie(@Path("movie_id") movieId: Int) : Response<MovieDetailResponse>

}