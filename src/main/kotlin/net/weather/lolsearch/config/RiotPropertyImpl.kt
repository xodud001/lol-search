package net.weather.lolsearch.config

import net.weather.lolsearch.riot.RiotProperty
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "props")
data class RiotPropertyImpl(
    var riotApiKey: String = ""
): RiotProperty {

    override fun getApiKey(): String {
        return riotApiKey
    }
}