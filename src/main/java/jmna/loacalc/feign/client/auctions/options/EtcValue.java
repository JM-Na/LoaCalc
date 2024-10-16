package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EtcValue {
    @JsonProperty("DisplayValue")
    private String displayValue;
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("IsPercentage")
    private Boolean isPercentage;
}