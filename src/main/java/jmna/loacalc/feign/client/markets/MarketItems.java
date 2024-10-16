package jmna.loacalc.feign.client.markets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loaapi.feign.client.markets.items.Item;
import lombok.Data;

import java.util.List;

@Data
public class MarketItems {
    @JsonProperty("PageNo")
    private Integer pageNo;
    @JsonProperty("PageSize")
    private Integer pageSize;
    @JsonProperty("TotalCount")
    private Integer totalCount;
    @JsonProperty("Items")
    private List<Item> items;
}
