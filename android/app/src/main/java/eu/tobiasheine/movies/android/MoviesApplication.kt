package eu.tobiasheine.movies.android

import android.app.Application

import eu.tobiasheine.movies.frontend.DependencyProvider
import eu.tobiasheine.movies.frontend.MoviesDBSQLDriver

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        DependencyProvider.initDependencyProvider(
            sqlDriver = MoviesDBSQLDriver.create(this)
        )
    }
}
