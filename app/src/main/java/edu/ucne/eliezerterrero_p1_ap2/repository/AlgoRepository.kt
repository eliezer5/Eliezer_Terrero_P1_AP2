package edu.ucne.eliezerterrero_p1_ap2.repository

import edu.ucne.eliezerterrero_p1_ap2.local.data.dao.algoDao
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.algoEntity
import javax.inject.Inject

class AlgoRepository @Inject constructor(
    private val algoDao: algoDao
) {
    suspend fun save(algo: algoEntity) = algoDao.save(algo)
    suspend fun getAlgo(id: Int) = algoDao.find(id)
    suspend fun delete(algo: algoEntity) = algoDao.delete(algo)
    fun getAlgos() = algoDao.getAll()

}