package com.example.serviraviaticos.componentes

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltroEstadoDropdown(filtroActual: String, onFiltroSeleccionado: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf("Todos", "Pendiente", "En Observacion")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            readOnly = true,
            value = if (filtroActual.isEmpty()) "Seleccionar" else filtroActual,
            onValueChange = {},
            label = { Text("Filtro") },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp // Tamaño del texto principal (valor mostrado)
            ),
            modifier = Modifier
                .width(200.dp) // tamaño más compacto pero funcional
                .menuAnchor(), // importante para que el dropdown funcione
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )


        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onFiltroSeleccionado(opcion)
                        expanded = false
                    }
                )
            }
        }
    }
}