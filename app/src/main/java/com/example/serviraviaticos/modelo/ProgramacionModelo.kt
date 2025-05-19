package com.example.serviraviaticos.modelo

data class ProgramacionModelo(
    val scoop: String,
    val estadoViatico: String,
    val proveedor: String,
    val placaTracto: String,
    val placaCarreta: String,
    val direccionPuntoPartida: String,
    val fechaPartida: String,
    val esReten: Boolean
)