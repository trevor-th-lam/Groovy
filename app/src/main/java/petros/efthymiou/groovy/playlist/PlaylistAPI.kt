package petros.efthymiou.groovy.playlist

import petros.efthymiou.groovy.playlist.models.PlaylistRaw
import retrofit2.http.GET

interface PlaylistAPI {

    @GET("playlists")
    suspend fun fetchAllPlaylists(): List<PlaylistRaw>
}