package edu.ucne.eliezerterrero_p1_ap2.presentacion.algo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun AddVentaScreen(
    goToBack: () -> Unit,
    viewModel: VentaViewModel = hiltViewModel(),
    ventaId: Int

) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    AddVentaBodyScreen(
        goToBack = goToBack,
        uiState = uiState,
        ventaId = ventaId,
        onEvent = { event -> viewModel.onEvent(event) })


}


@Composable
fun AddVentaBodyScreen(
    goToBack: () -> Unit,
    uiState: UiState,
    ventaId: Int,
    onEvent: (VentaEvent) -> Unit
) {

    LaunchedEffect(key1 = true) {
        if (ventaId > 0) {
            onEvent(VentaEvent.editarVenta(ventaId))
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = goToBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(text = "Agregar Venta")
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                label = { Text(text = "Nombre") },
                value = uiState.nombreEmpresa,
                onValueChange = { onEvent(VentaEvent.onChangeNombre(it)) },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                label = { Text(text = "Galones") },
                value = uiState.galones?.toString() ?: "",
                onValueChange = {
                    onEvent(VentaEvent.onChangeGalones(it))
                    onEvent(VentaEvent.onChangeTotalDescuento)
                    onEvent(VentaEvent.onChangeTotal(it.toDouble()))
                },
                modifier = Modifier.fillMaxWidth()


            )

            OutlinedTextField(
                label = { Text(text = "Descuento por Galon") },
                value = uiState.descuentoGalon?.toString() ?: "",
                onValueChange = {
                    onEvent(VentaEvent.onChangeDescuentoGalon(it))
                    onEvent(VentaEvent.onChangeTotalDescuento)
                    onEvent(VentaEvent.onChangeTotal(it.toDouble()))
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                label = { Text(text = "Precio") },
                value = uiState.precio?.toString() ?: "",
                onValueChange = {
                    onEvent(VentaEvent.onChangePrecio(it))
                    onEvent(VentaEvent.onChangeTotal(it.toDouble()))
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                label = { Text(text = "Total descontado") },
                value = uiState.totalDescontado.toString(),
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            OutlinedTextField(
                label = { Text(text = "Total") },
                value = uiState.total.toString(),
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            uiState.errorMessage.let { error ->
                Text(text = error, color = Color.Red)
            }


            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                OutlinedButton(onClick = {
                    onEvent(VentaEvent.nuevo)

                }) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "nuevo")
                    Text(text = "nuevo")
                }
                OutlinedButton(onClick = {
                    onEvent(VentaEvent.save)
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
                if (ventaId > 0)
                    OutlinedButton(onClick = {
                        onEvent(VentaEvent.delete)
                        onEvent(VentaEvent.nuevo)
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                        Text(text = "Delete", color = Color.Red)
                    }
            }
        }
    }
}