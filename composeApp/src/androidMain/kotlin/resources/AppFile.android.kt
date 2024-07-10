package resources

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AppFile actual constructor(val fileName: String) {
    actual fun exists(): Boolean {
        return context?.getFileStreamPath(fileName)?.exists() ?: false
    }

    actual fun create(): Boolean {
        return context?.openFileOutput(fileName, android.content.Context.MODE_PRIVATE)?.use {
            it.write(byteArrayOf())
            true
        } ?: false
    }

    actual fun delete(): Boolean {
        return context?.deleteFile(fileName) ?: false
    }

    actual fun readText(): String? {
        if (!exists()) return null

        return context?.openFileInput(fileName)?.bufferedReader()?.use {
            it.readText()
        }
    }

    companion object {
        var context: android.content.Context? = null
    }
}