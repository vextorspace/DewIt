package ids

import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldNotBe
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
}