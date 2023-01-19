package net.weather.lolsearch

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    @GetMapping("/api/summoner/{nickname}")
    fun search(@PathVariable("nickname") nickname: String): String {
        return "Hello, ${nickname}!"
    }
}