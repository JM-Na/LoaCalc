package jmna.loacalc.feign.client.auctions;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.auctions.options.Category;
import jmna.loacalc.feign.client.auctions.options.EtcOption;
import jmna.loacalc.feign.client.auctions.options.SkillOption;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuctionOptions {
    @JsonProperty("MaxItemLevel")
    private Integer maxItemLevel;
    @JsonProperty("ItemGradeQualities")
    private List<Integer> itemGradeQualities;
    @JsonProperty("SkillOptions")
    private List<SkillOption> skillOptions;
    @JsonProperty("EtcOptions")
    private List<EtcOption> etcOptions;
    @JsonProperty("Categories")
    public List<Category> categories;
    @JsonProperty("ItemGrades")
    private List<String> itemGrades;
    @JsonProperty("ItemTiers")
    private List<Integer> itemTiers;
    @JsonProperty("Classes")
    private List<String> classes;
}
