package net.weather.lolsearch.controller.response

import net.weather.lolsearch.riot.dto.MatchDto
import net.weather.lolsearch.riot.type.GameMode
import net.weather.lolsearch.riot.type.GameType

data class MatchResponse(
    val gameCreatedAt: Long,         // 게임 생성 시간
    val gameStaredAt: Long,          // 게임 시작 시간
    val gameEndedAt: Long,           // 게임 종료 시간
    val gameDuration: Long,          // 게임 진행 시간
    val gameMode: GameMode,          // 게임 모드
    val gameType: GameType,          // 게임 타입
    val gameName: String,            // 게임 이름
    val summoner: SummonerResponse,  // 소환사
    val team: TeamResponse
){
    object Mapper {
        fun from(match: MatchDto, puuid: String): MatchResponse{
            val info = match.info;
            val targetParticipant = info.participants.first { it -> it.puuid == puuid };
            val targetTeam = info.teams.first { it.teamId == targetParticipant.teamId }

            return MatchResponse(
                info.gameCreation,
                info.gameStartTimestamp,
                info.gameEndTimestamp,
                info.gameDuration,
                GameMode.valueOf(info.gameMode),
                GameType.valueOf(info.gameType),
                info.gameName,
                SummonerResponse.Model.from(targetParticipant, targetTeam),
                TeamResponse.Model.from(targetTeam)
            )
        }
    }
}

