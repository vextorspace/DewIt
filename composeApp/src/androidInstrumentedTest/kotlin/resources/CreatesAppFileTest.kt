package resources

import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class CreatesAppFileTest {
    val saveFile = AppFile("saveFile.json")

    init {
        AppFile.context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @BeforeTest
    fun setup() {
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @AfterTest
    fun teardown() {
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @Test
    fun createAppFile() {
        saveFile.exists().shouldBeFalse()
        saveFile.create().shouldBeTrue()
        saveFile.exists().shouldBeTrue()
    }
}