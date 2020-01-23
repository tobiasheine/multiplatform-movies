package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.frontend.db.MoviesDb
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem
import eu.tobiasheine.movies.data.MoviesBackend

typealias MovieGalleryItemSQL = eu.tobiasheine.movies.frontend.db.MovieGalleryItem

interface MovieGalleryRepository {
    suspend fun movieGallery(): MovieGallery

    suspend fun refresh()
}

class MovieGalleryRepositoryImpl(
    private val moviesDb: MoviesDb,
    private val moviesBackend: MoviesBackend
) : MovieGalleryRepository {

    override suspend fun refresh() {
        moviesBackend
            .movieGallery()
            .persist(moviesDb)
    }

    override suspend fun movieGallery(): MovieGallery {
        val selectAllMovieItems = moviesDb.movieGalleryQueries.selectAllMovieItems()
        return MovieGallery(selectAllMovieItems.executeAsList().asDataItems())
    }
}

private fun List<MovieGalleryItemSQL>.asDataItems(): List<MovieGalleryItem> {
    return map {
        MovieGalleryItem(
            it.id,
            it.thumbnailUrl
        )
    }
}

private fun MovieGallery.persist(moviesDb: MoviesDb) {
    items.forEach { item ->
        moviesDb.movieGalleryQueries.insertMovieItem(item.movieId, item.thumbnailUrl)
    }
}
