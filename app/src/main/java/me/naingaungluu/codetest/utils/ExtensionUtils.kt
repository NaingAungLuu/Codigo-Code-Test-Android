package me.naingaungluu.codetest.utils

import me.naingaungluu.codetest.data.models.domain.Movie
import me.naingaungluu.codetest.data.models.entities.MovieInfoEntity
import me.naingaungluu.codetest.data.models.networkResponses.MovieResponse

fun List<MovieResponse>.toMovieList(): List<Movie> = this.map { it.toMovie() }
fun List<Movie>.toMovieInfoEntities(): List<MovieInfoEntity> = this.map { it.toMovieInfoEntity() }