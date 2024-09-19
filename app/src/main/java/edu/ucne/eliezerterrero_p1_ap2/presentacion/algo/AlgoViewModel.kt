package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.eliezerterrero_p1_ap2.repository.AlgoRepository
import javax.inject.Inject

@HiltViewModel
class AlgoViewModel @Inject constructor(
    private val algoRepository: AlgoRepository
)  {
}