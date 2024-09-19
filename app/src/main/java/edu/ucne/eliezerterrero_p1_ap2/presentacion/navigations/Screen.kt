package edu.ucne.eliezerterrero_p1_ap2.presentacion.navigations

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object ListScreen: Screen()

    @Serializable
    data class RegistroScreen(var id :Int ):Screen()
}