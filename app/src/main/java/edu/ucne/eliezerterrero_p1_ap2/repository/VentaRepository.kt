package edu.ucne.eliezerterrero_p1_ap2.repository

import edu.ucne.eliezerterrero_p1_ap2.local.data.dao.VentaDao
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

import javax.inject.Inject

class VentaRepository @Inject constructor(
    private val ventaDao: VentaDao
) {
    suspend fun save(algo: VentaEntity) = ventaDao.save(algo)
    suspend fun getAlgo(id: Int) = ventaDao.find(id)
    suspend fun delete(algo: VentaEntity) = ventaDao.delete(algo)
    fun getAlgos() = ventaDao.getAll()

}