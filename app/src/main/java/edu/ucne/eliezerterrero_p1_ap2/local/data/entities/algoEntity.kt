package edu.ucne.eliezerterrero_p1_ap2.local.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "algos")
data class algoEntity (
    @PrimaryKey
    val algoId: Int? =null
)