package resources

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class DeletesResourceDesktopTest {
    private val resource = Resource("/resources/writes_test.txt")

    @BeforeTest
    fun setup() {
        if(resource.exists()) {
            resource.delete()
        }
    }

    @Test
    fun `deletesResource should suceed`() {

        val textToWrite = "boo!"
        resource.writeTextToFile(textToWrite)
        resource.exists()
            .shouldBeTrue()

        resource.delete()

        resource.exists()
            .shouldBeFalse()
    }
}