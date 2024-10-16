package jmna.loacalc.feign.client.armories.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tripod {
    @JsonProperty("Tier")
    private Integer tier;
    @JsonProperty("Slot")
    private Integer slot;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Level")
    private Integer level;
    @JsonProperty("IsSelected")
    private Boolean isSelected;
    @JsonProperty("Tooltip")
    private String tooltip;
}
