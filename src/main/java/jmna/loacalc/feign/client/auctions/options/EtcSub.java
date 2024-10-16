package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class EtcSub {
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("Text")
    private String text;
    @JsonProperty("Class")
    private String clase;
    @JsonProperty("Categorys")
    private List<Integer> categorys;
    @JsonProperty("Tiers")
    private List<Integer> tiers;
    @JsonProperty("EtcValues")
    private List<EtcValue> etcValues;
}