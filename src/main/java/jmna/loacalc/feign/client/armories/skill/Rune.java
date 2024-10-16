package jmna.loacalc.feign.client.armories.skill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rune {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Tooltip")
    private String tooltip;
}
