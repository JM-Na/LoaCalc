package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Tripod {
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("Text")
    private String text;
    @JsonProperty("IsGem")
    private Boolean isGem;
}