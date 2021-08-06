package petros.efthymiou.groovy

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import petros.efthymiou.groovy.playlist.idlingResource

@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {

    @Before
    fun setup() {
        // Automatic sleep until http is ready
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    fun checkCell(
        position: Int,
        name: String? = null,
        category: String? = null,
        @DrawableRes image: Int? = null
    ) {
        name?.let {
            Espresso.onView(
                AllOf.allOf(
                    // Item title textView
                    ViewMatchers.withId(R.id.playlist_name),

                    // First item in list
                    ViewMatchers.isDescendantOfA(
                        nthChildOf(ViewMatchers.withId(R.id.playlists_list), position)
                    )
                )
            )
                .check(ViewAssertions.matches(ViewMatchers.withText(it)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        category?.let {
            Espresso.onView(
                AllOf.allOf(
                    ViewMatchers.withId(R.id.playlist_category),
                    ViewMatchers.isDescendantOfA(
                        nthChildOf(ViewMatchers.withId(R.id.playlists_list), position)
                    )
                )
            )
                .check(ViewAssertions.matches(ViewMatchers.withText(category)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        image?.let {
            Espresso.onView(
                AllOf.allOf(
                    ViewMatchers.withId(R.id.playlist_imageView),
                    ViewMatchers.isDescendantOfA(
                        nthChildOf(ViewMatchers.withId(R.id.playlists_list), position)
                    )
                )
            )
                .check(ViewAssertions.matches(DrawableMatcher.withDrawable(it)))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    private fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}