package eu.tobiasheine.movies.frontend

object DependencyProvider {

    fun provideViewModel() =
        MovieGalleryViewModel(
            uiContext = UI,
            moviesBackend = KtorMoviesBackend()
        )
}