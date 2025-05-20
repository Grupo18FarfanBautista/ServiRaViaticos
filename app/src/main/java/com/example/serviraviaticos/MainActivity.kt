package com.example.serviraviaticos

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.serviraviaticos.navegacion.BarraNavegacionInferior
import com.example.serviraviaticos.navegacion.GrafoNavegacion
import com.example.serviraviaticos.navegacion.ItemNavegacionInferior
import com.example.serviraviaticos.navegacion.RutasNavegacion
import com.example.serviraviaticos.ui.theme.ServiRaViaticosTheme
import com.example.serviraviaticos.vista.ProgramacionPendienteVista
import android.os.Build
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import android.view.Window
import androidx.activity.viewModels
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import com.example.serviraviaticos.vista_modelo.ProgramacionVistaModelo

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm: ProgramacionVistaModelo by viewModels() // IMPORTANTE


        setContent {

            ServiRaViaticosTheme {
                val navController = rememberNavController()
                GrafoNavegacion(navController, vm)
                val rutaActual = navController.currentBackStackEntryFlow.collectAsState(initial = null).value?.destination?.route

                val items = listOf(
                    ItemNavegacionInferior(RutasNavegacion.Liquidaciones, Icons.Default.ContentCopy, "Liquidaciones"),
                    ItemNavegacionInferior(RutasNavegacion.Inicio, Icons.Default.Home, "Inicio"),
                    ItemNavegacionInferior(RutasNavegacion.Otra, Icons.Default.ContentPaste, "Inicio")
                )
                @Composable
                fun CambiarColorBarraEstado(color: Color) {
                    val view = LocalView.current
                    if (!view.isInEditMode) {
                        SideEffect {
                            val window = (view.context as Activity).window
                            window.statusBarColor = color.toArgb()
                            WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
                        }
                    }
                }
                CambiarColorBarraEstado(Color(0xFF003E92))

                Scaffold(
                    bottomBar = {
                        BarraNavegacionInferior(navController, items, rutaActual)
                    }
                ) { padding ->
                    Box(modifier = Modifier.padding(padding)) {
                        GrafoNavegacion(navController,vm)
                    }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ServiRaViaticosTheme {
        Greeting("Android")
    }
}