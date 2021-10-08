package me.naingaungluu.codetest.data.models.networkResponses

import com.google.gson.annotations.SerializedName
import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.data.models.entities.MovieInfoEntity
import me.naingaungluu.codetest.data.sources.remote.RemoteDataSource

/**
 * Movie detail response
 *
 * Although we actually use 7 fields in our domain model,
 * we can see all the fields from network response are captured here
 * It is just to demonstrate the use of separate Data Models based on app's layer
 *
 */
data class MovieDetailResponse(
    @SerializedName("adult") var adult : Boolean,
    @SerializedName("backdrop_path") var backdropPath : String,
    @SerializedName("belongs_to_collection") var belongsToCollection : BelongsToCollectionResponse,
    @SerializedName("budget") var budget : Int,
    @SerializedName("genres") var genres : List<GenresResponse>,
    @SerializedName("homepage") var homepage : String,
    @SerializedName("id") var id : Int,
    @SerializedName("imdb_id") var imdbId : String,
    @SerializedName("original_language") var originalLanguage : String,
    @SerializedName("original_title") var originalTitle : String,
    @SerializedName("overview") var overview : String,
    @SerializedName("popularity") var popularity : Double,
    @SerializedName("poster_path") var posterPath : String,
    @SerializedName("production_companies") var productionCompanies : List<ProductionCompaniesResponse>,
    @SerializedName("production_countries") var productionCountries : List<ProductionCountriesResponse>,
    @SerializedName("release_date") var releaseDate : String,
    @SerializedName("revenue") var revenue : Int,
    @SerializedName("runtime") var runtime : Int,
    @SerializedName("spoken_languages") var spokenLanguages : List<SpokenLanguagesResponse>,
    @SerializedName("status") var status : String,
    @SerializedName("tagline") var tagline : String,
    @SerializedName("title") var title : String,
    @SerializedName("video") var video : Boolean,
    @SerializedName("vote_average") var voteAverage : Double,
    @SerializedName("vote_count") var voteCount : Int
) {

    fun toMovie() : Movie = Movie(
        id = id,
        title = title,
        coverImageUrl = RemoteDataSource.getImageUrl(posterPath),
        rating = voteAverage,
        releaseDate = releaseDate,
        genres = genres.map { it.name }.reduce { acc, s -> "$acc/$s" },
        description = overview
    )

    fun toMovieInfoEntity() : MovieInfoEntity = MovieInfoEntity(
        id = id,
        title = title,
        coverImageUrl = RemoteDataSource.getImageUrl(posterPath),
        rating = voteAverage,
        releaseDate = releaseDate,
        genres = genres.map { it.name }.reduce { acc, s -> "$acc/$s" },
        description = overview
    )

}