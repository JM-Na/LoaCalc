package jmna.loacalc.feign.client.armories.colosseums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TeamDeathmatch {
    @JsonProperty("PlayCount")
    private Integer PlayCount;
    @JsonProperty("VictoryCount")
    private Integer VictoryCount;
    @JsonProperty("LoseCount")
    private Integer LoseCount;
    @JsonProperty("TieCount")
    private Integer TieCount;
    @JsonProperty("KillCount")
    private Integer KillCount;
    @JsonProperty("AceCount")
    private Integer AceCount;
    @JsonProperty("DeathCount")
    private Integer DeathCount;
}
