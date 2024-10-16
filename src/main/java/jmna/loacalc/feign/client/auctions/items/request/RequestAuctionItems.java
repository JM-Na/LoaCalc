package jmna.loacalc.feign.client.auctions.items.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RequestAuctionItems {
    @JsonProperty("ItemLevelMin")
    private Integer itemLevelMin;
    @JsonProperty("ItemLevelMax")
    private Integer itemLevelMax;
    @JsonProperty("ItemGradeQuality")
    private Integer itemGradeQuality;
    @JsonProperty("ItemUpgradeLevel")
    private Integer itemUpgradeLevel;
    @JsonProperty("ItemTradeAllowCount")
    private Integer itemTradeAllowCount;
    @JsonProperty("SkillOptions")
    private List<SkillOption> skillOptions;
    @JsonProperty("EtcOptions")
    private List<EtcOption> etcOptions;
    @JsonProperty("Sort")
    private String sort; //"BIDSTART_PRICE"
    @JsonProperty("CategoryCode")
    private Integer categoryCode;
    @JsonProperty("CharacterClass")
    private String characterClass;
    @JsonProperty("ItemTier")
    private Integer itemTier;
    @JsonProperty("ItemGrade")
    private String itemGrade;
    @JsonProperty("ItemName")
    private String itemName;
    @JsonProperty("PageNo")
    private Integer pageNo;
    @JsonProperty("SortCondition")
    private String sortCondition;
}
