package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.colosseums.Colosseum;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryColosseums {
    @JsonProperty("Rank")
    private Integer rank;
    @JsonProperty("PreRank")
    private Integer preRank;
    @JsonProperty("Exp")
    private Integer exp;
    @JsonProperty("Colosseums")
    private List<Colosseum> colosseums;
}
