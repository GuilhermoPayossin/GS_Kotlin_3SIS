package com.fiap.orbitflow.data.model

data class OrbitalObject(
    val id: Int,
    val name: String,
    val type: String,
    val orbit: String,
    val riskLevel: String,
    val distanceKm: Int,
    val recommendation: String
)
