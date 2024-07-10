package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class AppFile(fileName: String) {
    fun exists(): Boolean
    fun create(): Boolean
    fun delete(): Boolean
    fun readText(): String?
}
