package net.weather.lolsearch.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import net.weather.lolsearch.riot.dto.*
import net.weather.lolsearch.service.SearchDto
import net.weather.lolsearch.service.SearchService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest
class SearchControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var searchService: SearchService;

    private fun searchDto(puuid: String): SearchDto {
        val metadata = MetadataDto("1.0.0", "KR_123456789", listOf("P1"))
        val info = infoDto(puuid)
        return SearchDto(listOf(MatchDto(metadata, info)), puuid)
    }

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
    fun `matches by nickname`() {
        val nickname = "test-summoner"
        every { searchService.findMatches(nickname) } returns searchDto("TEST_PUUID")

        mockMvc.perform(get("/api/summoner/$nickname").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("\$.stats.matchCount").value(1))
            .andExpect(jsonPath("\$.matches[0].summoner.puuid").value("TEST_PUUID"))

    }
    @Test
    fun `validate nickname`() {
        val shortNickname = "a"
        val longNickname = "abcdefg0123456789"

        mockMvc.perform(get("/api/summoner/$shortNickname").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest)

        mockMvc.perform(get("/api/summoner/$longNickname").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest)
    }


}
