package eu.tobiasheine.movies.frontend

import kotlinx.coroutines.Dispatchers

object DependencyProvider {

    fun providePresenter(): MoviesPresenter =
        MoviesPresenter(
            uiContext = Dispatchers.Main,
            ioContext = IO,
            moviesBackend = MoviesBackend()
        )
}