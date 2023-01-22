package net.weather.lolsearch.controller.response

import net.weather.lolsearch.util.MathExtension
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.roundToInt

data class StatsResponse(
    val matchCount: Int,
    val kills: Double,
    val deaths: Double,
    val assists: Double,
    val wins: Int,
    val loses: Int
) {

    object Mapper {
        fun from(matches: List<MatchResponse>): StatsResponse {
            val wins = matches.count() { it.summoner.win }
            
            return StatsResponse(
                matches.size,
                MathExtension.round((matches.sumOf { it.summoner.kills } / matches.size.toDouble()), 1),
                MathExtension.round((matches.sumOf { it.summoner.deaths } / matches.size.toDouble()), 1),
                MathExtension.round((matches.sumOf { it.summoner.assists } / matches.size.toDouble()), 1),
                wins,
                matches.size - wins,
            )
        }
    }
}

