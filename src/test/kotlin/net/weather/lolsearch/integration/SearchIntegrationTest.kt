package net.weather.lolsearch.integration

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import net.weather.lolsearch.controller.exception.ExceptionCode
import net.weather.lolsearch.controller.exception.ExceptionResponse
import net.weather.lolsearch.controller.response.SearchResponse
import net.weather.lolsearch.riot.api.RiotApi
import net.weather.lolsearch.riot.dto.*
import net.weather.lolsearch.riot.exception.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchIntegrationTest(@Autowired val restTemplate: TestRestTemplate) {

    @MockkBean
    lateinit var riotApi: RiotApi

    private fun summoner(puuid: String, nickname: String) =
        SummonerDto("", "", puuid, nickname, 100, 100, 100)

    private fun infoDto(puuid: String) =
        InfoDto(100,
            1,
            999,
            1,
            1000,
            "CLASSIC",
            "RANK 5vs5",
            "MATCHED_GAME",
            "1.0.0",
            100,
            200,
            "ASIA_KR",
            "T_CODE",
            participants(puuid),
            teams())

    private fun participants(puuid: String): List<ParticipantDto> {
        val participant = ParticipantDto(1,
            200,
            puuid,
            "R_NAME",
            "R_TAG",
            "USER",
            "SUMMONER_ID",
            200,
            "TESTER",
            100,
            true,
            999,
            100,
            "Vayne",
            16,
            1000,
            0,
            5,
            3,
            10,
            1,
            "AD",
            "AD",
            "AD",
            2,
            1,
            0,
            0,
            0,
            0,
            10000,
            9000,
            1000,
            2000,
            3000,
            4000,
            5000,
            6000,
            7000,
            100,
            200,
            50,
            30,
            10,
            7,
            4,
            5,
            7,
            19,
            30,
            10000,
            10000,
            10000,
            10000,
            10000,
            10000,
            10000,
            10000,
            10000,
            10000,
            100,
            100,
            100,
            1000,
            100,
            100,
            10000,
            10000,
            10000,
            10000,
            1000,
            1000,
            1000,
            100,
            5,
            2,
            521,
            4,
            4,
            4,
            20,
            10,
            10,
            firstBloodAssist = true,
            firstBloodKill = false,
            firstTowerAssist = true,
            firstTowerKill = false,
            baronKills = 0,
            dragonKills = 1,
            nexusKills = 0,
            nexusTakedowns = 0,
            nexusLost = 0,
            inhibitorKills = 2,
            inhibitorTakedowns = 1,
            inhibitorsLost = 1,
            turretKills = 1,
            turretTakedowns = 1,
            turretsLost = 1,
            neutralMinionsKilled = 100,
            objectivesStolen = 1,
            objectivesStolenAssists = 1,
            gameEndedInEarlySurrender = false,
            gameEndedInSurrender = false,
            teamEarlySurrendered = false,
            perks = perksDto()
        )
        return listOf(participant);
    }

    private fun perksDto() = PerksDto(PerkStatsDto(0, 0, 0), emptyList());

    private fun teams(): List<TeamDto> {
        val team = TeamDto(
            emptyList(), ObjectivesDto(
                ObjectiveDto(true, 10),
                ObjectiveDto(true, 20),
                ObjectiveDto(true, 10),
                ObjectiveDto(true, 10),
                ObjectiveDto(true, 10),
                ObjectiveDto(true, 10)
            ),
            100,
            true
        )

        return listOf(team)
    }

    @Nested
    @DisplayName("find matches by the summoner nickname")
    inner class SearchMatchByNickname{
        @Test
        fun `success`(){
            val nickname = "TESTER"
            val puuid = "TEST_PUUID"
            val matchId = "KR_123456789"

            val metadata = MetadataDto("1.0.0", "KR_123456789", listOf("P1"))
            val info = infoDto(puuid)

            every { riotApi.getSummonerByNickname(nickname) } returns summoner(puuid, nickname)
            every { riotApi.getMatchIds(puuid) } returns listOf(matchId)
            every { riotApi.getMatch(matchId) } returns MatchDto(metadata, info)

            val response = restTemplate.getForEntity("/api/summoner/$nickname", SearchResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.OK);

            val searchResponse = response.body
            assertThat(searchResponse?.stats?.matchCount).isEqualTo(1)
            assertThat(searchResponse?.matches?.size).isEqualTo(1)
        }

        @Test
        fun `fail by SummonerNotFound`(){
            val nickname = "TESTER"
            every { riotApi.getSummonerByNickname(nickname) } throws SummonerNotFoundException("소환사 찾기 실패")

            val response = restTemplate.getForEntity("/api/summoner/$nickname", ExceptionResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND);

            val exception = response.body
            assertThat(exception?.code).isEqualTo(ExceptionCode.SUMMONER_NOT_FOUND)
        }

        @Test
        fun `fail by MatchNotFound`(){
            val nickname = "TESTER"
            val puuid = "TEST_PUUID"

            every { riotApi.getSummonerByNickname(nickname) } returns summoner(puuid, nickname)
            every { riotApi.getMatchIds(puuid) } throws MatchNotFoundException("매치 조회 실패")

            val response = restTemplate.getForEntity("/api/summoner/$nickname", ExceptionResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND);

            val exception = response.body
            assertThat(exception?.code).isEqualTo(ExceptionCode.MATCH_NOT_FOUND)
        }

        @Test
        fun `fail by RateLimit`(){
            val nickname = "TESTER"
            every { riotApi.getSummonerByNickname(nickname) } throws RateLimitException("요청이 너무 많습니다.")

            val response = restTemplate.getForEntity("/api/summoner/$nickname", ExceptionResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);

            val exception = response.body
            assertThat(exception?.code).isEqualTo(ExceptionCode.RIOT_TOO_MANY_REQUEST)
        }

        @Test
        fun `fail by RiotClientError`(){
            val nickname = "TESTER"
            every { riotApi.getSummonerByNickname(nickname) } throws RiotClientException("RIOT API 요청 실패")

            val response = restTemplate.getForEntity("/api/summoner/$nickname", ExceptionResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

            val exception = response.body
            assertThat(exception?.code).isEqualTo(ExceptionCode.RIOT_CLIENT_ERROR)
        }

        @Test
        fun `fail by RiotServerError`(){
            val nickname = "TESTER"
            every { riotApi.getSummonerByNickname(nickname) } throws RiotServerException("RIOT 서버 에러")

            val response = restTemplate.getForEntity("/api/summoner/$nickname", ExceptionResponse::class.java)
            assertThat(response.statusCode).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);

            val exception = response.body
            assertThat(exception?.code).isEqualTo(ExceptionCode.RIOT_SERVER_ERROR)
        }
    }

}