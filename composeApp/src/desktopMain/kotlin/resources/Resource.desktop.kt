package resources

import java.io.File
import java.io.IOException
import java.net.URL

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Resource actual constructor(val resourcePath: String) {

    actual fun readTextFromFile(): String? {
        return getResourceUrl()?.readText()
    }

    actual fun exists(): Boolean {
        return getResourceUrl() != null
    }

    fun delete() {
        getResourceUrl()?.toURI()?.let {
            File(it).delete()
        }
    }

    fun writeTextToFile(textToWrite: String) {
        val baseDir = this.javaClass.getResource("/")
        val file = File(baseDir.toURI().path + resourcePath)

        try {
            file.writeText(textToWrite)
        } catch (e: IOException) {
            println("Error writing to file: $e")
        }
    }

    private fun getResourceUrl(): URL? {
        return this.javaClass.getResource(resourcePath)
    }
}