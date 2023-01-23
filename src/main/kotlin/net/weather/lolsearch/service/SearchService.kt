package net.weather.lolsearch.service

import net.weather.lolsearch.riot.api.RiotApi
import net.weather.lolsearch.riot.dto.MatchDto
import org.springframework.stereotype.Service

@Service
class SearchService(
    val api: RiotApi
) {
    fun findMatches(nickname: String): SearchDto {
        val (_, _, puuid) = api.getSummonerByNickname(nickname);

        val matchIds = api.getMatchIds(puuid);

        val matches = matchIds.map { matchId -> api.getMatch(matchId) };

        return SearchDto(matches, puuid);
    }
}