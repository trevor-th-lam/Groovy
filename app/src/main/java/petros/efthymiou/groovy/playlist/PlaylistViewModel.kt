package petros.efthymiou.groovy.playlist

import androidx.lifecycle.*

class PlaylistViewModel(
    private val repository: PlaylistRepository
): ViewModel() {

    val playlists = liveData {
        emitSource(repository.getPlaylists().asLiveData())
    }

}