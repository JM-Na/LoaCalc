package jmna.loacalc.feign.client.armories.gems;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Gem {
    @JsonProperty("Slot")
    private Integer slot;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Level")
    private Integer level;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Tooltip")
    private String tooltip;
}
