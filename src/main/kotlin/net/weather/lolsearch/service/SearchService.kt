package net.weather.lolsearch.service

import net.weather.lolsearch.riot.api.RiotApi
import net.weather.lolsearch.riot.dto.MatchDto
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class SearchService(
    val api: RiotApi
) {

    fun findMatches(nickname: String): SearchDto {
        val (_, _, puuid) = api.getSummonerByNickname(nickname);

        val matchIds = api.getMatchIds(puuid);

        val matches = matchIds.map { matchId ->
            Thread.sleep(40) // 20 request every 1 seconds 제약
            CompletableFuture.supplyAsync { api.getMatch(matchId)
            }}
            .map { future -> future.get() }

        return SearchDto(matches, puuid);
    }
}