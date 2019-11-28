package eu.tobiasheine.movies.frontend

object DependencyProvider {

    fun providePresenter(): MoviesPresenter =
        MoviesPresenter(
            uiContext = UI,
            moviesBackend = KtorMoviesBackend()
        )
}