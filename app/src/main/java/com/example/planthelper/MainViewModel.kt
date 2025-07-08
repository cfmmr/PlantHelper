package com.example.planthelper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*
import java.util.concurrent.LinkedBlockingQueue

class MainViewModel(private val db: AppDatabase) : ViewModel() {
    init { viewModelScope.launch { if (db.plantaDao().getAll().isEmpty()) { insert() } } }
    init {
        loadPlanta()
        loadLuminosidade()
        loadAgua()
    }

    private val _plants = MutableStateFlow<List<Planta>>(emptyList())
    val plants: StateFlow<List<Planta>> = _plants
    private fun loadPlanta() { viewModelScope.launch { _plants.value = db.plantaDao().getAll() } }
    fun insertPlanta(planta: Planta) { viewModelScope.launch {
        db.plantaDao().insert(planta)
        loadPlanta()
    }}
    fun updatePlanta(planta: Planta) { viewModelScope.launch {
        db.plantaDao().update(planta)
        loadPlanta()
    }}
    fun deletePlanta(planta: Planta) { viewModelScope.launch {
        db.plantaDao().delete(planta)
        loadPlanta()
    }}

    private val _luminosidades = MutableStateFlow<List<Luminosidade>>(emptyList())
    val luminosidades: StateFlow<List<Luminosidade>> = _luminosidades
    private fun loadLuminosidade() { viewModelScope.launch { _luminosidades.value = db.luminosidadeDao().getAll() } }
    fun insertLuminosidade(luminosidade: Luminosidade) { viewModelScope.launch {
        db.luminosidadeDao().insert(luminosidade)
        loadLuminosidade()
    }}
    fun updateLuminosidade(luminosidade: Luminosidade) { viewModelScope.launch {
        db.luminosidadeDao().update(luminosidade)
        loadLuminosidade()
    }}
    fun deleteLuminosidade(luminosidade: Luminosidade) { viewModelScope.launch {
        db.luminosidadeDao().delete(luminosidade)
        loadLuminosidade()
    }}

    private val _aguas = MutableStateFlow<List<Agua>>(emptyList())
    val aguas: StateFlow<List<Agua>> = _aguas
    private fun loadAgua() { viewModelScope.launch { _aguas.value = db.aguaDao().getAll() } }
    fun insertAgua(agua: Agua) { viewModelScope.launch {
        db.aguaDao().insert(agua)
        loadAgua()
    }}
    fun updateAgua(agua: Agua) { viewModelScope.launch {
        db.aguaDao().update(agua)
        loadAgua()
    }}
    fun deleteAgua(agua: Agua) { viewModelScope.launch {
        db.aguaDao().delete(agua)
        loadAgua()
    }}

