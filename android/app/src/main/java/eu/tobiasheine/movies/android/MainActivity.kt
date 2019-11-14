package eu.tobiasheine.movies.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.frontend.DependencyProvider
import eu.tobiasheine.movies.frontend.MoviesPresenter

class MainActivity : AppCompatActivity(), MoviesPresenter.View {
    private val presenter = DependencyProvider.providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.bind(this)
    }

    override fun render(movieGallery: MovieGallery) {
        Log.d(TAG, "render $movieGallery")
    }


    override fun showError(throwable: Throwable) {
        Log.e(TAG, "error", throwable)
    }

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}
