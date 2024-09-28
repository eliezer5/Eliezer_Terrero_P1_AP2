package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity
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
            if (validar()){
                ventaRepository.save(_uiState.value.toEntity())
            }
            else {
                _uiState.update {
                    it.copy(
                        errorMessage = uiState.value.errorMessage
                    )
                }
            }
        }
    }

    private fun nuevo(){
        _uiState.update { it.copy(
            ventaId = null,
            descuentoGalon = 0.0,
            precio = 0.0,
            galones = 0.0,
            errorMessage = "",
            nombreEmpresa = "",
            totalDescontado = 0.0,
            total = 0.0
        ) }
    }

    private fun editarVenta(id: Int){
        viewModelScope.launch {
            val venta = ventaRepository.getAlgo(id)
            if (id > 0){
                _uiState.update { it.copy(
                    ventaId = venta.ventaId,
                    descuentoGalon = venta.descuentoGalon ?: 0.0,
                    precio = venta.precio,
                    galones = venta.galones,
                    nombreEmpresa = venta.nombreEmpresa?: "",
                    totalDescontado = venta.totalDescontado?: 0.0,
                    total = venta.total
                ) }
            }
        }
    }

    private fun delete(venta: VentaEntity){
        viewModelScope.launch {
            ventaRepository.delete(venta)
        }
    }

    private fun getVentas(){
        viewModelScope.launch {
            ventaRepository.getAlgos().collect{ventas -> _uiState.update { it.copy( ventas = ventas) }}
        }
    }

    private fun onChangePrecio(precio: String){
        val newPrecio = precio.toDouble()
        _uiState.update {
            it.copy(
                precio = newPrecio
            )
        }
    }

    private fun onChangeDescuentoGalon(galon: String){
        val newDescuento = galon.toDouble()
        _uiState.update {
            it.copy(
                descuentoGalon = newDescuento
            )
        }
    }

    private fun onChangeGalon(galon: String){
        val newGalon = galon.toDouble()
        _uiState.update {
            it.copy(
                galones = newGalon
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


    private fun onChangeTotalDescontado(){
        val galones = _uiState.value.galones?: 0.0
        val descuento = _uiState.value.descuentoGalon?: 0.0

        val totaldes = galones * descuento
        _uiState.update {
            it.copy(
                totalDescontado = totaldes
            )
        }
    }

     fun validar(): Boolean{
        if(uiState.value.nombreEmpresa.isBlank() ){
            _uiState.update {
                it.copy(
                    errorMessage = "Nombre vacio"
                )

            }
            return false
        }

        if(uiState.value.galones == null ){
            _uiState.update {
                it.copy(
                    errorMessage = "galones vacio"
                )

            }
            return false
        }

        if(uiState.value.descuentoGalon == null ){
            _uiState.update {
                it.copy(
                    errorMessage = "descuento vacio"
                )

            }
            return false
        }

        if(uiState.value.precio == null ){
            _uiState.update {
                it.copy(
                    errorMessage = "precio vacio"
                )

            }
            return false
        }

         _uiState.update {
             it.copy(
                 errorMessage = "Guardado Correctamente"
             )
         }
        return true
    }


    private fun onChangeTotal(total: Double){

        val descontado = _uiState.value.totalDescontado
        val galones = _uiState.value.galones?:0.0
        val precio = _uiState.value.precio?:0.0
        val newTotal = (precio * galones) - descontado
        _uiState.update {
            it.copy(
                total = newTotal
            )
        }
    }

    fun onEvent(event: VentaEvent){
        when(event){
            is VentaEvent.onChangeNombre -> onChangeNombre(event.nombre)
            is VentaEvent.delete -> delete(event.venta)
            is VentaEvent.editarVenta -> editarVenta(event.id)
            VentaEvent.nuevo -> nuevo()
            is VentaEvent.onChangeDescuentoGalon -> onChangeDescuentoGalon(event.DescuentoGalon)
            is VentaEvent.onChangeGalones -> onChangeGalon(event.galon)
            is VentaEvent.onChangePrecio -> onChangePrecio(event.precio)
            is VentaEvent.onChangeTotal -> onChangeTotal(event.total)

            VentaEvent.save -> save()
            is VentaEvent.onChangeTotalDescuento -> onChangeTotalDescontado()
        }
    }

}