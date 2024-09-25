package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

data class UiState (
    val ventaId: Int? = null,
    val nombreEmpresa : String? = "",
    val galones: Double? = null,
    val descuentoGalon: Double? = null,
    val precio: Double? = null,
    val totalDescontado: Double? = null,
    val total:Double? = null,
    val ventas: List<VentaEntity> = emptyList(),
    val errorMessage: String? = ""
)

fun UiState.toEntity() = VentaEntity(
    ventaId = ventaId,
    nombreEmpresa = nombreEmpresa,
    galones = galones,
    descuentoGalon = descuentoGalon,
    precio = precio,
    total =  total,
    totalDescontado = totalDescontado



)