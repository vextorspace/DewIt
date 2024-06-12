package resources

import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.Test

class DetectsResourceDesktopTest {

    @Test
    fun `detectsResource should return true`() {
        Resource("/test.txt").exists()
            .shouldBeTrue()
    }
}