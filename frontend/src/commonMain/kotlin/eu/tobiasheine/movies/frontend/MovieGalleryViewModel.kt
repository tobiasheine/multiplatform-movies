package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieGalleryViewModel(
    private val uiContext: CoroutineContext,
    private val moviesBackend: MoviesBackend
) : CoroutineScope {

    private lateinit var job: Job
    private lateinit var listerner: Listener

    override val coroutineContext: CoroutineContext
        get() = uiContext + job + CoroutineExceptionHandler { _, throwable ->
            job = Job()
            listerner.onError(throwable)
        }

    fun loadMovies() = launch {
        val gallery = moviesBackend.movieGallery()
        listerner.onMovieGallery(gallery)
    }

    fun setListener(listener: Listener) {
        job = Job()
        this.listerner = listener
    }

    fun clear() {
        job.cancel()
    }

    interface Listener {
        fun onMovieGallery(movieGallery: MovieGallery)
        fun onError(throwable: Throwable)
    }
}