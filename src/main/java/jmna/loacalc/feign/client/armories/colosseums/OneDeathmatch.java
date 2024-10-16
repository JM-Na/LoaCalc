package jmna.loacalc.feign.client.armories.colosseums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OneDeathmatch {
    @JsonProperty("KillCount")
    private Integer killCount;
    @JsonProperty("DeathCount")
    private Integer deathCount;
    @JsonProperty("AllKillCount")
    private Integer allKillCount;
    @JsonProperty("OutDamage")
    private Integer outDamage;
    @JsonProperty("InDamage")
    private Integer inDamage;
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
}
