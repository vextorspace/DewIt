package resources

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreatesAppFileTest {

    @BeforeTest
    fun setup() {
        val saveFile = AppFile("saveFile.json")
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @Test
    fun `creates a save file`() {
        val saveFile = AppFile("saveFile.json")
        saveFile.exists().shouldBeFalse()
        saveFile.create().shouldBeTrue()
        saveFile.exists().shouldBeTrue()
    }
}