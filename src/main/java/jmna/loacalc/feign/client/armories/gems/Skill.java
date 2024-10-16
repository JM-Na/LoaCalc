package jmna.loacalc.feign.client.armories.gems;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Skill {
    @JsonProperty("GemSlot")
    private Long gemSlot;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private List<String> description;
    @JsonProperty("Option")
    private String option;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Tooltip")
    private String tooltip;
}
