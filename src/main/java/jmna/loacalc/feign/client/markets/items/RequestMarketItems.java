package jmna.loacalc.feign.client.markets.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RequestMarketItems {
    @JsonProperty("Sort")
    private String sort;
    @JsonProperty("CategoryCode")
    private Integer categoryCode;
    @JsonProperty("CharacterClass")
    private String characterClass;
    @JsonProperty("ItemTier")
    private Integer itemTier;
    @JsonProperty("ItemGrade")
    private Integer itemGrade;
    @JsonProperty("ItemName")
    private String itemName;
    @JsonProperty("PageNo")
    private Integer pageNo;
    @JsonProperty("SortCondition")
    private String sortCondition;
}
