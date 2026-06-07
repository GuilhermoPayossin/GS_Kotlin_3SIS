package com.fiap.orbitflow.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.fiap.orbitflow.ui.theme.DeepSpace
import com.fiap.orbitflow.ui.theme.OrbitCyan
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrbitTopBar(
    title: String,
    onBack: (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (onBack != null) {
                TextButton(onClick = onBack) {
                    Text(text = "Voltar", color = OrbitCyan)
                }
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = DeepSpace,
            titleContentColor = OrbitTextPrimary
        )
    )
}
