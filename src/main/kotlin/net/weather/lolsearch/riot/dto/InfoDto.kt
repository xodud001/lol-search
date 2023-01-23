package net.weather.lolsearch.riot.dto

data class InfoDto(
    val gameId: Long,
    val gameCreation: Long,
    val gameDuration: Long,
    val gameStartTimestamp: Long,
    val gameEndTimestamp: Long,
    val gameMode: String,
    val gameName: String,
    val gameType: String,
    val gameVersion: String,

    val mapId: Int,
    val queueId: Int,
    val platformId: String,
    val tournamentCode: String,

    val participants: List<ParticipantDto>,
    val teams: List<TeamDto>
)
