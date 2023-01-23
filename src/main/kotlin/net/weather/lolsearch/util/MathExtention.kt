package net.weather.lolsearch.util

import kotlin.math.pow
import kotlin.math.roundToInt

class MathExtension {

    companion object {
        fun round(value: Double, position: Int): Double{
            if(position <= 0){
                return (value.roundToInt()).toDouble()
            }

            val weighted = 10.0.pow(position.toDouble());

            return kotlin.math.round(value * weighted) / weighted
        }
    }
}