package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArmoryAvatar {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Grade")
    private String grade;

    @JsonProperty("IsSet")
    private Boolean isSet;

    @JsonProperty("IsInner")
    private Boolean isInner;

    @JsonProperty("Tooltip")
    private String tooltip;
}
