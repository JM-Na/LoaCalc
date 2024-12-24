package jmna.loacalc.feign.client.auctions.items.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EtcOption {
    @JsonProperty("FirstOption")
    private Integer firstOption;
    @JsonProperty("SecondOption")
    private Integer secondOption;
    @JsonProperty("MinValue")
    private Integer minValue;
    @JsonProperty("MaxValue")
    private Integer maxValue;
}
