package net.weather.lolsearch.service

import net.weather.lolsearch.riot.dto.MatchDto

data class SearchDto(val matches: List<MatchDto>, val puuid: String)
