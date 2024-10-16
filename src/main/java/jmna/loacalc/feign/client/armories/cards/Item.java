package jmna.loacalc.feign.client.armories.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
}
