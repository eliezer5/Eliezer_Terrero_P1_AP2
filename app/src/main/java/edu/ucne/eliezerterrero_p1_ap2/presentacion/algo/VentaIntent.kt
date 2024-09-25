package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

sealed interface VentaIntent {

    data object save : VentaIntent
    data object delete: VentaIntent
    data object nuevo: VentaIntent
    data class editarVenta(val id: Int): VentaIntent

    data class onChangePrecio(val precio: Double): VentaIntent
    data class onChangeNombre(val nombre: String): VentaIntent
    data class onChangeTotal(val total: Double): VentaIntent
    data class onChangeTotalDescuento(val totalDescuento: Double): VentaIntent
    data class onChangeGalones(val galon: Double): VentaIntent
    data class onChangeDescuentoGalon(val DescuentoGalon: Double): VentaIntent

}