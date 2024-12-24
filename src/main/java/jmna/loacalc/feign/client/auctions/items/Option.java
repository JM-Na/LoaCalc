package jmna.loacalc.feign.client.auctions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Option {
    @JsonProperty("Type")
    private String type; // "None"
    @JsonProperty("OptionName")
    private String optionName;
    @JsonProperty("OptionNameTripod")
    private String optionNameTripod;
    @JsonProperty("Value")
    private Double value;
    @JsonProperty("IsPenalty")
    private Boolean isPenalty;
    @JsonProperty("ClassName")
    private String className;
    @JsonProperty("IsValuePercentage")
    private Boolean isValuePercentage;
}
