package jmna.loacalc.feign.client.armories.arkpassives;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Point {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("Tooltip")
    private String tooltip;
}
