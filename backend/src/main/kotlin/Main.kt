import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {

    install(CallLogging)

    install(ContentNegotiation) {
        serialization()
    }

    routing {
        get("/movies") {
            call.respond(
                MovieGallery(
                    items = listOf(
                        MovieGalleryItem(movieId = 1L, thumbnailUrl = "https://via.placeholder.com/150")
                    )
                )
            )
        }
    }
}