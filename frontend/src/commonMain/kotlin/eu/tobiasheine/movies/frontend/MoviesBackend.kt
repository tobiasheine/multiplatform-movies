package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get

private const val BASE_URL: String = "http://10.0.2.2:8080"

class MoviesBackend {

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun movieGallery(): MovieGallery = client.get("$BASE_URL/movies")
}