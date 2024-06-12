package resources

import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.fail

class SavesResourceDesktopTest {
    val resource = Resource("/resources/writes_test.txt")

    @BeforeTest
    fun setup() {
        if(resource.exists()) {
            resource.delete()
        }
    }

    @Test
    fun `savesResource should return true`() {
        if(resource.exists()) {
            fail("Resource ${resource.resourcePath} should not exist")
        }

        val textToWrite = "boo!"
        resource.writeTextToFile(textToWrite)
        resource.exists()
            .shouldBeTrue()

        resource.readTextFromFile()
            .shouldBe(textToWrite)
        resource.delete()
    }
}