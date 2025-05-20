package com.example.serviraviaticos.vista

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.serviraviaticos.componentes.FiltroEstadoDropdown
import com.example.serviraviaticos.componentes.ProgramacionCard
import com.example.serviraviaticos.vista_modelo.ProgramacionVistaModelo
@Composable
fun ProgramacionPendienteVista(
    navController: NavController,
    vm: ProgramacionVistaModelo
) {
    val programaciones by vm.programaciones.collectAsState()
    val filtro by vm.filtro.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {

        // 🔵 Título centrado
        Text(
            text = "Programación Pendiente",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
        )

        // 🔵 Línea divisoria
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // 🔵 Etiqueta + Dropdown alineados
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Filtrar por Estado:",
                style = MaterialTheme.typography.bodyMedium
            )
            FiltroEstadoDropdown(filtroActual = filtro) { vm.actualizarFiltro(it) }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 🔵 Lista filtrada
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            val filtradas = programaciones.filter {
                filtro == "Todos" || filtro.isEmpty() || it.estadoViatico == filtro
            }
            items(filtradas) { programacion ->
                ProgramacionCard(programacion = programacion) {
                    vm.seleccionarPorScoop(programacion.scoop)
                    navController.navigate("detalle")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
