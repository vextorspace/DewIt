package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ResourceLoader(resourcePath: String) {
    fun readTextFromFile(): String?
}
