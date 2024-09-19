package edu.ucne.eliezerterrero_p1_ap2.local.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.algoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface algoDao {
    @Upsert
    suspend fun save(algo: algoEntity)

    @Query("""
        select * from algos where algoId=:id
        limit 1
    """)

    suspend fun find(id: Int): algoEntity

    @Delete
    suspend fun delete(algo: algoEntity)
    @Query("""Select * from algos""")
    fun getAll(): Flow<List<algoEntity>>


}