package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sub {
    @JsonProperty("Code")
    private Integer code;
    @JsonProperty("CodeName")
    private String codeName;
}