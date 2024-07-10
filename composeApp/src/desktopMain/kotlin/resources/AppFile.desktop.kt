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
        return file.delete()
    }
}