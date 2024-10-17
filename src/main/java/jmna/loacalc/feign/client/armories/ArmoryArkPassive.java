package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.arkpassives.Effect;
import jmna.loacalc.feign.client.armories.arkpassives.Point;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryArkPassive {
    @JsonProperty("IsArkPassive")
    private Boolean isArkPassive;
    @JsonProperty("Points")
    private List<Point> points;
    @JsonProperty("Effects")
    private List<Effect> effects;
}
