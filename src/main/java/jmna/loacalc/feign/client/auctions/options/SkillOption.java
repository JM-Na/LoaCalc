package jmna.loacalc.feign.client.auctions.options;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SkillOption {
    @JsonProperty("Value")
    private Integer value;
    @JsonProperty("Class")
    private String clase;
    @JsonProperty("Text")
    private String Text;
    @JsonProperty("IsSkillGroup")
    private Boolean isSkillGroup;
    @JsonProperty("Tripods")
    private List<Tripod> tripods;
}