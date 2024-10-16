package jmna.loacalc.feign.client.armories.engravings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Effect {
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
}
