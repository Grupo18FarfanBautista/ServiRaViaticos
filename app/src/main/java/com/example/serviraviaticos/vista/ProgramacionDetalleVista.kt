package com.example.serviraviaticos.vista

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Event
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.serviraviaticos.R
import com.example.serviraviaticos.modelo.ProgramacionModelo
import com.example.serviraviaticos.vista_modelo.ProgramacionVistaModelo
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramacionDetalleVista(
    vm: ProgramacionVistaModelo,
    onBack: () -> Unit
) {
    val programacion by vm.programacionSeleccionada.collectAsState()

    var fechaIngreso by remember { mutableStateOf("") }
    var fechaPartida by remember { mutableStateOf("") }
    var kilometraje by remember { mutableStateOf("") }

    var showFechaIngresoPicker by remember { mutableStateOf(false) }
    var showFechaPartidaPicker by remember { mutableStateOf(false) }

    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val dateTimeFormatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())

    val context = LocalContext.current

    if (showFechaIngresoPicker) {
        DatePickerDialog(
            context,
            { _, year, month, day ->
                val cal = Calendar.getInstance()
                cal.set(year, month, day)
                fechaIngreso = dateFormatter.format(cal.time)
                showFechaIngresoPicker = false
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    if (showFechaPartidaPicker) {
        val now = Calendar.getInstance()
        DatePickerDialog(
            context,
            { _, year, month, day ->
                TimePickerDialog(
                    context,
                    { _, hour, minute ->
                        val cal = Calendar.getInstance()
                        cal.set(year, month, day, hour, minute)
                        fechaPartida = dateTimeFormatter.format(cal.time)
                        showFechaPartidaPicker = false
                    },
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
                ).show()
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        // Encabezado
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
            }
            Text(
                text = "Información de Programación",
                style = MaterialTheme.typography.titleMedium,
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (programacion == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Primera fila
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Programación:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(programacion!!.scoop, fontSize = 13.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("N° Docs:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text("3", fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Segunda fila
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Proveedor:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(programacion!!.proveedor, fontSize = 13.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Fecha de ingreso:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (fechaIngreso.isNotBlank()) fechaIngreso else "--/--/----",
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(onClick = { showFechaIngresoPicker = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "Seleccionar fecha"
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                // Kilometraje (input largo pero delgado)
                Column {
                    Text("Kilometraje:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    TextField(
                        value = kilometraje,
                        onValueChange = { kilometraje = it },
                        placeholder = { Text("Ingrese km", fontSize = 10.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(43.dp),
                        textStyle = LocalTextStyle.current.copy(fontSize = 10.sp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Placa y Partida
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Placa Tracto:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(programacion!!.placaTracto, fontSize = 13.sp)
                    }
                    Column(modifier = Modifier.weight(1f)) {
                        Text("Fecha y Hora de Partida:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (fechaPartida.isNotBlank()) fechaPartida else "--/--/---- --:--",
                                fontSize = 13.sp,
                                modifier = Modifier.weight(1f)
                            )
                            IconButton(onClick = { showFechaPartidaPicker = true }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_calendar),
                                    contentDescription = "Seleccionar fecha y hora"
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text("Placa Carreta:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Text(programacion!!.placaCarreta, fontSize = 13.sp)

                Spacer(modifier = Modifier.height(16.dp))

                // Imagen y Dirección
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.camion_camino),
                        contentDescription = "Camión",
                        modifier = Modifier
                            .size(80.dp)
                            .padding(end = 12.dp)
                    )
                    Column {
                        Text("Dirección de Planta:", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        Text(programacion!!.direccionPuntoPartida, fontSize = 13.sp)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Acción */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Registrar Viático")
                }
            }
        }
    }
}
