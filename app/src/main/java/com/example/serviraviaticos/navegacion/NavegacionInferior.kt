package com.example.serviraviaticos.navegacion

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.Alignment

@Composable
fun BarraNavegacionInferior(
    navController: NavController,
    items: List<ItemNavegacionInferior>,
    rutaActual: String?
) {
    NavigationBar(
        containerColor = Color(0xFF003E92) // azul oscuro
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.ruta == rutaActual,
                onClick = {
                    navController.navigate(item.ruta) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = item.icono,
                            contentDescription = item.titulo,
                            tint = if (item.ruta == rutaActual) Color(0xFF003E92) else Color.White
                        )
                        Text(
                            text = item.titulo,
                            fontSize = 12.sp,
                            color = if (item.ruta == rutaActual) Color(0xFF003E92) else Color.White
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White             )
            )
        }
    }
}
