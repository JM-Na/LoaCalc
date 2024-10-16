package jmna.loacalc.feign.client.markets.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Item {
    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("BundleCount")
    private Integer bundleCount;
    @JsonProperty("TradeRemainCount")
    private Integer tradeRemainCount;
    @JsonProperty("YDayAvgPrice")
    private Double yDayAvgPrice;
    @JsonProperty("RecentPrice")
    private Integer recentPrice;
    @JsonProperty("CurrentMinPrice")
    private Integer currentMinPrice;
}
