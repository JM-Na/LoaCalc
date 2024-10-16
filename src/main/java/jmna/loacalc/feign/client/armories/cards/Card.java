package jmna.loacalc.feign.client.armories.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Card {
    @JsonProperty("Slot")
    private Integer slot;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("AwakeCount")
    private Integer awakeCount;
    @JsonProperty("AwakeTotal")
    private Integer awakeTotal;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Tooltip")
    private String tooltip;
}
