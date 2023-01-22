package net.weather.lolsearch.controller.response

import net.weather.lolsearch.riot.dto.ParticipantDto
import net.weather.lolsearch.riot.dto.TeamDto

data class TeamResponse(
    val teamId: Int,
    val champion: Int,
    val baron: Int,
    val dragon: Int,
    val tower: Int,
    val win: Boolean
){
    object Model {
        fun from(team: TeamDto): TeamResponse {
            val (baron, champion, dragon, _, _, tower) = team.objectives;
            return TeamResponse(
                team.teamId,
                champion.kills,
                baron.kills,
                dragon.kills,
                tower.kills,
                team.win
            )
        }
    }
}
