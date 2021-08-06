package petros.efthymiou.groovy.playlist.models

import androidx.annotation.DrawableRes

data class Playlist(
    val id: String,
    val name: String,
    val category: String,
    @DrawableRes val image: Int
)
