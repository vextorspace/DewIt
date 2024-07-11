package resources

import io.kotest.matchers.shouldBe
import kotlin.test.BeforeTest
import kotlin.test.Test

class WriteAppFileTest {
    val appFile = AppFile("appFile.json")

    @BeforeTest
    fun setup() {
        if(appFile.exists()) {
            appFile.delete()
        }
    }

    @Test
    fun `Writes App File`() {
        appFile.create()
        val textToWrite = "::ANY TEXT AT ALL::"
        appFile.writeText(textToWrite)

        appFile.readText()
            .shouldBe(textToWrite)
    }
}