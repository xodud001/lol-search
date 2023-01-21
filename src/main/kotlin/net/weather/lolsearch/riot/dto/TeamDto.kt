package net.weather.lolsearch.riot.dto

data class TeamDto(
    val bans: List<BanDto>,
    val objectives: ObjectivesDto,
    val teamId: Int,
    val win: Boolean,

)
