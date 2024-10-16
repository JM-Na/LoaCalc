package jmna.loacalc.feign.client.armories.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Stat {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Value")
    private String value;
    @JsonProperty("Tooltip")
    private List<String> tooltip;
}
