import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8090, module = Application::module).start(wait = true)
}

fun Application.module() {

    install(CallLogging)

    install(ContentNegotiation) {
        serialization()
    }

    routing {
        get("/movies") {
            call.response.header("Access-Control-Allow-Origin", "http://localhost:8080")

            call.respond(
                MovieGallery(
                    items = listOf(
                        MovieGalleryItem(
                            movieId = 1L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/udDclJoHjfjb8Ekgsd4FDteOkCU.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 2L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/vqzNJRH4YyquRiWxCCOH0aXggHI.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 3L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/vn94LlNrbUWIZZyAdmvUepFBeaY.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 4L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/lcq8dVxeeOqHvvgcte707K0KVx5.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 5L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 6L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/zfE0R94v1E8cuKAerbskfD3VfUt.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 7L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/uTALxjQU8e1lhmNjP9nnJ3t2pRU.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 8L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/r15SUgzjL8bablcdEkHk9T7TSRl.jpg"
                        ),
                        MovieGalleryItem(
                            movieId = 9L,
                            thumbnailUrl = "https://image.tmdb.org/t/p/w1280/8j58iEBw9pOXFD2L0nt0ZXeHviB.jpg"
                        )
                    )
                )
            )
        }
    }
}