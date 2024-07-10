package resources

import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldBeEmpty
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class ReadAppFileTest {

    val appFile = AppFile("appFile.json")

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
    fun `Non-existent App File Reads null`() {
        val appFile = AppFile("appFile.json")
        appFile.readText()
            .shouldBeNull()
    }

    @Test
    fun `Reads Empty App File`() {
        val appFile = AppFile("appFile.json")
        appFile.create()

        appFile.readText()
            .shouldNotBeNull()
            .shouldBeEmpty()
    }
}