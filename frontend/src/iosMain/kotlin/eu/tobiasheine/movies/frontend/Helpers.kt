package eu.tobiasheine.movies.frontend

import kotlin.native.concurrent.ensureNeverFrozen
import platform.Foundation.NSThread

internal actual fun Any.ensureNeverFrozen() {
    this.ensureNeverFrozen()
}

internal actual val mainThread: Boolean
    get() = NSThread.isMainThread