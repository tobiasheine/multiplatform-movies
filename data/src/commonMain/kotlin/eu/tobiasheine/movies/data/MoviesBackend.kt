package eu.tobiasheine.movies.data

interface MoviesBackend {

    suspend fun movieGallery(): MovieGallery
}
