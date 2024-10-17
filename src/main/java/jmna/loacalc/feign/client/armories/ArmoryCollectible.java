package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.collectibles.CollectiblePoint;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryCollectible {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Point")
    private Integer point;
    @JsonProperty("MaxPoint")
    private Integer maxPoint;
    @JsonProperty("CollectiblePoints")
    private List<CollectiblePoint> collectiblePoints;
}
