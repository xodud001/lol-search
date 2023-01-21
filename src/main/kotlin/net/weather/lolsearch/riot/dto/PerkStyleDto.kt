package net.weather.lolsearch.riot.dto

data class PerkStyleDto(
    val description: String,
    val selections: List<PerkStyleSelectionDto>,
    val style: Int
)
