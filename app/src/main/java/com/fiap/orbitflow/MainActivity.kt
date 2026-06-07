package com.fiap.orbitflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fiap.orbitflow.navigation.AppNavigation
import com.fiap.orbitflow.ui.theme.OrbitFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrbitFlowTheme {
                AppNavigation()
            }
        }
    }
}
