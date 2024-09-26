package edu.ucne.eliezerterrero_p1_ap2.presentacion.navigations

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.eliezerterrero_p1_ap2.presentacion.algo.AddVentaScreen
import edu.ucne.eliezerterrero_p1_ap2.presentacion.algo.ListVentaScreen

@Composable
fun NavHostExamen(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = Screen.ListScreen) {

        composable<Screen.ListScreen> {


            ListVentaScreen(
                goToAdd = { navHostController.navigate(Screen.RegistroScreen(0)) },
                onSelect = { navHostController.navigate(Screen.RegistroScreen(it)) }
            )
        }

        composable<Screen.RegistroScreen> {
            val id = it.toRoute<Screen.RegistroScreen>().id
            AddVentaScreen(goToBack = { navHostController.navigate(Screen.ListScreen) }, ventaId = id)
        }

    }
}