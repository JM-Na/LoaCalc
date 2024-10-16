package jmna.loacalc.feign.client.armories.collectibles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CollectiblePoint {
    @JsonProperty("PointName")
    private String pointName;
    @JsonProperty("Point")
    private Integer point;
    @JsonProperty("MaxPoint")
    private Integer maxPoint;
}
