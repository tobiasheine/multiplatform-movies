package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MovieGalleryViewModel(
    private val uiContext: CoroutineContext,
    private val movieGalleryRepository: MovieGalleryRepository
) : CoroutineScope {

    private lateinit var job: Job
    private lateinit var listener: Listener

    override val coroutineContext: CoroutineContext
        get() = uiContext + job + CoroutineExceptionHandler { _, throwable ->
            job = Job()
            listener.onError(throwable)
        }

    fun loadMovies() = launch {
        val gallery = movieGalleryRepository.movieGallery()
        listener.onMovieGallery(gallery)
    }

    fun setListener(listener: Listener) {
        job = Job()
        this.listener = listener
        movieGalleryRepository.observeMovies {
            loadMovies()
        }
        refreshMovies()
    }

    private fun refreshMovies() = launch {
        movieGalleryRepository.refresh()
    }

    fun clear() {
        job.cancel()
    }

    interface Listener {
        fun onMovieGallery(movieGallery: MovieGallery)
        fun onError(throwable: Throwable)
    }
}