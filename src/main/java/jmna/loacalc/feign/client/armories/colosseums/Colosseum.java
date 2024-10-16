package jmna.loacalc.feign.client.armories.colosseums;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Colosseum {
    @JsonProperty("SeasonName")
    private String seasonName;
    @JsonProperty("Competitive")
    private Competitive competitive;
    @JsonProperty("TeamDeathmatch")
    private TeamDeathmatch teamDeathmatch;
    @JsonProperty("TeamElimination")
    private TeamElimination teamElimination;
    @JsonProperty("CoOpBattle")
    private CoOpBattle coOpBattle;
    @JsonProperty("OneDeathmatch")
    private OneDeathmatch oneDeathmatch;
}
