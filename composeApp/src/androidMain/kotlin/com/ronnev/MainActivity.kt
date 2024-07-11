package com.ronnev

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import resources.AppFile

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppFile.context = this

        setContent {
            App()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        AppFile.context = null

    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}