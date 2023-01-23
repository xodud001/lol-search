package net.weather.lolsearch.riot.api

import net.weather.lolsearch.riot.dto.MatchDto

interface Match: Api{

    fun getMatchIds(puuid: String): List<String>;

    fun getMatch(matchId: String): MatchDto
}