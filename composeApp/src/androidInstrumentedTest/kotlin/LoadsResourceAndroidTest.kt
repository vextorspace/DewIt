import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import resources.Resource
import kotlin.test.Test

class LoadsResourceAndroidTest {

    @Test
    fun loadsAFileFromResources() {
        Resource("/test.txt").readTextFromFile()
            .shouldNotBeNull()
            .shouldBe("boo!")
    }
}