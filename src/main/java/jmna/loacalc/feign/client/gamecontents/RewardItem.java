package jmna.loacalc.feign.client.gamecontents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RewardItem {
    @JsonProperty("ItemLevel")
    private Integer itemLevel;
    @JsonProperty("Items")
    private List<Item> items;
}
