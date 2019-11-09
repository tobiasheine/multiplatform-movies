package eu.tobiasheine.movies.data

data class MovieGallery(val items: List<MovieGalleryItem>)

data class MovieGalleryItem(
    val movieId: Long,
    val thumbnailUrl: String
)
