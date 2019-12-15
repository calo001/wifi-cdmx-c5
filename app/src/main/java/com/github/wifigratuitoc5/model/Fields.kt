package com.github.wifigratuitoc5.model

data class Fields(
    val alcaldia: String,
    val altavoz: String,
    val boton: String,
    val colonia: String,
    val direccion: String,
    val esquina: String,
    val estatus_clausura: String,
    val estatus_conectividad: String,
    val geopoint: List<Double>,
    val latitud: Double,
    val longitud: Double,
    val tipo_de_poste: String
)