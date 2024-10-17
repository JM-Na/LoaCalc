package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.gems.Effects;
import jmna.loacalc.feign.client.armories.gems.Gem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryGems {
    @JsonProperty("Gems")
    private List<Gem> gems;

    @JsonProperty("Effects")
    private Effects effects;
}
