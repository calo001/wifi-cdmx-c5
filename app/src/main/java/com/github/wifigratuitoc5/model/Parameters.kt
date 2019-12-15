package com.github.wifigratuitoc5.model

data class Parameters(
    val dataset: String,
    val facet: List<String>,
    val format: String,
    val rows: Int,
    val timezone: String
)