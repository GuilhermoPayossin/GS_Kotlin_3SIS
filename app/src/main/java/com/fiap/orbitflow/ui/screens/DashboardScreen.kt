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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.data.mock.MockData
import com.fiap.orbitflow.data.model.OrbitAlert
import com.fiap.orbitflow.ui.components.OrbitFlowCard
import com.fiap.orbitflow.ui.components.OrbitTopBar
import com.fiap.orbitflow.ui.components.RiskBadge
import com.fiap.orbitflow.ui.components.SectionTitle
import com.fiap.orbitflow.ui.components.riskColor
import com.fiap.orbitflow.ui.theme.OrbitCyan
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary
import com.fiap.orbitflow.ui.theme.OrbitTextSecondary
import com.fiap.orbitflow.ui.theme.SpaceBackground
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    onBack: () -> Unit,
    onHomeClick: () -> Unit,
    onObjectsClick: () -> Unit,
    onRiskZonesClick: () -> Unit
) {
    var refreshCount by remember { mutableStateOf(0) }
    var lastUpdate by remember { mutableStateOf("Aguardando atualização simulada") }

    val criticalRisks = MockData.orbitalObjects.count { it.riskLevel == "Crítico" } +
        MockData.riskZones.count { it.riskLevel == "Crítico" }
    val monitoredObjects = MockData.orbitalObjects.size + refreshCount
    val openRequests = MockData.openRequests.size + if (refreshCount > 0) 1 else 0

    Scaffold(
        topBar = {
            OrbitTopBar(
                title = "Dashboard",
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
                    title = "Visão geral orbital",
                    subtitle = "Monitoramento simulado de objetos, zonas críticas e solicitações operacionais."
                )
            }

            item {
                SummaryGrid(
                    first = DashboardMetric("Objetos monitorados", monitoredObjects.toString()),
                    second = DashboardMetric("Riscos críticos", criticalRisks.toString()),
                    third = DashboardMetric("Zonas ativas", MockData.riskZones.size.toString()),
                    fourth = DashboardMetric("Solicitações abertas", openRequests.toString())
                )
            }

            item {
                DashboardNavigation(
                    onHomeClick = onHomeClick,
                    onObjectsClick = onObjectsClick,
                    onRiskZonesClick = onRiskZonesClick
                )
            }

            item {
                OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Atualização simulada",
                        style = MaterialTheme.typography.titleMedium,
                        color = OrbitTextPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Último ciclo: $lastUpdate",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OrbitTextSecondary
                    )
                    Text(
                        text = "Sincronizações realizadas: $refreshCount",
                        style = MaterialTheme.typography.bodyMedium,
                        color = OrbitTextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            refreshCount += 1
                            lastUpdate = "ciclo $refreshCount às ${currentSimulatedTime()}"
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OrbitCyan,
                            contentColor = SpaceBackground
                        )
                    ) {
                        Text(text = "Atualizar dados simulados")
                    }
                }
            }

            item {
                SectionTitle(title = "Alertas recentes")
            }

            items(MockData.alerts, key = { it.id }) { alert ->
                AlertCard(alert = alert)
            }

            item {
                SectionTitle(title = "Recomendações operacionais")
            }

            items(MockData.operationalRecommendations.take(3)) { recommendation ->
                OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = recommendation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = OrbitTextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun SummaryGrid(
    first: DashboardMetric,
    second: DashboardMetric,
    third: DashboardMetric,
    fourth: DashboardMetric
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(metric = first, modifier = Modifier.weight(1f))
            MetricCard(metric = second, modifier = Modifier.weight(1f))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            MetricCard(metric = third, modifier = Modifier.weight(1f))
            MetricCard(metric = fourth, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
private fun MetricCard(
    metric: DashboardMetric,
    modifier: Modifier = Modifier
) {
    OrbitFlowCard(modifier = modifier) {
        Text(
            text = metric.value,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = OrbitCyan
        )
        Text(
            text = metric.label,
            style = MaterialTheme.typography.bodyMedium,
            color = OrbitTextSecondary
        )
    }
}

@Composable
private fun DashboardNavigation(
    onHomeClick: () -> Unit,
    onObjectsClick: () -> Unit,
    onRiskZonesClick: () -> Unit
) {
    OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Fluxo de telas",
            style = MaterialTheme.typography.titleMedium,
            color = OrbitTextPrimary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onHomeClick
            ) {
                Text(text = "Home")
            }
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onObjectsClick
            ) {
                Text(text = "Objetos")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onRiskZonesClick
        ) {
            Text(text = "Zonas de Risco")
        }
    }
}

@Composable
private fun AlertCard(alert: OrbitAlert) {
    OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = alert.title,
                style = MaterialTheme.typography.titleMedium,
                color = OrbitTextPrimary,
                modifier = Modifier.weight(1f)
            )
            RiskBadge(riskLevel = alert.riskLevel)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = alert.description,
            style = MaterialTheme.typography.bodyMedium,
            color = OrbitTextSecondary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Prioridade: ${alert.riskLevel}",
            style = MaterialTheme.typography.labelLarge,
            color = riskColor(alert.riskLevel)
        )
    }
}

private data class DashboardMetric(
    val label: String,
    val value: String
)

private fun currentSimulatedTime(): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.forLanguageTag("pt-BR"))
    return formatter.format(Date())
}
