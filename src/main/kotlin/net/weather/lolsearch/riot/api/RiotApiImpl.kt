package net.weather.lolsearch.riot.api

import net.weather.lolsearch.riot.dto.MatchDto
import net.weather.lolsearch.riot.exception.SummonerNotFoundException
import net.weather.lolsearch.riot.dto.SummonerDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriBuilder
import java.net.URI

@Component
class RiotApiImpl: RiotApi {

    private val apiKey: String = "RGAPI-020559eb-87cf-4220-aa77-763dc0981419";
    private val client: WebClient = WebClient.builder().build();

    override fun getMatchIds(puuid: String): List<String> {
        val response = client.get()
            .uri(URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/$puuid/ids?start=0&count=20"))
            .header("X-Riot-Token", apiKey)
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<List<String>>(){})
            .block()

        return response ?: throw IllegalStateException("API 응답이 전달되지 않았습니다.")
    }

    override fun getMatch(matchId: String): MatchDto {
        val response = client.get()
            .uri(URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/$matchId"))
            .header("X-Riot-Token", apiKey)
            .retrieve()
            .bodyToMono(MatchDto::class.java)
            .block()

        return response ?: throw IllegalStateException("API 응답이 전달되지 않았습니다.")
    }

    override fun getSummonerByNickname(nickname: String): SummonerDto {
        val path = "/lol/summoner/v4/summoners/by-name/$nickname"
        return getSummoner(path)
    }

    private fun getSummoner(path: String): SummonerDto {
        val response = client.get()
            .uri(URI.create("https://kr.api.riotgames.com$path"))
            .header("X-Riot-Token", apiKey)
            .retrieve()
            .onStatus({ other: HttpStatusCode? -> HttpStatus.NOT_FOUND == other })
            { throw SummonerNotFoundException("소환사가 존재하지 않습니다. $path") }
            .onStatus({ obj: HttpStatusCode -> obj.is4xxClientError })
            { throw IllegalArgumentException("요청이 잘못되었습니다.") }
            .onStatus({ obj: HttpStatusCode -> obj.is5xxServerError })
            { throw IllegalStateException("Riot 서버에 문제가 발생했습니다.") }
            .bodyToMono(SummonerDto::class.java)
            .block()

        return response ?: throw IllegalStateException("API 응답이 전달되지 않았습니다.")
    }
}