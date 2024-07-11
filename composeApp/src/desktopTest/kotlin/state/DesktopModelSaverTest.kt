package state

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import resources.AppFile
import viewmodel.GtdModel
import kotlin.test.BeforeTest
import kotlin.test.Test

const val fileName = "testSave.json"

class DesktopModelSaverTest {
    val saveFile = AppFile(fileName)
    val model = GtdModel.createModel()

    @BeforeTest
    fun setup() {
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @Test
    fun androidModelSaverCreatesASaveFile() {
        val modelSaver: ModelSaver = DesktopModelSaver(fileName, model)

        saveFile.exists().shouldBeFalse()

        modelSaver.save()

        saveFile.exists().shouldBeTrue()
    }
}