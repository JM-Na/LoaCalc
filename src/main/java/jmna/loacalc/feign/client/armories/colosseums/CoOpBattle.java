package jmna.loacalc.feign.client.armories.colosseums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CoOpBattle {
    @JsonProperty("FirstWinCount")
    private Integer firstWinCount;
    @JsonProperty("SecondWinCount")
    private Integer secondWinCount;
    @JsonProperty("ThirdWinCount")
    private Integer thirdWinCount;
    @JsonProperty("FirstPlayCount")
    private Integer firstPlayCount;
    @JsonProperty("SecondPlayCount")
    private Integer secondPlayCount;
    @JsonProperty("ThirdPlayCount")
    private Integer thirdPlayCount;
    @JsonProperty("AllKillCount")
    private Integer allKillCount;
    @JsonProperty("PlayCount")
    private Integer playCount;
    @JsonProperty("VictoryCount")
    private Integer victoryCount;
    @JsonProperty("LoseCount")
    private Integer loseCount;
    @JsonProperty("TieCount")
    private Integer tieCount;
    @JsonProperty("KillCount")
    private Integer killCount;
    @JsonProperty("AceCount")
    private Integer aceCount;
    @JsonProperty("DeathCount")
    private Integer deathCount;
}
