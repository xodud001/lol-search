package net.weather.lolsearch.riot.api

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import net.weather.lolsearch.riot.dto.*
import net.weather.lolsearch.riot.repository.MemoryMapRepository
import org.junit.jupiter.api.Test

class CachedProxyRiotAPiTest{

    private val riotApi: RiotApi = mockk()

    private val proxyRiotApi: RiotApi = CachedProxyRiotAPi(
        riotApi,
        MemoryMapRepository(),
        MemoryMapRepository())

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
    fun `call getMatch of target only once`(){
        val matchId = "MATCH_ID"
        val puuid = "PUUID"
        val metadata = MetadataDto("1.0.0", "KR_123456789", listOf("P1"))

        every { riotApi.getMatch(matchId) } returns MatchDto(metadata, infoDto(puuid))

        proxyRiotApi.getMatch(matchId)
        proxyRiotApi.getMatch(matchId)
        proxyRiotApi.getMatch(matchId)

        verify(exactly = 1) { riotApi.getMatch(matchId) }
    }
    @Test
    fun `call getSummonerByNickname of target only once`(){
        val nickname = "NICKNAME"
        val puuid = "PUUID"

        every { riotApi.getSummonerByNickname(nickname) } returns summoner(puuid, nickname)

        proxyRiotApi.getSummonerByNickname(nickname)
        proxyRiotApi.getSummonerByNickname(nickname)
        proxyRiotApi.getSummonerByNickname(nickname)

        verify(exactly = 1) { riotApi.getSummonerByNickname(nickname) }
    }
}