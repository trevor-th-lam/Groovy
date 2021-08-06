package petros.efthymiou.groovy.playlist

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import petros.efthymiou.groovy.playlist.models.PlaylistRaw
import petros.efthymiou.groovy.utils.BaseUnitTest
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistAPI = mock()

    private val playlists: List<PlaylistRaw> = mock()
    private val exception = "Something went wrong"

    @Test
    fun fetchPlaylistFromApi() = runBlockingTest {
        service = PlaylistService(api)

        service.fetchPlaylists().first()

        verify(api, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultsAndEmitsThem() = runBlockingTest {
        mockSuccessfulCase()

        assertEquals(Result.success(playlists), service.fetchPlaylists().first())
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runBlockingTest {
        mockErrorCase()

        assertEquals(exception, service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockSuccessfulCase() {
        whenever(api.fetchAllPlaylists()).thenReturn(playlists)

        service = PlaylistService(api)
    }

    private suspend fun mockErrorCase() {
        whenever(api.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developers"))

        service = PlaylistService(api)
    }
}