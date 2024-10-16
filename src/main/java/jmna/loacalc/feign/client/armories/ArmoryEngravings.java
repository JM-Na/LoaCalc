package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loaapi.feign.client.armories.engravings.ArkPassiveEffect;
import jmna.loaapi.feign.client.armories.engravings.Effect;
import jmna.loaapi.feign.client.armories.engravings.Engraving;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryEngravings {
    @JsonProperty("Engravings")
    private List<Engraving> engravings;

    @JsonProperty("Effects")
    private List<Effect> effects;

    @JsonProperty("ArkPassiveEffects")
    private List<ArkPassiveEffect> arkPassiveEffects;
}
