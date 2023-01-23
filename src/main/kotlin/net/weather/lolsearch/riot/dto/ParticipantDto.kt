package net.weather.lolsearch.riot.dto

data class ParticipantDto(

    val participantId: Int,
    val profileIcon: Int,
    val puuid: String,
    val riotIdName: String,
    val riotIdTagline: String,
    val role: String,
    val summonerId: String,
    val summonerLevel: Int,
    val summonerName: String,

    val teamId: Int,
    val win: Boolean,

    val timePlayed: Int,

    val championId: Int,
    val championName: String,
    val champLevel: Int,
    val champExperience: Int,
    val championTransform: Int,

    val kills: Int,
    val deaths: Int,
    val assists: Int,
    val bountyLevel: Int,

    val lane: String,
    val individualPosition: String,
    val teamPosition: String,

    val killingSprees: Int,
    val doubleKills: Int,
    val tripleKills: Int,
    val quadraKills: Int,
    val pentaKills: Int,
    val unrealKills: Int,

    val goldEarned: Int,
    val goldSpent: Int,

    val item0: Int,
    val item1: Int,
    val item2: Int,
    val item3: Int,
    val item4: Int,
    val item5: Int,
    val item6: Int,

    val summoner1Id: Int,
    val summoner2Id: Int,

    val spell1Casts: Int,
    val spell2Casts: Int,
    val spell3Casts: Int,
    val spell4Casts: Int,
    val summoner1Casts: Int,
    val summoner2Casts: Int,

    val consumablesPurchased: Int,
    val itemsPurchased: Int,

    val timeCCingOthers: Int,

    val damageDealtToBuildings: Int,
    val damageDealtToObjectives: Int,
    val damageDealtToTurrets: Int,
    val damageSelfMitigated: Int,

    val totalDamageDealt: Int,
    val totalDamageDealtToChampions: Int,
    val totalDamageShieldedOnTeammates: Int,
    val totalDamageTaken: Int,
    val totalHeal: Int,
    val totalHealsOnTeammates: Int,
    val totalMinionsKilled: Int,
    val totalTimeCCDealt: Int,
    val totalTimeSpentDead: Int,
    val totalUnitsHealed: Int,

    val magicDamageDealt: Int,
    val magicDamageDealtToChampions: Int,
    val magicDamageTaken: Int,

    val physicalDamageDealt: Int,
    val physicalDamageDealtToChampions: Int,
    val physicalDamageTaken: Int,

    val trueDamageDealt: Int,
    val trueDamageDealtToChampions: Int,
    val trueDamageTaken: Int,


    val largestCriticalStrike: Int,
    val largestKillingSpree: Int,
    val largestMultiKill: Int,

    val longestTimeSpentLiving: Int,

    val detectorWardsPlaced: Int,
    val sightWardsBoughtInGame: Int,
    val visionWardsBoughtInGame: Int,
    val visionScore: Int,
    val wardsKilled: Int,
    val wardsPlaced: Int,

    val firstBloodAssist: Boolean,
    val firstBloodKill: Boolean,
    val firstTowerAssist: Boolean,
    val firstTowerKill: Boolean,

    val baronKills: Int,
    val dragonKills: Int,

    val nexusKills: Int,
    val nexusTakedowns: Int,
    val nexusLost: Int,

    val inhibitorKills: Int,
    val inhibitorTakedowns: Int,
    val inhibitorsLost: Int,

    val turretKills: Int,
    val turretTakedowns: Int,
    val turretsLost: Int,

    val neutralMinionsKilled: Int,

    val objectivesStolen: Int,
    val objectivesStolenAssists: Int,

    val gameEndedInEarlySurrender: Boolean,
    val gameEndedInSurrender: Boolean,
    val teamEarlySurrendered: Boolean,


    val perks: PerksDto,
)
