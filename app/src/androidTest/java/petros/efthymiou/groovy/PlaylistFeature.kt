package petros.efthymiou.groovy

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import org.junit.Rule
import org.junit.Test
import petros.efthymiou.groovy.playlist.idlingResource

class PlaylistFeature : BaseUITest() {

    val mActivityRule = ActivityScenarioRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displayListOfPlaylists() {
        assertRecyclerViewItemCount(R.id.playlists_list, 10)

        checkCell(
            position = 0,
            name = "Hard Rock Cafe",
            category = "rock",
            image = R.mipmap.rock
        )
    }

    @Test
    fun displayLoaderWhileFetchingThePlaylists() {
        // Unregister idling resource so thread sleep will not apply
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun hidesLoader() {
        assertNotDisplayed(R.id.loader)
    }

    @Test
    fun displaysRockImageForRockListItems() {
        val rockImage = R.mipmap.rock

        checkCell(
            position = 0,
            image = rockImage
        )
        checkCell(
            position = 3,
            image = rockImage
        )
    }
}
