package jmna.loacalc.feign.client.auctions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuctionInfo {
    @JsonProperty("StartPrice")
    private Integer startPrice;
    @JsonProperty("BuyPrice")
    private Integer buyPrice;
    @JsonProperty("BidPrice")
    private Integer bidPrice;
    @JsonProperty("EndDate")
    private String endDate;
    @JsonProperty("BidCount")
    private Integer bidCount;
    @JsonProperty("BidStartPrice")
    private Integer bidStartPrice;
    @JsonProperty("IsCompetitive")
    private Boolean isCompetitive;
    @JsonProperty("TradeAllowCount")
    private Integer tradeAllowCount;
    @JsonProperty("UpgradeLevel")
    private Integer upgradeLevel;
}
