package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test

class MoviePresenterTest {

    private val movieGallery = MovieGallery(emptyList())
    private val backend = object : MoviesBackend {
        override suspend fun movieGallery(): MovieGallery = runBlocking(EmptyCoroutineContext) {
            MovieGallery(emptyList())
        }
    }
    private val view = object : MoviesPresenter.View {

        private lateinit var movieGallery: MovieGallery

        override fun render(movieGallery: MovieGallery) {
            this.movieGallery = movieGallery
        }

        fun verifyDidRender(expectedMovieGallery: MovieGallery) {
            assert(movieGallery == expectedMovieGallery)
        }

        override fun showError(throwable: Throwable) {
            TODO("no error handling implemented")
        }

    }
    private val presenter = MoviesPresenter(Dispatchers.Unconfined, backend)

    @Test
    fun render_movieGallery_from_backend() = suspendTest {
        presenter.setView(view)

        presenter.loadMovies().join()

        view.verifyDidRender(movieGallery)
    }

}

fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(context, block)

fun <T> suspendTest(block: suspend CoroutineScope.() -> T): T =
    kotlinx.coroutines.runBlocking(EmptyCoroutineContext, block)
