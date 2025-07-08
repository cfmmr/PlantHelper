package com.example.planthelper

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _planta = MutableLiveData<Planta>()
    val planta: LiveData<Planta>
        get() = _planta

    private val _luminosidade = MutableLiveData<Luminosidade>()
    val luminosidade: LiveData<Luminosidade>
        get() = _luminosidade

    private val _agua = MutableLiveData<Agua>()
    val agua: LiveData<Agua>
        get() = _agua
}