package state

import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.equality.shouldBeEqualUsingFields
import io.kotest.matchers.string.shouldContain
import resources.AppFile
import viewmodel.DewItViewModel
import viewmodel.GtdModel
import kotlin.test.BeforeTest
import kotlin.test.Test

const val fileName = "testSave.json"

class DesktopModelSaverTest {
    private val saveFile = AppFile(fileName)
    private val model = GtdModel.createModel()

    @BeforeTest
    fun setup() {
        if(saveFile.exists()) {
            saveFile.delete()
        }
    }

    @Test
    fun `DesktopModelSaver creates file`() {
        val modelSaver: ModelSaver = DesktopModelSaver(fileName, model)

        saveFile.exists().shouldBeFalse()

        modelSaver.save()

        saveFile.exists().shouldBeTrue()

        saveFile.readText().shouldContain(model.toJson())
    }

    @Test
    fun `DesktopModelSaver reads from file`() {
        val modelSaver: ModelSaver = DesktopModelSaver(fileName, model)

        modelSaver.save()

        val loadedModel: DewItViewModel = modelSaver.load()
        loadedModel.shouldBeEqualUsingFields(model)
    }
}