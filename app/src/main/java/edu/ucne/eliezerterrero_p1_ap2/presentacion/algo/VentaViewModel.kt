package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.eliezerterrero_p1_ap2.repository.VentaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VentaViewModel @Inject constructor(
    private val ventaRepository: VentaRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()


    init {
        getVentas()
    }

    private fun save(){
        viewModelScope.launch {
            ventaRepository.save(_uiState.value.toEntity())
        }
    }

    private fun nuevo(){
        _uiState.update { it.copy(
            ventaId = null,
            descuentoGalon = null,
            precio = null,
            galones = null,
            errorMessage = "",
            nombreEmpresa = "",
            totalDescontado = null,
            total = null
        ) }
    }

    private fun editarVenta(id: Int){
        viewModelScope.launch {
            val venta = ventaRepository.getAlgo(id)
            if (id > 0){
                _uiState.update { it.copy(
                    ventaId = venta.ventaId,
                    descuentoGalon = venta.descuentoGalon,
                    precio = venta.precio,
                    galones = venta.galones,
                    nombreEmpresa = venta.nombreEmpresa,
                    totalDescontado = venta.totalDescontado,
                    total = venta.total
                ) }
            }
        }
    }

    private fun delete(){
        viewModelScope.launch {
            ventaRepository.delete(_uiState.value.toEntity())
        }
    }

    private fun getVentas(){
        viewModelScope.launch {
            ventaRepository.getAlgos().collect{ventas -> _uiState.update { it.copy( ventas = ventas) }}
        }
    }

    private fun onChangePrecio(precio: Double){
        _uiState.update {
            it.copy(
                descuentoGalon = precio
            )
        }
    }

    private fun onChangeDescuentoGalon(galon: Double){
        _uiState.update {
            it.copy(
                descuentoGalon = galon
            )
        }
    }

    private fun onChangeGalon(galon: Double){
        _uiState.update {
            it.copy(
                galones = galon
            )
        }
    }

    private fun onChangeNombre(nombre: String){
        _uiState.update {
            it.copy(
                nombreEmpresa = nombre
            )
        }
    }


    private fun onChangeTotalDescontado(totalDescuento: Double){
        _uiState.update {
            it.copy(
                totalDescontado = totalDescuento
            )
        }
    }


    private fun onChangeTotal(total: Double){
        _uiState.update {
            it.copy(
                total = total
            )
        }
    }

    fun onEvent(event: VentaIntent){
        when(event){
            VentaIntent.delete -> delete()
            is VentaIntent.editarVenta -> editarVenta(event.id)
            VentaIntent.nuevo -> nuevo()
            is VentaIntent.onChangeDescuentoGalon -> onChangeDescuentoGalon(event.DescuentoGalon)
            is VentaIntent.onChangeGalones -> onChangeGalon(event.galon)
            is VentaIntent.onChangeNombre -> onChangeNombre(event.nombre)
            is VentaIntent.onChangePrecio -> onChangePrecio(event.precio)
            is VentaIntent.onChangeTotal -> onChangeTotal(event.total)
            is VentaIntent.onChangeTotalDescuento -> onChangeTotalDescontado(event.totalDescuento)
            VentaIntent.save -> save()
        }
    }

}