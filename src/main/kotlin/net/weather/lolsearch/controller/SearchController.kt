package net.weather.lolsearch.controller

import net.weather.lolsearch.controller.exception.ValidationException
import net.weather.lolsearch.controller.response.MatchResponse
import net.weather.lolsearch.controller.response.SearchResponse
import net.weather.lolsearch.controller.response.StatsResponse
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
        lengthCheck(nickname, 3, 16);

        val search = searchService.findMatches(nickname);

        val matches = search.matches.map { match -> MatchResponse.Mapper.from(match, search.puuid) }

        return SearchResponse(
            StatsResponse.Mapper.from(matches),
            matches
        );
    }

    private fun lengthCheck(nickname: String, min: Int, max: Int) {
        if( nickname.length !in min..max){
            throw ValidationException("nickname의 길이는 $min 이상, $max 이하로 입력해주세요.")
        }
    }

}