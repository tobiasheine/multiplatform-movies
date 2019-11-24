package eu.tobiasheine.movies.frontend

import eu.tobiasheine.movies.data.MovieGallery
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

expect val BASE_URL: String

class MoviesBackend {

    fun movieGallery(): MovieGallery = simpleGet("$BASE_URL/movies").let {
        val json = Json(JsonConfiguration.Stable.copy(unquoted = true))
        json.parse(MovieGallery.serializer(), it)
    }
}

internal expect fun simpleGet(url: String): String