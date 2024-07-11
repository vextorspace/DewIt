package com.ronnev

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import resources.AppFile
import state.LifeCycle
import state.ModelSaver
import viewmodel.GtdModel

class MainActivity : ComponentActivity() {
    val lifeCycle = LifeCycle(
        ModelSaver(
            fileName = ModelSaver.DEFAULT_SAVE_FILE_NAME,
            defaultModel = GtdModel.createModel()
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppFile.context = this
        lifeCycle.onInit()

        setContent {
            App(lifeCycle)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeCycle.onShutdown()
        AppFile.context = null
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val lifeCycle = LifeCycle(
        ModelSaver(
            fileName = ModelSaver.DEFAULT_SAVE_FILE_NAME,
            defaultModel = GtdModel.createModel()
        )
    )

    App(lifeCycle)
}