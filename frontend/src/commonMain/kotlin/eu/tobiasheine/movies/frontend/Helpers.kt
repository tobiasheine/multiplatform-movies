package eu.tobiasheine.movies.frontend

internal expect fun Any.ensureNeverFrozen()
internal expect val mainThread: Boolean
internal fun assertNotMainThread() {
    if (mainThread)
        throw IllegalStateException("Must not be on main thread")
}