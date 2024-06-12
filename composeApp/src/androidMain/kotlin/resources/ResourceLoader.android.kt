package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class ResourceLoader actual constructor(val resourcePath: String) {

    actual fun readTextFromFile(): String? {
        return this.javaClass.getResource(resourcePath)?.readText()
    }
}