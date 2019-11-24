package eu.tobiasheine.movies.frontend

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.newSingleThreadContext

internal actual val IO: CoroutineDispatcher = newSingleThreadContext("io")