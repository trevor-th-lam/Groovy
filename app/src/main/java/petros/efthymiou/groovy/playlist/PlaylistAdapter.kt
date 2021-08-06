package petros.efthymiou.groovy.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import petros.efthymiou.groovy.databinding.PlaylistItemBinding
import petros.efthymiou.groovy.playlist.models.Playlist

class PlaylistAdapter(
    private val values: List<Playlist>
) : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PlaylistItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        with(holder) {
            playlistName.text = item.name
            playlistCategory.text = item.category
            playlistImage.setImageResource(item.image)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val playlistName = binding.playlistName
        val playlistCategory = binding.playlistCategory
        val playlistImage = binding.playlistImageView
    }
}