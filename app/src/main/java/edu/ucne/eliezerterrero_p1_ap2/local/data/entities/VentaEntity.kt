package edu.ucne.eliezerterrero_p1_ap2.local.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ventas")
data class VentaEntity (
    @PrimaryKey
    val ventaId: Int? =null,
    val galones: Double? = null,
    val precio: Double? = null,
    val descuentoGalon : Double? = null,
    val totalDescontado: Double? =null,
    val total: Double? = null,
    val nombreEmpresa: String? =""

)