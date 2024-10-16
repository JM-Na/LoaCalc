package jmna.loacalc.feign.client.markets.itemsByID;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Stat {
    @JsonProperty("Date")
    private String date;
    @JsonProperty("AvgPrice")
    private Integer avgPrice;
    @JsonProperty("TradeCount")
    private Integer tradeCount;
}
