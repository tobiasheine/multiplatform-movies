package eu.tobiasheine.movies

import eu.tobiasheine.movies.data.KtorMoviesBackend
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MoviesBackend
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import react.*
import react.dom.*
import kotlin.coroutines.CoroutineContext

class MoviesApp : RComponent<RProps, MoviesApp.AppState>(), CoroutineScope {
    private val moviesBackend: MoviesBackend = KtorMoviesBackend()
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job

    override fun componentDidMount() {
        launch {
            val movieGallery = moviesBackend.movieGallery()
            setState {
                this.gallery = movieGallery
            }
        }
    }

    override fun componentWillUnmount() {
        job.cancel()
    }

    override fun RBuilder.render() {
        div {
            h3 {
                label {
                    +"Movies:"
                }
                ul {
                    state.gallery?.items?.forEach { item ->
                        li {
                            img(src = item.thumbnailUrl) {}
                        }
                    }
                }
            }
        }
    }

    interface AppState : RState {
        var gallery: MovieGallery?
    }

}

fun RBuilder.moviesApp() = child(MoviesApp::class) {
}