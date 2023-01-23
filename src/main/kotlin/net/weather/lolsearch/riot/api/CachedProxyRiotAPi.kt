package net.weather.lolsearch.riot.api

import net.weather.lolsearch.riot.dto.MatchDto
import net.weather.lolsearch.riot.dto.SummonerDto
import net.weather.lolsearch.riot.repository.Repository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class CachedProxyRiotAPi(
    @Qualifier("riotApi") val riotApi: RiotApi,
    @Qualifier("summonerRepository") val summonerRepository: Repository<SummonerDto, String>,
    @Qualifier("matchRepository") val matchRepository: Repository<MatchDto, String>,

): RiotApi {
    override fun getMatchIds(puuid: String): List<String> {
        return riotApi.getMatchIds(puuid)
    }

    override fun getMatch(matchId: String): MatchDto {
        val cachedMatch = matchRepository.findById(matchId);

        return if( cachedMatch == null ){
            val match = riotApi.getMatch(matchId)
            matchRepository.save(matchId, match)
            match
        }else {
            cachedMatch
        }
    }

    override fun getSummonerByNickname(nickname: String): SummonerDto {
        val cachedSummoner = summonerRepository.findById(nickname);

        return if( cachedSummoner == null ){
            val summoner = riotApi.getSummonerByNickname(nickname);
            summonerRepository.save(nickname, summoner);
            summoner
        }else{
            cachedSummoner
        }
    }
}