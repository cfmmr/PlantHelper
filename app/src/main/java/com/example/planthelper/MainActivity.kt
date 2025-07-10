package com.example.planthelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = AppDatabase.getInstance(this)
        val viewModel = MainViewModel(db)
        setContent { MaterialTheme { PlantApp(viewModel) } }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantApp(viewModel: MainViewModel) {
    val plantas by viewModel.plants.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Plant App") },
                actions = {
                    IconButton(onClick = { showAddDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Plant")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            PlantsList(
                planta = plantas,
                onEditPlant = { plant -> viewModel.updatePlanta(plant) },
                onDeletePlant = { plant -> viewModel.deletePlanta(plant) }
            )
        }
    }

    if (showAddDialog) {
        AddPlantDialog(
            onDismiss = { showAddDialog = false },
            onAddPlant = { plant ->
                viewModel.insertPlanta(plant)
                showAddDialog = false
            }
        )
    }
}

@Composable
fun PlantsList(
    planta: List<Planta>,
    onEditPlant: (Planta) -> Unit,
    onDeletePlant: (Planta) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(planta) { planta ->
            PlantCard(
                planta = planta,
                onEdit = { onEditPlant(planta) },
                onDelete = { onDeletePlant(planta) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PlantCard(
    planta: Planta,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = planta.Nome,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = onDelete) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Tipo: ${planta.Tipo}")
            Text("Toxicidade: ${planta.Toxicidade}")
            Text("Fertilização: ${planta.Fertelizacao}")
            Text("Propagação: ${planta.Propagacao}")
            Text("Manutenção: Nível ${planta.Manutencao}")
        }
    }
}

@Composable
fun AddPlantDialog(
    onDismiss: () -> Unit,
    onAddPlant: (Planta) -> Unit
) {
    var nome by remember { mutableStateOf("") }
    val tipo by remember { mutableStateOf("Ornamental") }
    val toxicidade by remember { mutableStateOf("Não") }
    val fertilizacao by remember { mutableStateOf("Mensal") }
    val propagacao by remember { mutableStateOf("Divisão da Raíz") }
    val manutencao by remember { mutableStateOf("1") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add New Plant") },
        text = {
            Column {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newPlanta = Planta(
                        ID_especie = 0, // Auto-generated
                        Nome = nome,
                        Tipo = tipo,
                        Toxicidade = toxicidade,
                        Fertelizacao = fertilizacao,
                        Propagacao = propagacao,
                        Manutencao = manutencao
                    )
                    onAddPlant(newPlanta)
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}