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
        val cachedGallery = movieGalleryRepository.movieGallery()
        listener.onMovieGallery(cachedGallery)

        movieGalleryRepository.refresh()
        val freshGallery = movieGalleryRepository.movieGallery()
        if (freshGallery != cachedGallery) {
            listener.onMovieGallery(freshGallery)
        }

    }

    fun setListener(listener: Listener) {
        job = Job()
        this.listener = listener
    }

    fun clear() {
        job.cancel()
    }

    interface Listener {
        fun onMovieGallery(movieGallery: MovieGallery)
        fun onError(throwable: Throwable)
    }
}