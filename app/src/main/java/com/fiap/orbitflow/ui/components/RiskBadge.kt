package com.fiap.orbitflow.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.ui.theme.OrbitGreen
import com.fiap.orbitflow.ui.theme.OrbitOrange
import com.fiap.orbitflow.ui.theme.OrbitRed
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary
import com.fiap.orbitflow.ui.theme.OrbitYellow

@Composable
fun RiskBadge(
    riskLevel: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        color = riskColor(riskLevel).copy(alpha = 0.18f),
        contentColor = OrbitTextPrimary
    ) {
        Text(
            text = riskLevel,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = MaterialTheme.typography.labelLarge,
            color = riskColor(riskLevel)
        )
    }
}

fun riskColor(riskLevel: String): Color = when (riskLevel) {
    "Baixo" -> OrbitGreen
    "Médio" -> OrbitYellow
    "Alto" -> OrbitOrange
    "Crítico" -> OrbitRed
    else -> OrbitTextPrimary
}
