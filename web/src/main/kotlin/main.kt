import kotlin.browser.document
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem

fun main() {
    val gallery = MovieGallery(
        items = listOf(
            MovieGalleryItem(
                movieId = 1L,
                thumbnailUrl = "https://image.tmdb.org/t/p/w1280/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
            )
        )
    )
    document.write(gallery.toString())
}