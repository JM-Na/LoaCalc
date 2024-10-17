package jmna.loacalc.feign.client.markets;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.markets.options.Category;
import lombok.Data;

import java.util.List;

@Data
public class MarketOptions {

    @JsonProperty("Categories")
    private List<Category> categories;
    @JsonProperty("ItemGrades")
    private List<String> itemGrades;
    @JsonProperty("ItemTiers")
    private List<Integer> itemTiers;
    @JsonProperty("Classes")
    private List<String> classes;
}
