package com.github.wifigratuitoc5.model

data class WifiAccessPoint(
    val facet_groups: List<FacetGroup>,
    val nhits: Int,
    val parameters: Parameters,
    val records: List<Record>
)