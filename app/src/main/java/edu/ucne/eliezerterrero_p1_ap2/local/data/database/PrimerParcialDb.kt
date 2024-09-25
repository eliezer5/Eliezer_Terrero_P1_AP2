package edu.ucne.eliezerterrero_p1_ap2.local.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.eliezerterrero_p1_ap2.local.data.dao.VentaDao
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

@Database(
    entities = [
        VentaEntity::class,
    ],
    version = 2,
    exportSchema = false
)
abstract class PrimerParcialDb : RoomDatabase() {
    abstract fun ventaDao(): VentaDao
}