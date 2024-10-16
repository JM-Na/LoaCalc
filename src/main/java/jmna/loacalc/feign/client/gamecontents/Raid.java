package jmna.loacalc.feign.client.gamecontents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Raid {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("MinCharacterLevel")
    private Integer minCharacterLevel;
    @JsonProperty("MinItemLevel")
    private Integer minItemLevel;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("EndTime")
    private String endTime;
    @JsonProperty("Image")
    private String image;
}
