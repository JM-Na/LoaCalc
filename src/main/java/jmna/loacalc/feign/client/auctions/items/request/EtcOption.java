package jmna.loacalc.feign.client.auctions.items.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EtcOption {
    @JsonProperty("FirstOption")
    private String firstOption;
    @JsonProperty("SecondOption")
    private String secondOption;
    @JsonProperty("MinValue")
    private Integer minValue;
    @JsonProperty("MaxValue")
    private Integer maxValue;
}
