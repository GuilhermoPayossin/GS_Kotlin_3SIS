package com.fiap.orbitflow.data.mock

import com.fiap.orbitflow.data.model.OrbitAlert
import com.fiap.orbitflow.data.model.OrbitalObject
import com.fiap.orbitflow.data.model.RiskZone

object MockData {
    val orbitalObjects = listOf(
        OrbitalObject(
            id = 1,
            name = "Fragmento COSMOS-2251",
            type = "Detrito espacial",
            orbit = "LEO",
            riskLevel = "Crítico",
            distanceKm = 4,
            recommendation = "Executar janela de desvio preventivo e manter rastreio contínuo."
        ),
        OrbitalObject(
            id = 2,
            name = "Satélite Sentinel-X",
            type = "Satélite operacional",
            orbit = "LEO",
            riskLevel = "Médio",
            distanceKm = 22,
            recommendation = "Manter rota atual e revisar telemetria no próximo ciclo."
        ),
        OrbitalObject(
            id = 3,
            name = "Estágio de foguete Ariane",
            type = "Corpo de foguete",
            orbit = "MEO",
            riskLevel = "Alto",
            distanceKm = 9,
            recommendation = "Simular manobra de desvio e priorizar comunicação com operadores."
        ),
        OrbitalObject(
            id = 4,
            name = "Painel solar desprendido",
            type = "Fragmento orbital",
            orbit = "LEO",
            riskLevel = "Alto",
            distanceKm = 7,
            recommendation = "Evitar aproximação em rotas de baixa altitude nas próximas 6 horas."
        ),
        OrbitalObject(
            id = 5,
            name = "Satélite meteorológico",
            type = "Satélite operacional",
            orbit = "GEO",
            riskLevel = "Baixo",
            distanceKm = 80,
            recommendation = "Monitoramento padrão sem necessidade de alteração de rota."
        ),
        OrbitalObject(
            id = 6,
            name = "Fragmento Starlink",
            type = "Detrito espacial",
            orbit = "LEO",
            riskLevel = "Médio",
            distanceKm = 18,
            recommendation = "Recalcular corredor seguro antes da próxima passagem orbital."
        )
    )

    val riskZones = listOf(
        RiskZone(
            id = 1,
            name = "Corredor LEO Norte",
            orbitType = "LEO",
            riskLevel = "Crítico",
            description = "Faixa com alta densidade de fragmentos entre 520 km e 580 km de altitude.",
            recommendation = "Registrar solicitação de limpeza e bloquear rotas não essenciais."
        ),
        RiskZone(
            id = 2,
            name = "Anel de transferência MEO-03",
            orbitType = "MEO",
            riskLevel = "Alto",
            description = "Região com cruzamento de corpos de foguete e satélites de navegação.",
            recommendation = "Aplicar desvio leve e revisar plano de transferência orbital."
        ),
        RiskZone(
            id = 3,
            name = "Faixa GEO Leste",
            orbitType = "GEO",
            riskLevel = "Médio",
            description = "Zona com satélites antigos em órbita cemitério próxima ao plano operacional.",
            recommendation = "Acompanhar tendência de aproximação e preparar janela de correção."
        ),
        RiskZone(
            id = 4,
            name = "Cluster Polar LEO",
            orbitType = "LEO",
            riskLevel = "Alto",
            description = "Concentração de detritos em órbita polar com risco para constelações de observação.",
            recommendation = "Recalcular passagens polares e reduzir manobras simultâneas."
        )
    )

    val alerts = listOf(
        OrbitAlert(
            id = 1,
            title = "Aproximação crítica detectada",
            description = "COSMOS-2251 a 4 km de corredor operacional em LEO.",
            riskLevel = "Crítico"
        ),
        OrbitAlert(
            id = 2,
            title = "Zona LEO Norte com densidade elevada",
            description = "Novos fragmentos catalogados no último ciclo simulado.",
            riskLevel = "Alto"
        ),
        OrbitAlert(
            id = 3,
            title = "Recomendação de desvio atualizada",
            description = "Rota segura gerada para satélite Sentinel-X.",
            riskLevel = "Médio"
        )
    )

    val openRequests = listOf(
        "Análise de limpeza para Corredor LEO Norte",
        "Simulação de desvio para Estágio de foguete Ariane"
    )

    val operationalRecommendations = listOf(
        "Priorizar manobras de desvio em objetos com distância inferior a 10 km.",
        "Evitar janelas operacionais em zonas LEO críticas até nova varredura.",
        "Registrar solicitações de limpeza orbital para áreas com risco alto ou crítico.",
        "Revisar rotas GEO quando houver objetos antigos próximos ao plano operacional."
    )
}
