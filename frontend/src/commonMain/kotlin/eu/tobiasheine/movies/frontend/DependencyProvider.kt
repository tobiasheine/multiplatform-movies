package eu.tobiasheine.movies.frontend

object DependencyProvider {

    fun providePresenter(): MoviesPresenter =
        MoviesPresenter(
            UI,
            IO,
            MoviesBackend()
        )
}