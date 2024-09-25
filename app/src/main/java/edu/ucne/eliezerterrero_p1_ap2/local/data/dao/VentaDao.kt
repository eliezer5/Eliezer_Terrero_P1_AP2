package edu.ucne.eliezerterrero_p1_ap2.local.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {
    @Upsert
    suspend fun save(algo: VentaEntity)

    @Query("""
        select * from ventas where ventaId=:id
        limit 1
    """)

    suspend fun find(id: Int): VentaEntity

    @Delete
    suspend fun delete(algo: VentaEntity)
    @Query("""Select * from ventas""")
    fun getAll(): Flow<List<VentaEntity>>


}