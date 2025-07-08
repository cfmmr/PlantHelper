package com.example.planthelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*

class MainActivity : ComponentActivity() {
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val planta by remember { mutableStateOf(viewModel.planta) }
            val luminosidade by remember { mutableStateOf(viewModel.luminosidade) }
            val agua by remember { mutableStateOf(viewModel.agua) }

            Text(
                text = (planta,luminosidade,agua)?:"Blank"
            )

        }
    }
}