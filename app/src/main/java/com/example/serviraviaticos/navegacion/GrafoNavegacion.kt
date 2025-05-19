package com.example.serviraviaticos.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.serviraviaticos.vista.ProgramacionPendienteVista

@Composable
fun GrafoNavegacion(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = RutasNavegacion.Inicio
    ) {
        composable(RutasNavegacion.Inicio) { ProgramacionPendienteVista() }
        composable(RutasNavegacion.Liquidaciones) { /* TODO: Crear PantallaLiquidaciones */ }
        composable(RutasNavegacion.Otra) { /* TODO: Crear OtraPantalla */ }
    }
}
