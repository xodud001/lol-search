package net.weather.lolsearch.controller.response

import kotlin.math.round
import kotlin.math.roundToInt

data class StatsResponse(
    val matchCount: Int,
    val kills: Float,
    val deaths: Float,
    val assists: Float,
    val wins: Int,
    val loses: Int
) {

    object Mapper {
        fun from(matches: List<MatchResponse>): StatsResponse {
            val wins = matches.count() { it.summoner.win }

            return StatsResponse(
                matches.size,
                round((matches.sumOf { it.summoner.kills } / matches.size.toFloat()), 1),
                round((matches.sumOf { it.summoner.deaths } / matches.size.toFloat()), 1),
                round((matches.sumOf { it.summoner.assists } / matches.size.toFloat()), 1),
                wins,
                matches.size - wins,
            )
        }

        private fun round(value: Float, position: Int): Float{
            if(position <= 0){
                return (value.roundToInt()).toFloat()
            }

            var weighted = 1;
            for(i in 1..position){
                weighted *= 10;
            }

            return round(value*weighted) / weighted
        }
    }
}

