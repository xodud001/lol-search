package net.weather.lolsearch.riot.dto

data class SummonerDto(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Long
)
