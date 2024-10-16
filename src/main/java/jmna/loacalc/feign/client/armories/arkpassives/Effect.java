package jmna.loacalc.feign.client.armories.arkpassives;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Effect {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Tooltip")
    private String tooltip;
}
