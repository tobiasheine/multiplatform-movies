package eu.tobiasheine.movies.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import eu.tobiasheine.movies.data.MovieGallery
import eu.tobiasheine.movies.data.MovieGalleryItem


class GalleryAdapter :
    RecyclerView.Adapter<GalleryItemViewHolder>() {

    private var items = listOf<MovieGalleryItem>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): GalleryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return GalleryItemViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(viewHolder: GalleryItemViewHolder, position: Int) {
        val movie = items[position]
        viewHolder.bind(movie)
    }

    fun updateWith(gallery: MovieGallery) {
        items = gallery.items
        notifyDataSetChanged()
    }
}

class GalleryItemViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.poster_image)

    fun bind(item: MovieGalleryItem) {
        Glide
            .with(imageView.context)
            .load(item.thumbnailUrl)
            .into(imageView)

    }
}