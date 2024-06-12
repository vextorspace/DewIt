package ids

import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldNotBe
import resources.Resource
import kotlin.test.Test

class UUIDTest {

    @Test
    fun `generateUUID should return a string with 36 characters`() {
        val uuid = UUID.generateUUID()
        uuid.length.shouldBeExactly(36)
    }

    @Test
    fun `generateUUID should never return the same value`() {
        val uuid1 = UUID.generateUUID()
        val uuid2 = UUID.generateUUID()
        uuid1.shouldNotBe(uuid2)
    }


    @Test
    fun `when first loaded should not return the same uuid`() {
        val regressionTestResource = Resource("/resources/uuidRegressionTest.txt")
        val uuid1 = UUID.generateUUID()
        val uuid2 = regressionTestResource.readTextFromFile()
        uuid1.shouldNotBe(uuid2)
        regressionTestResource.writeTextToFile(uuid1)
    }
}