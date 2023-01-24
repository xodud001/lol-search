package net.weather.lolsearch.controller.exception

data class ExceptionResponse(
    val code: ExceptionCode,
    val message: String,
    val description: String
)
