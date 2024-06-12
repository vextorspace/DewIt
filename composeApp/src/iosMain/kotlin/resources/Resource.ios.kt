package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Resource actual constructor(resourcePath: String) {

    actual fun readTextFromFile(): String? {
        TODO("Not yet implemented")
    }

    actual fun exists(): Boolean {
        TODO("Not yet implemented")
    }

    actual fun delete() {
        TODO("Not yet implemented")
    }

    actual fun writeTextToFile(textToWrite: String) {
        TODO("Not yet implemented")
    }
}