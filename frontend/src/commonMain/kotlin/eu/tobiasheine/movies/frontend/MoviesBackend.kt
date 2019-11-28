package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery

interface MoviesBackend {

    suspend fun movieGallery(): MovieGallery
}
