package eu.tobiasheine.movies.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.frontend.DependencyProvider
import eu.tobiasheine.movies.frontend.MoviesPresenter
import kotlinx.android.synthetic.main.activity_movie_gallery.*

private const val NO_OF_COLUMNS = 3

class MovieGalleryActivity : AppCompatActivity(), MoviesPresenter.View {

    private val presenter = DependencyProvider.providePresenter()

    private val galleryAdapter: GalleryAdapter by lazy {
        GalleryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_gallery)
        movie_gallery.adapter = galleryAdapter
        movie_gallery.layoutManager = GridLayoutManager(this, NO_OF_COLUMNS)

        presenter.setView(this)
        presenter.loadMovies()
    }

    override fun render(movieGallery: MovieGallery) {
        galleryAdapter.updateWith(movieGallery)
    }

    override fun showError(throwable: Throwable) {
        Log.e(TAG, "error", throwable)
    }

    companion object {
        val TAG: String = MovieGalleryActivity::class.java.simpleName
    }
}
