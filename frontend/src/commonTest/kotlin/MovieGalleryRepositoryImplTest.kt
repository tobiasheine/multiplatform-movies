import com.squareup.sqldelight.db.SqlDriver
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem
import eu.tobiasheine.movies.frontend.MovieGalleryRepository
import eu.tobiasheine.movies.frontend.MovieGalleryRepositoryImpl
import eu.tobiasheine.movies.data.MoviesBackend
import eu.tobiasheine.movies.frontend.db.MoviesDb
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class MovieGalleryRepositoryImplTest {

    private val cachedGallery = MovieGallery(
        listOf(
            MovieGalleryItem(1L, "some url")
        )
    )

    private val remoteGallery = MovieGallery(
        listOf(
            MovieGalleryItem(1L, "some url"),
            MovieGalleryItem(2L, "another url")
        )
    )

    private val moviesBackend: MoviesBackend = object : MoviesBackend {
        override suspend fun movieGallery() = runBlocking(EmptyCoroutineContext) {
            remoteGallery
        }

    }
    private lateinit var sqlDriver: SqlDriver
    private lateinit var moviesDb: MoviesDb
    private lateinit var repository: MovieGalleryRepository

    @BeforeTest
    fun setUp() {
        sqlDriver = createInMemorySqlDriver()
        moviesDb = MoviesDb(sqlDriver)
        repository = MovieGalleryRepositoryImpl(
            moviesDb, moviesBackend
        )

        cachedGallery.items.map {
            moviesDb.movieGalleryQueries.insertMovieItem(it.movieId, it.thumbnailUrl)
        }
    }

    @Test
    fun given_database_has_gallery_then_return_cached_gallery() = suspendTest {
        val movieGallery = repository.movieGallery()

        assertEquals(cachedGallery, movieGallery)
    }

    @Test
    fun given_backend_has_fresh_gallery_then_update_database() = suspendTest {
        repository.refresh()
        val movieGallery = repository.movieGallery()

        assertEquals(remoteGallery, movieGallery)
    }

    @AfterTest
    fun tearDown() {
        sqlDriver.close()
    }
}