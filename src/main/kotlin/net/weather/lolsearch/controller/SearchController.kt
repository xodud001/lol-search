package net.weather.lolsearch.controller

import net.weather.lolsearch.controller.response.MatchResponse
import net.weather.lolsearch.controller.response.SearchResponse
import net.weather.lolsearch.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    val searchService: SearchService
) {

    @GetMapping("/api/summoner/{nickname}")
    fun search(@PathVariable("nickname") nickname: String): SearchResponse {
        val search = searchService.findMatches(nickname);

        val matches = search.matches.map { match -> MatchResponse.Mapper.from(match, search.puuid) }

        return SearchResponse(matches);
    }

}