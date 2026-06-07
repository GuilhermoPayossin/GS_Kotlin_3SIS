package com.fiap.orbitflow.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiap.orbitflow.data.mock.MockData
import com.fiap.orbitflow.data.model.OrbitalObject
import com.fiap.orbitflow.ui.components.OrbitFlowCard
import com.fiap.orbitflow.ui.components.OrbitTopBar
import com.fiap.orbitflow.ui.components.RiskBadge
import com.fiap.orbitflow.ui.components.SectionTitle
import com.fiap.orbitflow.ui.theme.DeepSpace
import com.fiap.orbitflow.ui.theme.OrbitCyan
import com.fiap.orbitflow.ui.theme.OrbitTextPrimary
import com.fiap.orbitflow.ui.theme.OrbitTextSecondary
import com.fiap.orbitflow.ui.theme.SpaceBackground

@Composable
fun ObjectsScreen(
    onBack: () -> Unit,
    onDashboardClick: () -> Unit,
    onRiskZonesClick: () -> Unit
) {
    var selectedRisk by remember { mutableStateOf("Todos") }
    val riskFilters = listOf("Todos", "Baixo", "Médio", "Alto", "Crítico")
    val filteredObjects = if (selectedRisk == "Todos") {
        MockData.orbitalObjects
    } else {
        MockData.orbitalObjects.filter { it.riskLevel == selectedRisk }
    }

    Scaffold(
        topBar = {
            OrbitTopBar(
                title = "Objetos Orbitais",
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
                    title = "Objetos monitorados",
                    subtitle = "Filtre por nível de risco para revisar rotas, distâncias e recomendações."
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    riskFilters.forEach { risk ->
                        FilterChip(
                            selected = selectedRisk == risk,
                            onClick = { selectedRisk = risk },
                            label = { Text(text = risk) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = OrbitCyan,
                                selectedLabelColor = SpaceBackground,
                                labelColor = OrbitTextPrimary
                            )
                        )
                    }
                }
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
                        onClick = onRiskZonesClick
                    ) {
                        Text(text = "Zonas")
                    }
                }
            }

            item {
                Text(
                    text = "${filteredObjects.size} objeto(s) exibido(s)",
                    style = MaterialTheme.typography.bodyMedium,
                    color = OrbitTextSecondary
                )
            }

            items(filteredObjects, key = { it.id }) { orbitalObject ->
                OrbitalObjectCard(orbitalObject = orbitalObject)
            }
        }
    }
}

@Composable
private fun OrbitalObjectCard(orbitalObject: OrbitalObject) {
    OrbitFlowCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = orbitalObject.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OrbitTextPrimary
                )
                Text(
                    text = orbitalObject.type,
                    style = MaterialTheme.typography.bodyMedium,
                    color = OrbitTextSecondary
                )
            }
            RiskBadge(riskLevel = orbitalObject.riskLevel)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            InfoPill(label = "Órbita", value = orbitalObject.orbit)
            InfoPill(label = "Distância", value = "${orbitalObject.distanceKm} km")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Recomendação",
            style = MaterialTheme.typography.labelLarge,
            color = OrbitCyan
        )
        Text(
            text = orbitalObject.recommendation,
            style = MaterialTheme.typography.bodyMedium,
            color = OrbitTextSecondary
        )
    }
}

@Composable
private fun InfoPill(
    label: String,
    value: String
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = DeepSpace
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                color = OrbitCyan
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = OrbitTextPrimary
            )
        }
    }
}
