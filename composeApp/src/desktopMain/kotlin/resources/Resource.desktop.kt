package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class Resource actual constructor(resourcePath: String) {
    private val resourceUrl = this.javaClass.getResource(resourcePath)

    actual fun readTextFromFile(): String? {
        return resourceUrl?.readText()
    }

    actual fun exists(): Boolean {
        return resourceUrl != null
    }
}