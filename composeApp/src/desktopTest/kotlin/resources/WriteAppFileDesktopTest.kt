package resources

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class WriteAppFileDesktopTest {
    val appFile = AppFile("appFile.json")

    @BeforeTest
    fun setup() {
        if(appFile.exists()) {
            appFile.delete()
        }
    }

    @Test
    fun `Writes App File`() {
        val textToWrite = "::ANY TEXT AT ALL::"
        appFile.exists().shouldBeFalse()

        appFile.create()

        appFile.writeText(textToWrite)

        appFile.readText()
            .shouldBe(textToWrite)
    }

    @Test
    fun `Writes App File Overwriting Existing`() {
        val textToWrite = "::ANY TEXT AT ALL::"
        val textToWrite2 = "::ANY TEXT AT ALL 2::"
        appFile.exists().shouldBeFalse()

        appFile.create()

        appFile.writeText(textToWrite)
        appFile.writeText(textToWrite2)

        appFile.readText()
            .shouldBe(textToWrite2)
    }

    @Test
    fun `If file does not exist creates new`() {
        val textToWrite = "::ANY TEXT AT ALL::"
        appFile.exists().shouldBeFalse()

        appFile.writeText(textToWrite)

        appFile.exists().shouldBeTrue()

        appFile.readText()
            .shouldBe(textToWrite)
    }
}