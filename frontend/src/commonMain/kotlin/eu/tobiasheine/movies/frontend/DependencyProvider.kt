package eu.tobiasheine.movies.frontend

import co.touchlab.stately.concurrency.AtomicReference
import co.touchlab.stately.freeze
import com.squareup.sqldelight.db.SqlDriver
import eu.tobiasheine.movies.frontend.db.MoviesDb
import eu.tobiasheine.movies.data.KtorMoviesBackend
import kotlin.reflect.KProperty

object DependencyProvider {

    private var sqlDriver: SqlDriver by FrozenDelegate()

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

internal class FrozenDelegate<T>{
    private val delegateReference = AtomicReference<T?>(null)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = delegateReference.get()!!

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        delegateReference.set(value.freeze())
    }
}