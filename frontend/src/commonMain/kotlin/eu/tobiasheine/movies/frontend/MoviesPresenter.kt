package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MoviesPresenter(
    private val uiContext: CoroutineContext,
    private val moviesBackend: MoviesBackend
) : CoroutineScope {

    private lateinit var job: Job
    private lateinit var view: View

    override val coroutineContext: CoroutineContext
        get() = uiContext + job + CoroutineExceptionHandler { _, throwable ->
            job = Job()
            view.showError(throwable)
        }

    fun bind(view: View) {
        this.view = view
        job = Job()

        launch {
            val gallery = moviesBackend.movieGallery()
            view.render(gallery)
        }
    }

    fun loadMovies() = launch {
        val gallery = moviesBackend.movieGallery()
        view.render(gallery)
    }

    fun setView(view: View) {
        job = Job()
        this.view = view
    }

    interface View {
        fun render(movieGallery: MovieGallery)
        fun showError(throwable: Throwable)
    }
}