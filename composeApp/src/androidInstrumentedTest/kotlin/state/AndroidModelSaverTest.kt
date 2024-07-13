package state

import androidx.test.platform.app.InstrumentationRegistry
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.string.shouldContain
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
        val androidModelSaver = ModelSaver(fileName, model)

        saveFile.exists().shouldBeFalse()

        androidModelSaver.save()

        saveFile.exists().shouldBeTrue()
        saveFile.readText().shouldContain(model.toJson())
    }

    @Test
    fun androidModelSaverLoadsASavedFile() {
        val saveModel = ModelSaver(fileName, model)

        saveModel.save()
        val loadModel = ModelSaver(fileName, model)
        loadModel.load()
        loadModel.model.shouldBeEqualUsingFields(model)
    }
}