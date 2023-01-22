package net.weather.lolsearch.controller.response

data class SearchResponse(
    val stats: StatsResponse,
    val matches: List<MatchResponse>
)
