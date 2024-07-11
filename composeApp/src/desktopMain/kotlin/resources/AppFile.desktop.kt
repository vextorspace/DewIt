package resources

import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AppFile actual constructor(fileName: String) {
    val file = File(System.getProperty("user.home"), fileName)

    actual fun exists(): Boolean {
        return file.exists()
    }

    actual fun create(): Boolean {
        return file.createNewFile()
    }

    actual fun delete(): Boolean {
        if (!file.exists())
            return false
        return file.delete()
    }

    actual fun readText(): String? {
        if (!file.exists())
            return null
        return file.readText()
    }

    actual fun writeText(textToWrite: String) {
        file.writeText(textToWrite)
    }
}