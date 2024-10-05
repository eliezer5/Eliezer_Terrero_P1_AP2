package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

sealed interface VentaEvent {

    data object save : VentaEvent
    data class delete(val venta: VentaEntity): VentaEvent
    data object nuevo: VentaEvent
    data class editarVenta(val id: Int): VentaEvent

    data class onChangePrecio(val precio: String): VentaEvent
    data class onChangeNombre(val nombre: String): VentaEvent
    data class onChangeTotal(val total: Double): VentaEvent
    data object onChangeTotalDescuento: VentaEvent
    data class onChangeGalones(val galon: String): VentaEvent
    data class onChangeDescuentoGalon(val DescuentoGalon: String): VentaEvent

}