    private fun insert() {
        viewModelScope.launch {
            val planta = listOf(
                Planta(1, "Hedera Helix", "Arbusto", "Sim", "Mensal", "Corte do Caule", "2"),
                Planta(2, "Aloe Vera", "Suculenta", "Sim", "Anual", "Divisão da Raíz", "1"),
                Planta(
                    3,
                    "Aglaonema Zinkon",
                    "Ornamental",
                    "Sim",
                    "Mensal",
                    "Divisão da Raíz",
                    "2"
                ),
                Planta(4, "Asparagus Plumosus", "Arbusto", "Sim", "Mensal", "Divisão da Raíz", "2"),
                Planta(
                    5,
                    "Chamaedorea Elegans",
                    "Ornamental",
                    "Não",
                    "Mensal",
                    "Divisão da Raíz",
                    "2"
                ),
                Planta(
                    6,
                    "Calathea Makoyana",
                    "Ornamental",
                    "Não",
                    "Quinzenal",
                    "Divisão da Raíz",
                    "3"
                ),
                Planta(
                    7,
                    "Philodendron Wizard",
                    "Ornamental",
                    "Sim",
                    "Semanal",
                    "Corte do Caule",
                    "1"
                ),
                Planta(
                    8,
                    "Calathea Triostar",
                    "Ornamental",
                    "Não",
                    "Quinzenal",
                    "Divisão da Raíz",
                    "3"
                ),
                Planta(
                    9,
                    "Zamioculcas Raven",
                    "Ornamental",
                    "Sim",
                    "Anual",
                    "Divisão da Raíz",
                    "1"
                ),
                Planta(
                    10,
                    "Tradescantia Fluminesis",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte do Caule",
                    "1"
                ),
                Planta(
                    11,
                    "Dracaena Fragans",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    12,
                    "Epipremnum Aureum",
                    "Ornamental",
                    "Sim",
                    "Mensal",
                    "Corte do Caule",
                    "1"
                ),
                Planta(
                    13,
                    "Peperomia Prostata",
                    "Suculenta",
                    "Não",
                    "ensal",
                    "Corte da Folha",
                    "1"
                ),
                Planta(
                    14,
                    "Alocasia Cuprea",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Divisão da Raíz",
                    "3"
                ),
                Planta(
                    15,
                    "Monstera Adansonii",
                    "Ornamental",
                    "Sim",
                    "Anual",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    16,
                    "Peperomia Caperata",
                    "Suculenta",
                    "Não",
                    "Quinzenal",
                    "Corte da Folha",
                    "1"
                ),
                Planta(17, "Eucalyptus Gunni", "Ornamental", "Sim", "Anual", "Corte do Caule", "3"),
                Planta(18, "Begonia Rex", "Ornamental", "Sim", "Quinzenal", "Corte da Folha", "4"),
                Planta(
                    19,
                    "Begonia Maculata",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte da Folha",
                    "4"
                ),
                Planta(
                    20,
                    "Monstera Deliciosa",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    21,
                    "Caladium Bicolor",
                    "Ornamental",
                    "Sim",
                    "Mensal",
                    "Divisão da Raíz",
                    "4"
                ),
                Planta(
                    22,
                    "Alocasia x Amazonica",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Divisão da Raíz",
                    "4"
                ),
                Planta(
                    23,
                    "Dracaena Reflexa",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    24,
                    "Fittonia Albivenis",
                    "Ornamental",
                    "Não",
                    "Mensal",
                    "Corte da Folha",
                    "3"
                ),
                Planta(
                    25,
                    "Philodendron Cardinal",
                    "Ornamental",
                    "Sim",
                    "Semanal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    26,
                    "Philodendron Hederaceum",
                    "Ornamental",
                    "Não",
                    "Semanal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    27,
                    "Tradescantia Bubblegum",
                    "Ornamental",
                    "Sim",
                    "Quinzenal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    28,
                    "Syngonium Podophylum",
                    "Ornamental",
                    "Sim",
                    "Anual",
                    "Corte do Caule",
                    "3"
                ),
                Planta(
                    29,
                    "Senecio Rowleyanus",
                    "Suculenta",
                    "Sim",
                    "Anual",
                    "Corte do Caule",
                    "3"
                ),
                Planta(
                    30,
                    "Rosa x odorata",
                    "Ornamental",
                    "Não",
                    "Quinzenal",
                    "Corte do Caule",
                    "4"
                ),
                Planta(
                    31,
                    "Ceropegia Woddi",
                    "Suculenta",
                    "Não",
                    "Quinzenal",
                    "Corte do Caule",
                    "2"
                ),
                Planta(
                    32,
                    "Lavandula Augustifolia",
                    "Ornamental",
                    "Sim",
                    "Anual",
                    "Corte do Caule",
                    "3"
                ),
                Planta(33, "Ficus Pumila", "Feto", "Sim", "Mensal", "Corte da Folha", "3"),
                Planta(34, "Dryopteris Flix-mas", "Feto", "Sim", "Anual", "Divisão da Raíz", "3"),
                Planta(
                    35,
                    "Adiantum Capillus-Veneris",
                    "Feto",
                    "Sim",
                    "Anual",
                    "Divisão da Raíz",
                    "4"
                )
            )
            planta.forEach { db.plantaDao().insert(it) }

            val luminosidade = listOf(
                Luminosidade(1, "Direta", "Alta", "Interior e Exterior", 0, 18),
                Luminosidade(2, "Direta", "Alta", "Interior e Exterior", 13, 27),
                Luminosidade(3, "Indireta", "dia", "Interior", 16, 29),
                Luminosidade(4, "Indireta", "Alta", "Interior", 10, 30),
                Luminosidade(5, "Indireta", "Média", "Interior", 20, 27),
                Luminosidade(6, "Indireta", "Média", "Interior", 13, 25),
                Luminosidade(7, "Indireta", "Média", "Interior", 13, 25),
                Luminosidade(8, "Indireta", "Média", "terior", 13, 25),
                Luminosidade(9, "Indireta", "Baixa", "Interior", 13, 26),
                Luminosidade(10, "Indireta", "Alta", "Interior e Exterior", 7, 30),
                Luminosidade(11, "Indireta", "Alta", "Interior", 15, 29),
                Luminosidade(12, "Indireta", "Média", "Interior", 15, 30),
                Luminosidade(13, "Indireta", "Alta", "Interior", 18, 24),
                Luminosidade(14, "Indireta", "Média", "Interior", 15, 30),
                Luminosidade(15, "Indireta", "Alta", "Interior", 10, 30),
                Luminosidade(16, "Direta", "Alta", "Interior", 10, 30),
                Luminosidade(17, "Direta", "Alta", "Exterior", 10, 28),
                Luminosidade(18, "Indireta", "Alta", "Interior", 10, 25),
                Luminosidade(19, "Indireta", "Alta", "Interior", 10, 28),
                Luminosidade(20, "Indireta", "Média", "Interior", 10, 30),
                Luminosidade(21, "Indireta", "Média", "Interior", 7, 25),
                Luminosidade(22, "Indireta", "Média", "Interior", 15, 30),
                Luminosidade(23, "direta", "Alta", "Interior e Exterior", 15, 29),
                Luminosidade(24, "Indireta", "Alta", "Interior", 18, 30),
                Luminosidade(25, "direta", "Média", "Interior", 13, 25),
                Luminosidade(26, "Indireta", "Média", "Interior", 13, 30),
                Luminosidade(27, "Indireta", "Alta", "Interior e Exterior", 7, 30),
                Luminosidade(28, "Indireta", "Alta", "Interior", 17, 27),
                Luminosidade(29, "Indireta", "Alta", "Exterior", 10, 26),
                Luminosidade(30, "Direta", "Alta", "Exterior", 10, 25),
                Luminosidade(31, "Indireta", "Alta", "Interior e Exterior", 15, 30),
                Luminosidade(32, "Direta", "Alta", "Interior e Exterior", 18, 30),
                Luminosidade(33, "Indireta", "Média", "Interior e Exterior", 13, 24),
                Luminosidade(34, "Direta", "Alta", "Exterior", 15, 30),
                Luminosidade(35, "Indireta", "Média", "Interior e Exterior", 16, 24)
            )
            luminosidade.forEach { db.luminosidadeDao().insert(it) }

            val agua = listOf(
                Agua(1, 50, "2", "Normal", 7),
                Agua(2, 55, "1", "Pouca", 21),
                Agua(3, 60, "2", "Normal", 7),
                Agua(4, 60, "3", "Normal", 5),
                Agua(5, 55, "1", "Normal", 10),
                Agua(6, 70, "3", "Muita", 5),
                Agua(7, 50, "1", "Normal", 10),
                Agua(8, 70, "3", "Muita", 7),
                Agua(9, 45, "1", "Pouca", 7),
                Agua(10, 50, "2", "Muita", 7),
                Agua(11, 50, "1", "Normal", 10),
                Agua(12, 50, "2", "Normal", 14),
                Agua(13, 50, "2", "Normal", 15),
                Agua(14, 70, "2", "Muita", 10),
                Agua(15, 55, "1", "Normal", 7),
                Agua(16, 50, "1", "Pouca", 21),
                Agua(17, 70, "3", "Normal", 5),
                Agua(18, 60, "3", "Normal", 5),
                Agua(19, 70, "3", "Normal", 7),
                Agua(20, 55, "1", "Normal", 7),
                Agua(21, 75, "3", "Pouca", 5),
                Agua(22, 70, "2", "Muita", 7),
                Agua(23, 50, "1", "Normal", 10),
                Agua(24, 70, "1", "Normal", 10),
                Agua(25, 65, "1", "Normal", 10),
                Agua(26, 65, "1", "Normal", 10),
                Agua(27, 50, "2", "Muita", 10),
                Agua(28, 65, "2", "Normal", 7),
                Agua(29, 45, "1", "Muita", 15),
                Agua(30, 55, "2", "Normal", 7),
                Agua(31, 45, "1", "Muita", 15),
                Agua(32, 35, "1", "Muita", 7),
                Agua(33, 65, "2", "Muita", 10),
                Agua(34, 55, "2", "Normal", 7),
                Agua(35, 65, "2", "Muita", 10)
            )
            agua.forEach { db.aguaDao().insert(it) }
        }
    }
}