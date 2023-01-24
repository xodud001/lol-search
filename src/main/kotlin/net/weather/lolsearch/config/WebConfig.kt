package net.weather.lolsearch.config

import net.weather.lolsearch.riot.RiotProperty
import net.weather.lolsearch.riot.dto.MatchDto
import net.weather.lolsearch.riot.dto.SummonerDto
import net.weather.lolsearch.riot.repository.MemoryMapRepository
import net.weather.lolsearch.riot.repository.Repository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RiotPropertyImpl::class)
class WebConfig {

    @Bean
    @Qualifier("summonerRepository")
    fun summonerRepository(): Repository<SummonerDto, String> = MemoryMapRepository();

    @Bean
    @Qualifier("matchRepository")
    fun matchRepository(): Repository<MatchDto, String> = MemoryMapRepository();

}