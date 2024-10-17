package jmna.loacalc.feign.client.auctions;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.auctions.items.Item;
import lombok.Data;

import java.util.List;

@Data
public class AuctionItems {

    @JsonProperty("PageNo")
    private Integer pageNo;
    @JsonProperty("PageSize")
    private Integer pageSize;
    @JsonProperty("TotalCount")
    private Integer totalCount;
    @JsonProperty("Items")
    private List<Item> items;
}
