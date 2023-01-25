package net.weather.lolsearch.controller

import net.weather.lolsearch.controller.exception.ExceptionCode
import net.weather.lolsearch.controller.exception.ExceptionResponse
import net.weather.lolsearch.controller.exception.ValidationException
import net.weather.lolsearch.riot.exception.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class SearchAdvice {

    @ExceptionHandler(MatchNotFoundException::class)
    fun matchNotFound(e : MatchNotFoundException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.MATCH_NOT_FOUND, e)
    }

    @ExceptionHandler(SummonerNotFoundException::class)
    fun matchNotFound(e : SummonerNotFoundException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.SUMMONER_NOT_FOUND, e)
    }

    @ExceptionHandler(RateLimitException::class)
    fun matchNotFound(e : RateLimitException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.RIOT_TOO_MANY_REQUEST, e)
    }

    @ExceptionHandler(RiotClientException::class)
    fun matchNotFound(e : RiotClientException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.RIOT_CLIENT_ERROR, e)
    }

    @ExceptionHandler(RiotServerException::class)
    fun matchNotFound(e : RiotServerException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.RIOT_SERVER_ERROR, e)
    }

    @ExceptionHandler(ValidationException::class)
    fun matchNotFound(e : ValidationException): ResponseEntity<ExceptionResponse> {
        return makeResponse(ExceptionCode.BAD_REQUEST, e)
    }

    private fun makeResponse(
        code: ExceptionCode,
        e: RuntimeException
    ) = ResponseEntity.status(code.status.value()).body(
        ExceptionResponse(
            code,
            code.message,
            e.message ?: ""
        )
    )

}