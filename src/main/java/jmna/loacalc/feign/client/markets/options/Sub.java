package jmna.loacalc.feign.client.markets.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sub {
    @JsonProperty("Code")
    private String code;
    @JsonProperty("CodeName")
    private String codeName;
}
