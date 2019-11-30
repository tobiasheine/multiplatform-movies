package eu.tobiasheine.movies.frontend

import com.squareup.sqldelight.db.SqlDriver
import eu.tobiasheine.movies.frontend.db.MoviesDb

object DependencyProvider {

    private lateinit var sqlDriver: SqlDriver

    fun initDependencyProvider(sqlDriver: SqlDriver) {
        this.sqlDriver = sqlDriver
    }

    private fun provideRepository(): MovieGalleryRepository = MovieGalleryRepositoryImpl(
        moviesDb = MoviesDb(sqlDriver),
        moviesBackend = KtorMoviesBackend()
    )

    fun provideViewModel() =
        MovieGalleryViewModel(
            uiContext = UI,
            movieGalleryRepository = provideRepository()
        )
}