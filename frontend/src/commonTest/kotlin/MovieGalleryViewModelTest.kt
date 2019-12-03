import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem
import eu.tobiasheine.movies.frontend.MovieGalleryRepository
import eu.tobiasheine.movies.frontend.MovieGalleryViewModel
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieGalleryViewModelTest {

    private val emptyMovieGallery = MovieGallery(emptyList())
    private val singleItemMovieGallery = MovieGallery(
        items = listOf(
            MovieGalleryItem(movieId = 1L, thumbnailUrl = "some url")
        )
    )

    private val repository = TestMovieGalleryRepository(
        cachedMovieGallery = emptyMovieGallery,
        freshMovieGallery = singleItemMovieGallery
    )
    private val listener = object : MovieGalleryViewModel.Listener {

        private val receivedGalleries: MutableList<MovieGallery> = mutableListOf()

        override fun onMovieGallery(movieGallery: MovieGallery) {
            receivedGalleries.add(movieGallery)
        }

        fun verify(expectedMovieGalleries: List<MovieGallery>) {
            assertEquals(expectedMovieGalleries, receivedGalleries)
        }

        override fun onError(throwable: Throwable) {
            TODO("no error handling implemented")
        }

    }
    private val viewModel = MovieGalleryViewModel(Dispatchers.Unconfined, repository as MovieGalleryRepository)

    @Test
    fun render_cached_and_fresh_movieGallery_from_backend() = suspendTest {
        viewModel.setListener(listener)

        viewModel.loadMovies().join()

        listener.verify(listOf(emptyMovieGallery, singleItemMovieGallery))
    }

    @Test
    fun given_cached_gallery_up_to_date_then_skip_fresh_one() = suspendTest {
        repository.updateState(
            cachedMovieGallery = singleItemMovieGallery,
            freshMovieGallery = singleItemMovieGallery
        )
        viewModel.setListener(listener)

        viewModel.loadMovies().join()

        listener.verify(listOf(singleItemMovieGallery))
    }

}

class TestMovieGalleryRepository(
    private var cachedMovieGallery: MovieGallery,
    private var freshMovieGallery: MovieGallery
) : MovieGalleryRepository {

    private var currentGallery = cachedMovieGallery


    fun updateState(cachedMovieGallery: MovieGallery, freshMovieGallery: MovieGallery) {
        currentGallery = cachedMovieGallery
        this.cachedMovieGallery = cachedMovieGallery
        this.freshMovieGallery = freshMovieGallery
    }

    override suspend fun movieGallery() = runBlocking(EmptyCoroutineContext) {
        currentGallery
    }

    override suspend fun refresh() = runBlocking(EmptyCoroutineContext) {
        currentGallery = freshMovieGallery
    }

}