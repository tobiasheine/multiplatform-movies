package eu.tobiasheine.movies.frontend

import com.squareup.sqldelight.Query
import eu.tobiasheine.movies.frontend.db.MoviesDb
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem

typealias MovieGalleryItemSQL = eu.tobiasheine.movies.frontend.db.MovieGalleryItem

interface MovieGalleryRepository {
    suspend fun movieGallery(): MovieGallery

    suspend fun refresh()

    fun observeMovies(onResultsChanged: () -> Unit)
}

class MovieGalleryRepositoryImpl(
    private val moviesDb: MoviesDb,
    private val moviesBackend: KtorMoviesBackend
) : MovieGalleryRepository {

    private val selectAllMovieItems = moviesDb.movieGalleryQueries.selectAllMovieItems()

    override fun observeMovies(onResultsChanged: () -> Unit) {
        selectAllMovieItems.addListener(object :Query.Listener {
            override fun queryResultsChanged() {
                onResultsChanged()
            }
        })
    }

    override suspend fun refresh() {
        moviesBackend
            .movieGallery()
            .persist(moviesDb)
    }

    override suspend fun movieGallery(): MovieGallery {
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
