package jmna.loacalc.feign.client.armories.profiles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tendency {
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Point")
    private String point;
    @JsonProperty("MaxPoint")
    private String maxPoint;
}
