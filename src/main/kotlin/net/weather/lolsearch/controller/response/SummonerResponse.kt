package net.weather.lolsearch.controller.response

import net.weather.lolsearch.riot.dto.ParticipantDto
import net.weather.lolsearch.riot.dto.TeamDto
import kotlin.math.roundToInt

data class SummonerResponse(

    val puuid: String,

    val champLevel: Int,
    val championName: String,
    val profileIcon: Int,

    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val killInvolvement: Int,

    val largestMultiKill: Int,

    val summoner1Id: Int,
    val summoner2Id: Int,

    val teamId: Int,
    val teamPosition: String, // 라인

    val goldEarned: Int,
    val goldSpent: Int,

    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,

    val lane: String, // 라인

    val timePlayed: Int,

    val totalDamageDealtToChampions: Int,
    val totalDamageTaken: Int,

    val detectorWardsPlaced: Int,
    val wardsPlaced: Int,
    val wardsKilled: Int,

    val win: Boolean,
){
    object Model{
        fun from(participant: ParticipantDto, team: TeamDto): SummonerResponse{

            val killInvolvement = (((participant.kills + participant.assists) / team.objectives.champion.kills.toDouble()) * 100).roundToInt();

            return SummonerResponse(
                participant.puuid,
                participant.champLevel,
                participant.championName,
                participant.profileIcon,
                participant.kills,
                participant.deaths,
                participant.assists,
                killInvolvement,
                participant.largestMultiKill,
                participant.summoner1Id,
                participant.summoner2Id,
                participant.teamId,
                participant.teamPosition,
                participant.goldEarned,
                participant.goldSpent,
                participant.item0,
                participant.item1,
                participant.item2,
                participant.item3,
                participant.item4,
                participant.item5,
                participant.item6,
                participant.lane,
                participant.timePlayed,
                participant.totalDamageDealtToChampions,
                participant.totalDamageTaken,
                participant.detectorWardsPlaced,
                participant.wardsKilled,
                participant.wardsPlaced,
                participant.win
            )
        }
    }
}
