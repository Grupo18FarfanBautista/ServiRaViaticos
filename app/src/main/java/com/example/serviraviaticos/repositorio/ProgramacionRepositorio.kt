package com.example.serviraviaticos.repositorio

import com.example.serviraviaticos.modelo.ProgramacionModelo
import kotlinx.coroutines.delay

object ProgramacionRepositorio {
    suspend fun obtenerProgramaciones(): List<ProgramacionModelo> {
        delay(1000) // Simula consumo de API
        return listOf(
            ProgramacionModelo(
                scoop = "12345",
                estadoViatico = "Pendiente",
                proveedor = "GLP Proveedor",
                placaTracto = "ABC-123",
                placaCarreta = "XYZ-789",
                direccionPuntoPartida = "Lima",
                fechaPartida = "2025-05-15",
                esReten = false
            ),
            ProgramacionModelo(
                scoop = "67890",
                estadoViatico = "En Observacion",
                proveedor = "GLP Proveedor 2",
                placaTracto = "DEF-456",
                placaCarreta = "UVW-000",
                direccionPuntoPartida = "Arequipa",
                fechaPartida = "2025-05-16",
                esReten = true
            ),
            ProgramacionModelo(
                scoop = "54321",
                estadoViatico = "",
                proveedor = "Empresa XYZ",
                placaTracto = "LMN-111",
                placaCarreta = "LMN-222",
                direccionPuntoPartida = "Trujillo",
                fechaPartida = "2025-06-01",
                esReten = false
            ),
            ProgramacionModelo(
                scoop = "99999",
                estadoViatico = "Pendiente",
                proveedor = "Transportes del Norte",
                placaTracto = "JJJ-999",
                placaCarreta = "KKK-888",
                direccionPuntoPartida = "Piura - Planta GLP",
                fechaPartida = "2025-06-10",
                esReten = true
            ),
            ProgramacionModelo(
                scoop = "11111",
                estadoViatico = "En Observacion",
                proveedor = "Carga Express SAC",
                placaTracto = "TTT-123",
                placaCarreta = "UUU-456",
                direccionPuntoPartida = "Ica - Zona Industrial",
                fechaPartida = "2025-07-20",
                esReten = false
            )
        )
    }

}