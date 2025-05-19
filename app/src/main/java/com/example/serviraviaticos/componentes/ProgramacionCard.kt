package com.example.serviraviaticos.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serviraviaticos.modelo.ProgramacionModelo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Event
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.serviraviaticos.R
@Composable
fun ProgramacionCard(programacion: ProgramacionModelo) {
    // Color del estado
    val estadoColor = when (programacion.estadoViatico.uppercase()) {
        "PENDIENTE" -> Color(0xFFFFC107) // Amarillo
        "EN OBSERVACION" -> Color(0xFFD32F2F) // Rojo
        else -> Color.Gray
    }

    // Chip de tipo
    val chipColor = if (programacion.esReten) Color(0xFF2196F3) else Color(0xFF4CAF50)
    val chipTexto = if (programacion.esReten) "Reten" else "Normal"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // esto es CLAVE
            .padding(vertical = 6.dp)
    ) {
    // Borde lateral izquierdo coloreado
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
                .background(estadoColor)
        )

        // Card con sombra y fondo blanco
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(start = 0.dp), // evitar separación con el borde lateral
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                // Encabezado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "SCOOP:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Text(
                            text = programacion.scoop,
                            fontSize = 12.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Estado:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        Text(
                            text = programacion.estadoViatico.uppercase(),
                            color = estadoColor,
                            fontSize = 12.sp
                        )
                    }
                }

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )

                // Chip + Proveedor
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(color = chipColor, shape = RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = chipTexto,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = programacion.proveedor,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Placas y Fecha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Placas:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "${programacion.placaTracto}/${programacion.placaCarreta}",
                            fontSize = 12.sp
                        )
                    }
                    Column {
                        Text(
                            text = "Fecha Partida:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = programacion.fechaPartida,
                            fontSize = 12.sp
                        )
                    }
                }


                Spacer(modifier = Modifier.height(18.dp))

                // Dirección y camión (en fila)
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Box con ancho fijo para asegurar alineación izquierda
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(80.dp),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.camion_camino),
                            contentDescription = "Camión con camino",
                            modifier = Modifier.size(80.dp)
                        )
                    }
                    // Dirección de planta alineada a la derecha del ícono
                    Column(
                        modifier = Modifier.fillMaxWidth(), // esto toma el resto del espacio
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = "Dirección de Planta:",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = programacion.direccionPuntoPartida,
                            fontSize = 12.sp
                        )
                    }
                }

            }
        }
    }
}
