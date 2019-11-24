package eu.tobiasheine.movies.frontend

import java.net.URL

actual val BASE_URL: String = "http://10.0.2.2:8080"
actual fun simpleGet(url: String): String = URL(url).readText()