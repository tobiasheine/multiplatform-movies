package eu.tobiasheine.movies.frontend

import kotlinx.coroutines.CoroutineDispatcher

internal expect val UI: CoroutineDispatcher
internal expect val IO: CoroutineDispatcher