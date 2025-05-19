package com.example.serviraviaticos.repositorio

import com.example.serviraviaticos.modelo.ProgramacionModelo
import kotlinx.coroutines.delay

object ProgramacionRepositorio {
    suspend fun obtenerProgramaciones(): List<ProgramacionModelo> {
        delay(1000) // Simula consumo de API. Aquí usarías Retrofit realmente.
        return listOf(
            ProgramacionModelo(
                "12345", "Pendiente", "GLP Proveedor", "ABC-123", "XYZ-789",
                "Lima", "2025-05-15", esReten = false
            ),
            ProgramacionModelo(
                "67890", "En Observacion", "GLP Proveedor 2", "DEF-456", "UVW-000",
                "Arequipa", "2025-05-16", esReten = true
            )
        )
    }
}