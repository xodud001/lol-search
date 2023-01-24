# 리그 오브 레전드 전적 검색 프로젝트
리그 오브 레전드 전적을 조회하는 서비스입니다.

## 프로젝트 설명

### API 호출 흐름
![image](https://user-images.githubusercontent.com/51165061/214239069-285149bf-b54a-4801-9b73-b3990873718c.png)

* `SearchController`: Service 계층에서 받은 데이터를 API 스펙에 맞게 매핑해서 변환
* `SearchAdvice`: 발생하는 예외를 잡아서 처리
* `SearchService`: Riot API를 이용해서 전적 검색 정보 생성
* `RiotApi`: Riot API 인터페이스
* `CachedProxyRiotApi`: Riot API의 Rate Limit을 고려해서 한번 조회된 데이터를 캐싱하는 모듈
* `RiotApiImpl`: 실제 Riot 서버에서 데이터를 받아오는 역할 수행

### 사용한 기술 스택
```
Language: Kotlin
Runtime: Java 17
Framework: Spring Framework
Library: Spring Boot, Spring Web, Spring Webflux
Test: Spring Test, Junit5, Mockk
IDEA: IntelliJ
```

## 기능
### 소환사 전적 검색
소환사명으로 최근 20경기의 전적을 조회

#### 요청
```
GET /api/summoner/{소환사명}
```
#### 응답
```
{
    "stats": {
        "matchCount": 20,
        "kills": 9.0,
        "deaths": 7.2,
        "assists": 13.8,
        "wins": 12,
        "loses": 8
    },
    "matches": [
        {
            "gameCreatedAt": 1674390623764,
            "gameStaredAt": 1674390653709,
            "gameEndedAt": 1674391462402,
            "gameDuration": 808,
            "gameMode": "ARAM",
            "gameType": "MATCHED_GAME",
            "gameName": "teambuilder-match-6322295522",
            "summoner": {
                "puuid": "DgPTUFq6Cqhac4rCcc6S_XNkZvLOSZYT0MhmN4yS1kU287Wmz_OzNwtP4N940AkQuzxk9BdFjk8FzQ",
                "champLevel": 15,
                "championName": "Riven",
                "profileIcon": 5415,
                "kills": 11,
                "deaths": 5,
                "assists": 21,
                "killInvolvement": 76,
                "largestMultiKill": 4,
                "summoner1Id": 32,
                "summoner2Id": 4,
                "teamId": 200,
                "teamPosition": "",
                "goldEarned": 11567,
                "goldSpent": 11200,
                "item0": 6630,
                "item1": 3111,
                "item2": 6333,
                "item3": 3071,
                "item4": 1028,
                "item5": 0,
                "item6": 2052,
                "lane": "NONE",
                "timePlayed": 808,
                "totalDamageDealtToChampions": 18552,
                "totalDamageTaken": 17885,
                "detectorWardsPlaced": 0,
                "wardsPlaced": 0,
                "wardsKilled": 0,
                "win": true
            },
            "team": {
                "teamId": 200,
                "champion": 42,
                "baron": 0,
                "dragon": 0,
                "tower": 4,
                "win": true
            }
        },
        ...
    ]  
}
```

## 실행 방법

### 준비사항
* Java 17
* [Riot Developer](https://developer.riotgames.com)에서 API KEY 발급

### 프로젝트 다운

#### Git으로 받기
```git
$ git clone https://github.com/xodud001/lol-search.git 
```
#### 소스파일 받기
* [Github Repo](https://github.com/xodud001/lol-search)에서 `code` 클릭 후 `Download Zip`으로 파일 다운

### Riot API Key 설정
* `/resources/applicaiton.properties`에 `props.riot-api-key` 속성에 발급 받은 key 설정

### 실행
```
$ cd lol-search
$ ./gradlew bootRun
```
