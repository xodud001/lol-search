package net.weather.lolsearch.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

enum class ExceptionCode(val status: HttpStatus, val message: String) {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청이 잘못되었습니다."),

    MATCH_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 매치를 찾을 수 없습니다."),
    SUMMONER_NOT_FOUND(HttpStatus.NOT_FOUND, "요청한 소환사를 찾을 수 없습니다."),
    RIOT_TOO_MANY_REQUEST(HttpStatus.TOO_MANY_REQUESTS, "요청이 너무 많습니다."),
    RIOT_CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "라이엇 API 요청에 실패했습니다."),
    RIOT_SERVER_ERROR(HttpStatus.SERVICE_UNAVAILABLE, "라이엇 서버에 문제가 발생했습니다.");

}