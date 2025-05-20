package com.example.serviraviaticos.navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.serviraviaticos.vista.ProgramacionDetalleVista
import com.example.serviraviaticos.vista.ProgramacionPendienteVista
import com.example.serviraviaticos.vista_modelo.ProgramacionVistaModelo

@Composable
fun GrafoNavegacion(navController: NavHostController, vm: ProgramacionVistaModelo) {
    NavHost(
        navController = navController,
        startDestination = RutasNavegacion.Inicio
    ) {
        composable(RutasNavegacion.Inicio) {ProgramacionPendienteVista(navController, vm) }
        composable(RutasNavegacion.Liquidaciones) { /* TODO: Crear PantallaLiquidaciones */ }
        composable(RutasNavegacion.Otra) { /* TODO: Crear OtraPantalla */ }
        composable("detalle") {
            ProgramacionDetalleVista(vm = vm) {
                navController.popBackStack()
            }
        }

    }
}
