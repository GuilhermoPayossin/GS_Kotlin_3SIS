package com.fiap.orbitflow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.ui.theme.OrbitStroke
import com.fiap.orbitflow.ui.theme.OrbitSurface

@Composable
fun OrbitFlowCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, OrbitStroke),
        colors = CardDefaults.cardColors(containerColor = OrbitSurface),
        content = {
            androidx.compose.foundation.layout.Column(
                modifier = Modifier.padding(16.dp),
                content = content
            )
        }
    )
}
