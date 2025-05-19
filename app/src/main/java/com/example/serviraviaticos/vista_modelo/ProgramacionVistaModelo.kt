package com.example.serviraviaticos.vista_modelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serviraviaticos.modelo.ProgramacionModelo
import com.example.serviraviaticos.repositorio.ProgramacionRepositorio
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProgramacionVistaModelo : ViewModel() {

    private val _programaciones = MutableStateFlow<List<ProgramacionModelo>>(emptyList())
    val programaciones: StateFlow<List<ProgramacionModelo>> = _programaciones

    private val _filtro = MutableStateFlow("")
    val filtro: StateFlow<String> = _filtro

    init {
        listarProgramaciones()
    }

    fun actualizarFiltro(nuevoFiltro: String) {
        _filtro.value = nuevoFiltro
    }

    private fun listarProgramaciones() {
        viewModelScope.launch {
            _programaciones.value = ProgramacionRepositorio.obtenerProgramaciones()
        }
    }
}