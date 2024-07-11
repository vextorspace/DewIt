package state

import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import resources.AppFile
import viewmodel.GtdModel
import kotlin.test.BeforeTest
import kotlin.test.Test

const val fileName = "testSave.json"

class AndroidModelSaverTest {
    val saveFile = AppFile(fileName)
    val model = GtdModel.createModel()

    init {
        AppFile.context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @BeforeTest
    fun setup() {
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @Test
    fun androidModelSaverCreatesASaveFile() {
        val androidModelSaver: ModelSaver = AndroidModelSaver(fileName, model)

        saveFile.exists().shouldBeFalse()

        androidModelSaver.save()

        saveFile.exists().shouldBeTrue()
    }
}