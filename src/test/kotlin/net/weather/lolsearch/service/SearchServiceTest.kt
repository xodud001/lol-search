package net.weather.lolsearch.service

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import net.weather.lolsearch.riot.api.RiotApi
import net.weather.lolsearch.riot.dto.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

class SearchServiceTest{

    private val riotApi: RiotApi = mockk()
    private val searchService: SearchService = SearchService(riotApi)

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

    @Test
    fun `소환사 매치 정보 조회`(){
        val nickname = "TESTER"
        val puuid = "TEST_PUUID"
        val matchId = "KR_123456789"

        val metadata = MetadataDto("1.0.0", "KR_123456789", listOf("P1"))
        val info = infoDto(puuid)

        every { riotApi.getSummonerByNickname(nickname) } returns summoner(puuid, nickname)
        every { riotApi.getMatchIds(puuid) } returns listOf(matchId)
        every { riotApi.getMatch(matchId) } returns MatchDto(metadata, info)

        val (matches) = searchService.findMatches(nickname)

        assertThat(matches.size).isEqualTo(1);

        val match = matches[0]
        assertThat(match.metadata.matchId).isEqualTo(matchId)
        assertThat(match.info.participants.size).isEqualTo(1)

        val participant = match.info.participants[0]
        assertThat(participant.puuid).isEqualTo(puuid)

    }

}