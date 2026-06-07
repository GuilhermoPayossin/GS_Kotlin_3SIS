package com.fiap.orbitflow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.data.mock.MockData
import com.fiap.orbitflow.data.model.RiskZone
import com.fiap.orbitflow.ui.components.OrbitFlowCard
import com.fiap.orbitflow.ui.components.OrbitTopBar
import com.fiap.orbitflow.ui.components.RiskBadge
import com.fiap.orbitflow.ui.components.SectionTitle
import com.fiap.orbitflow.ui.theme.OrbitCyan
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary
import com.fiap.orbitflow.ui.theme.OrbitTextSecondary
import com.fiap.orbitflow.ui.theme.SpaceBackground

@Composable
fun RiskZonesScreen(
    onBack: () -> Unit,
    onDashboardClick: () -> Unit,
    onObjectsClick: () -> Unit
) {
    var requestMessage by remember { mutableStateOf("") }
    var requestCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            OrbitTopBar(
                title = "Zonas de Risco",
                onBack = onBack
            )
        },
        containerColor = SpaceBackground
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SectionTitle(
                    title = "Zonas críticas",
                    subtitle = "Regiões orbitais simuladas com risco operacional para satélites e rotas de missão."
                )
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = onDashboardClick
                    ) {
                        Text(text = "Dashboard")
                    }
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = onObjectsClick
                    ) {
                        Text(text = "Objetos")
                    }
                }
            }

            item {
                OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Ação operacional",
                        style = MaterialTheme.typography.titleMedium,
                        color = OrbitTextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Abra uma solicitação simulada para análise da equipe OrbitFlow.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OrbitTextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            val nextRequest = requestCount + 1
                            requestCount = nextRequest
                            requestMessage =
                                "Solicitação registrada para análise da equipe OrbitFlow. Protocolo simulado OF-${1000 + nextRequest}."
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrbitCyan,
                            contentColor = SpaceBackground
                        )
                    ) {
                        Text(text = "Solicitar limpeza orbital")
                    }
                }
            }

            if (requestMessage.isNotBlank()) {
                item {
                    OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = requestMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            color = OrbitCyan
                        )
                    }
                }
            }

            items(MockData.riskZones, key = { it.id }) { riskZone ->
                RiskZoneCard(riskZone = riskZone)
            }
        }
    }
}

@Composable
private fun RiskZoneCard(riskZone: RiskZone) {
    OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = riskZone.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OrbitTextPrimary
                )
                Text(
                    text = riskZone.orbitType,
                    style = MaterialTheme.typography.bodyMedium,
                    color = OrbitTextSecondary
                )
            }
            RiskBadge(riskLevel = riskZone.riskLevel)
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = riskZone.description,
            style = MaterialTheme.typography.bodyMedium,
            color = OrbitTextSecondary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Recomendação",
            style = MaterialTheme.typography.labelLarge,
            color = OrbitCyan
        )
        Text(
            text = riskZone.recommendation,
            style = MaterialTheme.typography.bodyMedium,
            color = OrbitTextSecondary
        )
    }
}
