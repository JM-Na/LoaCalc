package jmna.loacalc.feign.client.armories.colosseums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Competitive {
    @JsonProperty("Rank")
    private String rank;

    @JsonProperty("RankName")
    private String rankName;
    @JsonProperty("RankIcon")
    private Integer rankIcon;

    @JsonProperty("RankLastMmr")
    private Integer rankLastMmr;

    @JsonProperty("PlayCount")
    private Integer playCount;

    @JsonProperty("VictoryCount")
    private Integer victoryCount;

    @JsonProperty("LoseCount")
    private Integer lostCount;

    @JsonProperty("TieCount")
    private Integer tieCount;

    @JsonProperty("KillCount")
    private Integer killCount;

    @JsonProperty("AceCount")
    private Integer aceCount;

    @JsonProperty("DeathCount")
    private Integer deathCount;
}
