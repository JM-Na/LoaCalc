package jmna.loacalc.feign.client.armories.engravings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ArkPassiveEffect {
    @JsonProperty("AbilityStoneLevel")
    private Integer abilityStoneLevel;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("Level")
    private Integer level;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Description")
    private String description;
}
