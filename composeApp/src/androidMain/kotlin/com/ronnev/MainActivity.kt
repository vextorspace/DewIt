package com.ronnev

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import resources.AppFile
import state.AndroidModelSaver
import state.LifeCycle
import viewmodel.GtdModel

class MainActivity : ComponentActivity() {
    val model = GtdModel.createModel()
    val fileName = "DewItNowSave.json"
    val lifeCycle = LifeCycle(AndroidModelSaver(fileName, model))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppFile.context = this

        setContent {
            App()
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
    App()
}