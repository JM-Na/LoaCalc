package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EtcOption {
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("Text")
    private String text;
    @JsonProperty("Tiers")
    private List<Integer> tiers;
    @JsonProperty("EtcSubs")
    private List<EtcSub> etcSubs;
}
