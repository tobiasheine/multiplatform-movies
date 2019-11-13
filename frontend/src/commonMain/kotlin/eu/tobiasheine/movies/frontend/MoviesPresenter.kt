package eu.tobiasheine.movies.frontend

import com.autodesk.coroutineworker.CoroutineWorker.Companion.withContext
import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MoviesPresenter(
    private val uiContext: CoroutineContext,
    private val ioContext: CoroutineContext,
    private val moviesBackend: MoviesBackend
) : CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = uiContext + job + CoroutineExceptionHandler { _, throwable ->
            job = Job()
        }

    fun bind(view: View) {
        launch {
            val movieGallery = withContext(ioContext) {
                moviesBackend.movieGallery()
            }
            view.render(movieGallery)
        }
    }

    interface View {
        fun render(movieGallery: MovieGallery)
    }
}