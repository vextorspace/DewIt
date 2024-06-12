package resources

import kotlin.test.Test
import io.kotest.matchers.shouldBe
import io.kotest.matchers.nulls.shouldNotBeNull

class LoadsResourceDesktopTest {

    @Test
    fun `loads a file from the resources dir`() {
        Resource("/test.txt").readTextFromFile()
            .shouldNotBeNull()
            .shouldBe("boo!")
    }
}