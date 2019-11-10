package eu.tobiasheine.movies.data

import kotlinx.serialization.Serializable

@Serializable
data class MovieGallery(val items: List<MovieGalleryItem>)

@Serializable
data class MovieGalleryItem(
    val movieId: Long,
    val thumbnailUrl: String
)
