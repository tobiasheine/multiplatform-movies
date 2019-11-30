package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test

class MovieGalleryViewModelTest {

    private val movieGallery = MovieGallery(emptyList())

    private val repository = object : MovieGalleryRepository {

        override suspend fun refresh() {}

        override fun observeMovies(onResultsChanged: () -> Unit) {}

        override suspend fun movieGallery(): MovieGallery = runBlocking(EmptyCoroutineContext) {
            MovieGallery(emptyList())
        }
    }
    private val listener = object : MovieGalleryViewModel.Listener {

        private lateinit var movieGallery: MovieGallery

        override fun onMovieGallery(movieGallery: MovieGallery) {
            this.movieGallery = movieGallery
        }

        fun verify(expectedMovieGallery: MovieGallery) {
            assert(movieGallery == expectedMovieGallery)
        }

        override fun onError(throwable: Throwable) {
            TODO("no error handling implemented")
        }

    }
    private val viewModel = MovieGalleryViewModel(Dispatchers.Unconfined, repository)

    @Test
    fun render_movieGallery_from_backend() = suspendTest {
        viewModel.setListener(listener)

        viewModel.loadMovies().join()

        listener.verify(movieGallery)
    }

}

fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(context, block)

fun <T> suspendTest(block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(EmptyCoroutineContext, block)
