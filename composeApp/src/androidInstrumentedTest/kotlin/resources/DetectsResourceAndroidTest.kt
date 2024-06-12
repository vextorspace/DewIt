package resources

import io.kotest.matchers.booleans.shouldBeTrue
import org.junit.Test

class DetectsResourceAndroidTest {

    @Test
    fun detectsResource() {
        Resource("/test.txt").exists()
            .shouldBeTrue()
    }
}