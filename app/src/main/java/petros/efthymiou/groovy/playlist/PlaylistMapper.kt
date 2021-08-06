package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.R
import petros.efthymiou.groovy.playlist.models.Playlist
import petros.efthymiou.groovy.playlist.models.PlaylistRaw
import javax.inject.Inject

class PlaylistMapper @Inject constructor() : Function1<List<PlaylistRaw>, List<Playlist>> {

    override fun invoke(playlistsRaw: List<PlaylistRaw>): List<Playlist> =
        playlistsRaw.map {
            Playlist(
                id = it.id,
                name = it.name,
                category = it.category,
                image = when (it.category) {
                    "rock" -> R.mipmap.rock
                    else -> R.mipmap.playlist
                }
            )
        }
}
