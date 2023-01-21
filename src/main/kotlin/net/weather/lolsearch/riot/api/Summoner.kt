package net.weather.lolsearch.riot.api

import net.weather.lolsearch.riot.dto.SummonerDto

interface Summoner: Api {

    fun findByNickname(nickname: String): SummonerDto;
}