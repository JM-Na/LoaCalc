package jmna.loacalc.feign.client.markets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loaapi.feign.client.markets.itemsByID.Stat;
import lombok.Data;

import java.util.List;

@Data
public class MarketItemsById {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("TradeRemainCount")
    private Integer tradeRemainCount;
    @JsonProperty("BundleCount")
    private String bundleCount;
    @JsonProperty("Stats")
    private List<Stat> stats;
    @JsonProperty("ToolTip")
    private String toolTip;
}
