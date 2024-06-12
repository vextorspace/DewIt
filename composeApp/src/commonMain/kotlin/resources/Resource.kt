package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class Resource(resourcePath: String) {
    fun readTextFromFile(): String?
}
