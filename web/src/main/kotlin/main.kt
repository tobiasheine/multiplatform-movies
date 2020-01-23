import eu.tobiasheine.movies.moviesApp
import react.dom.render
import kotlin.browser.document

fun main() {
    render(document.getElementById("app")) {
        moviesApp()
    }
}