package jmna.loacalc.feign.client.armories.engravings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Engraving {
    @JsonProperty("Slot")
    private Integer slot;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Tooltip")
    private String tooltip;
}
