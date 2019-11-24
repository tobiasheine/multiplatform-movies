package eu.tobiasheine.movies.frontend

import platform.Foundation.*

actual val BASE_URL: String = "http://localhost:8080"

actual fun simpleGet(url: String): String {
    val urlObj = NSURL(string = url)
    var resultString: String? = null
    val request = NSURLRequest.requestWithURL(urlObj)
    val data = NSURLConnection.sendSynchronousRequest(request, null, null)
    if (data != null) {
        val decoded = NSString.create(data = data, encoding = NSUTF8StringEncoding)
        if (decoded != null)
            resultString = decoded as String
    }

    if (resultString == null)
        throw NullPointerException("No network response")
    else
        return resultString!!
}