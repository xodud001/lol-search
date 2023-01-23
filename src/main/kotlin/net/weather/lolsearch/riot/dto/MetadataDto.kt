package net.weather.lolsearch.riot.dto

data class MetadataDto(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)
