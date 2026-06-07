package com.fiap.orbitflow.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.ui.components.OrbitFlowCard
import com.fiap.orbitflow.ui.theme.OrbitBlue
import com.fiap.orbitflow.ui.theme.OrbitCyan
import com.fiap.orbitflow.ui.theme.OrbitOrange
import com.fiap.orbitflow.ui.theme.OrbitPurple
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary
import com.fiap.orbitflow.ui.theme.OrbitTextSecondary
import com.fiap.orbitflow.ui.theme.SpaceBackground

@Composable
fun HomeScreen(
    onDashboardClick: () -> Unit,
    onObjectsClick: () -> Unit,
    onRiskZonesClick: () -> Unit
) {
    Scaffold(
        containerColor = SpaceBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            OrbitIdentity()

            Text(
                text = "OrbitFlow",
                style = MaterialTheme.typography.displaySmall,
                color = OrbitTextPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Rotas seguras para uma nova era espacial.",
                style = MaterialTheme.typography.titleMedium,
                color = OrbitCyan,
                textAlign = TextAlign.Center
            )

            OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "O OrbitFlow monitora objetos orbitais, identifica zonas críticas e apoia decisões de rota, desvio e limpeza espacial.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = OrbitTextSecondary,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            HomePrimaryButton(
                text = "Acessar Dashboard",
                onClick = onDashboardClick
            )
            HomeSecondaryButton(
                text = "Ver Objetos Orbitais",
                onClick = onObjectsClick
            )
            HomeSecondaryButton(
                text = "Zonas de Risco",
                onClick = onRiskZonesClick
            )
        }
    }
}

@Composable
private fun OrbitIdentity(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(210.dp)) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val stroke = Stroke(width = 2.dp.toPx())

            drawCircle(
                color = OrbitBlue.copy(alpha = 0.25f),
                radius = size.minDimension * 0.36f,
                center = center,
                style = stroke
            )
            rotate(degrees = 24f) {
                drawOval(
                    color = OrbitCyan.copy(alpha = 0.82f),
                    topLeft = Offset(size.width * 0.08f, size.height * 0.36f),
                    size = Size(size.width * 0.84f, size.height * 0.28f),
                    style = Stroke(width = 3.dp.toPx())
                )
            }
            rotate(degrees = -22f) {
                drawOval(
                    color = OrbitPurple.copy(alpha = 0.62f),
                    topLeft = Offset(size.width * 0.16f, size.height * 0.30f),
                    size = Size(size.width * 0.68f, size.height * 0.40f),
                    style = stroke
                )
            }
            drawCircle(
                color = OrbitPurple,
                radius = 14.dp.toPx(),
                center = center
            )
            drawCircle(
                color = OrbitOrange,
                radius = 6.dp.toPx(),
                center = Offset(size.width * 0.78f, size.height * 0.42f)
            )
            drawCircle(
                color = OrbitCyan,
                radius = 4.dp.toPx(),
                center = Offset(size.width * 0.25f, size.height * 0.63f)
            )
        }

        Text(
            text = "OF",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = OrbitTextPrimary
        )
    }
}

@Composable
private fun HomePrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = OrbitCyan,
            contentColor = SpaceBackground
        )
    ) {
        Text(text = text)
    }
}

@Composable
private fun HomeSecondaryButton(
    text: String,
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Text(text = text)
    }
}
