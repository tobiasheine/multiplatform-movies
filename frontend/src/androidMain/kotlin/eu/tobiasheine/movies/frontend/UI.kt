package eu.tobiasheine.movies.frontend

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val UI: CoroutineDispatcher
    get() = Dispatchers.Main

internal actual val IO: CoroutineDispatcher
    get() = Dispatchers.IO