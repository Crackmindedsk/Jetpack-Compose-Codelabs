package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.mars.MarsPhotoApp
import com.example.myapplication.mars.MarsViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class Mars:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme() {
                MarsPhotoApp()
            }
        }
    }
}