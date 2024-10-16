package jmna.loacalc.feign.client.markets.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Category {
    @JsonProperty("Subs")
    private List<Sub> subs;
    @JsonProperty("Code")
    private Integer code;
    @JsonProperty("CodeName")
    private String codeName;
}
