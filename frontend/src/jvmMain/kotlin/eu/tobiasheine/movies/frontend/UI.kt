package eu.tobiasheine.movies.frontend

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val IO: CoroutineDispatcher = Dispatchers.IO