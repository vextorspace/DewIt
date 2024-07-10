package resources

import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldBeEmpty
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ReadAppFileTest {

    val appFile = AppFile("appFile.json")

    init {
        AppFile.context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @BeforeTest
    fun setup() {
        if(appFile.exists()) {
            appFile.delete()
        }
    }

    @AfterTest
    fun teardown() {
        if(appFile.exists()) {
            appFile.delete()
        }
    }

    @Test
    fun nonExistentAppFileReadsnull() {
        val appFile = AppFile("appFile.json")
        appFile.readText()
            .shouldBeNull()
    }

    @Test
    fun readsEmptyAppFile() {
        val appFile = AppFile("appFile.json")
        appFile.create()

        appFile.readText()
            .shouldNotBeNull()
            .shouldBeEmpty()
    }
}