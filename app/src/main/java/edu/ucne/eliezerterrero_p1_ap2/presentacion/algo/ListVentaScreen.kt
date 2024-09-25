package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.eliezerterrero_p1_ap2.local.data.entities.VentaEntity

@Composable
fun ListVentaScreen(
    goToAdd: ()-> Unit,
    onSelect: (Int) -> Unit,
    viewModel: VentaViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ListVentaBodyScreen(uiState = uiState, goToAdd = goToAdd, onSelect)
}

@Composable
fun ListVentaBodyScreen(
    uiState: UiState,
    goToAdd: () -> Unit,
    onSelect: (Int) -> Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = goToAdd ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Agregar Venta")
            }
        }
    ) {innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Lista Ventas")
            Spacer(modifier = Modifier.height(20.dp))

            Row (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "Id",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Nombre",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "DescuentoGalon",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Precio",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Total Descontado",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Total",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.weight(1f)
                )
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.ventas){
                    VentaRow(it, onSelect)
                }
            }
        }
    }
}

@Composable
fun VentaRow(it: VentaEntity, onSelect: (Int) -> Unit){
    Row (
        modifier = Modifier
            .clickable { onSelect(it.ventaId ?: 0) }
            .fillMaxSize()
    ){
        Text(
            text = it.ventaId.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = it.nombreEmpresa?:"",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = it.descuentoGalon.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = it.precio.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = it.totalDescontado.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = it.total.toString(),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f)
        )
    }
}