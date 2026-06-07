package com.fiap.orbitflow.data.model

data class RiskZone(
    val id: Int,
    val name: String,
    val orbitType: String,
    val riskLevel: String,
    val description: String,
    val recommendation: String
)
