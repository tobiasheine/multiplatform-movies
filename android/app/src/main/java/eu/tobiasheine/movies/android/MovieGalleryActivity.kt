package eu.tobiasheine.movies.android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.frontend.DependencyProvider
import eu.tobiasheine.movies.frontend.MovieGalleryViewModel
import kotlinx.android.synthetic.main.activity_movie_gallery.*

private const val NO_OF_COLUMNS = 3

class MovieGalleryActivity : AppCompatActivity(), MovieGalleryViewModel.Listener {

    private val viewModel = DependencyProvider.provideViewModel()

    private val galleryAdapter: GalleryAdapter by lazy {
        GalleryAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_gallery)
        movie_gallery.adapter = galleryAdapter
        movie_gallery.layoutManager = GridLayoutManager(this, NO_OF_COLUMNS)

        viewModel.setListener(this)
        viewModel.loadMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    override fun onMovieGallery(movieGallery: MovieGallery) {
        galleryAdapter.updateWith(movieGallery)
    }

    override fun onError(throwable: Throwable) {
        Log.e(TAG, "error", throwable)
    }

    companion object {
        val TAG: String = MovieGalleryActivity::class.java.simpleName
    }
}
