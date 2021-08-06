package petros.efthymiou.groovy.playlist

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import petros.efthymiou.groovy.playlist.models.PlaylistRaw
import java.lang.RuntimeException
import javax.inject.Inject

class PlaylistService @Inject constructor(
    private val api: PlaylistAPI
) {

    suspend fun fetchPlaylists(): Flow<Result<List<PlaylistRaw>>> =
        flow {
            emit(Result.success(api.fetchAllPlaylists()))
        }.catch {
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
}
