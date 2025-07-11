package com.example.planthelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val colors = darkColorScheme(background = Color(0xFF221B1B), surface = Color.White, primary = Color(0xFF41624D), secondary = Color(0xFFEEDFD3))
        val db = AppDatabase.getInstance(this)
        val viewModel = MainViewModel(db)
        setContent { MaterialTheme(colorScheme = colors) { PlantApp(viewModel) } }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantApp(viewModel: MainViewModel) {
    val plantas by viewModel.plants.collectAsState(); var showFilters by remember { mutableStateOf(false) }; var showDialog by remember { mutableStateOf(false) }; var editPlanta by remember { mutableStateOf<Planta?>(null) }
    Box(Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(Modifier.fillMaxSize().padding(16.dp).verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
            TopAppBar(
                title = { Text("Cuidados - Plantas", color = MaterialTheme.colorScheme.secondary, textAlign = TextAlign.Center, fontSize = 28.sp, fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth()) },
                actions = { IconButton(onClick = { showFilters = !showFilters }) { Icon(Icons.Default.Menu, "Filtros", tint = MaterialTheme.colorScheme.secondary) } },
                colors = TopAppBarDefaults.topAppBarColors( containerColor = Color.Transparent)
            ); Spacer(Modifier.height(16.dp))
            Row(Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalArrangement = Arrangement.Center) {
                Button(onClick = { editPlanta = null; showDialog = true }, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f), contentColor = MaterialTheme.colorScheme.secondary), shape = RoundedCornerShape(50)) { Text("Inserir", fontWeight = FontWeight.Bold) }
            }; Spacer(modifier = Modifier.height(16.dp))
            PlantsList(plantas = plantas, onEdit = { plant -> editPlanta = plant; showDialog = true }, onDelete = { plant -> viewModel.deletePlanta(plant) })
        }
        if (showFilters) { DrawerContent(onClose = { showFilters = false }) }
        if (showDialog) { PlantDialog(initial = editPlanta, onDismiss = { showDialog = false; editPlanta = null }, onConfirm = { plant -> if (editPlanta == null) { viewModel.insertPlanta(plant) } else { viewModel.updatePlanta(plant) }; showDialog = false; editPlanta = null}) }
    }
}
@Composable
fun PlantsList(plantas: List<Planta>, onEdit: (Planta) -> Unit, onDelete: (Planta) -> Unit) {
    Card(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(15.dp)), colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            item { Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                listOf("ID","Nome","Tipo","Toxicidade","Fertilização","Propagação","Manutenção", "").forEach { header -> Text(text = header, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary, textAlign = TextAlign.Center, modifier = Modifier.weight(1f)) }
            } }
            items(plantas) { planta -> PlantRow(planta = planta, onEdit = onEdit, onDelete = onDelete) }
        }
    }
}
@Composable fun TableCell(text: String) { Text(text = text, color = Color.Black, fontSize = 12.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 4.dp)) }
@Composable
fun PlantRow(planta: Planta, onEdit: (Planta) -> Unit, onDelete: (Planta) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(vertical = 8.dp).border(1.dp, Color.LightGray, RoundedCornerShape(4.dp)), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        TableCell(planta.ID_especie.toString());TableCell(planta.Nome); TableCell(planta.Tipo); TableCell(planta.Toxicidade); TableCell(planta.Fertelizacao); TableCell(planta.Propagacao); TableCell(planta.Manutencao);
        IconButton(onClick = { onEdit(planta) }, Modifier.size(32.dp)) { Icon(Icons.Default.Edit, "Editar", tint = MaterialTheme.colorScheme.primary) }
        IconButton(onClick = { onDelete(planta) }, Modifier.size(32.dp)) { Icon(Icons.Default.Delete, "Eliminar", tint = Color.Red) }
    }
}
@Composable
fun DrawerContent(onClose: () -> Unit) { val selectedType = remember { mutableStateOf(listOf<String>()) }
    Column( modifier = Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.primary).padding(16.dp)) {
        Text("Filtros", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold); Spacer(modifier = Modifier.height(16.dp))
        listOf("Ornamental","Feto","Suculenta","Cacto").forEach { type -> FilterCheckbox(type, selectedType)}; Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = onClose, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Fechar") }
    }
}
@Composable
fun FilterCheckbox(label: String, selectedType: MutableState<List<String>>) { var checked by remember { mutableStateOf(label in selectedType.value) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 4.dp)) {
        Checkbox(checked = checked,
            onCheckedChange = { checked = it; val current = selectedType.value.toMutableList(); if (it) current.add(label) else current.remove(label); selectedType.value = current },
            colors = CheckboxDefaults.colors(checkedColor = Color.White)
        ); Text(label, color = Color.White)
    }
}
@Composable
fun PlantDialog(initial: Planta?, onDismiss: () -> Unit, onConfirm: (Planta) -> Unit) {
    var nome by remember { mutableStateOf(initial?.Nome ?: "") }; var tipo by remember { mutableStateOf(initial?.Tipo ?: "") }; var toxicidade by remember { mutableStateOf(initial?.Toxicidade ?: "") }; var fertilizacao by remember { mutableStateOf(initial?.Fertelizacao ?: "") }; var propagacao by remember { mutableStateOf(initial?.Propagacao ?: "") }; var manutencao by remember { mutableStateOf(initial?.Manutencao ?: "") }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { Button(onClick = { val id = initial?.ID_especie?: 0; onConfirm(Planta(id, nome, tipo, toxicidade, fertilizacao, propagacao, manutencao)) }) { Text("Guardar") } },
        dismissButton = { Button(onClick = onDismiss) { Text("Cancelar") } },
        title = { Text(if (initial == null) "Inserir Planta" else "Editar Planta") },
        text = { Column {
            OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") });
            OutlinedTextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo") });
            OutlinedTextField(value = toxicidade, onValueChange = { toxicidade = it }, label = { Text("Toxicidade") });
            OutlinedTextField(value = fertilizacao, onValueChange = { fertilizacao = it }, label = { Text("Fertilizacao") });
            OutlinedTextField(value = propagacao, onValueChange = { propagacao = it }, label = { Text("Propagacao") });
            OutlinedTextField(value = manutencao, onValueChange = { manutencao = it }, label = { Text("Manutencao") });
        } }
    )
}