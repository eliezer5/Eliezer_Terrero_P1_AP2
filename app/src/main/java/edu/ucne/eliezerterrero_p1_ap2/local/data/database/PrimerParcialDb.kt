package edu.ucne.eliezerterrero_p1_ap2.local.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.eliezerterrero_p1_ap2.local.data.dao.algoDao
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.algoEntity

@Database(
    entities = [
        algoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class PrimerParcialDb : RoomDatabase() {
    abstract fun algoDao(): algoDao
}