package eu.tobiasheine.movies.data

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

expect val BASE_URL: String

class KtorMoviesBackend: MoviesBackend {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    override suspend fun movieGallery(): MovieGallery = client.get("$BASE_URL/movies")
}