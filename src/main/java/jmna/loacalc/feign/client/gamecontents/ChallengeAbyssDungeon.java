package jmna.loacalc.feign.client.gamecontents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChallengeAbyssDungeon {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("MinCharacterLevel")
    private Integer minCharacterLevel;
    @JsonProperty("MinItemLevel")
    private Integer minItemLevel;
    @JsonProperty("AreaName")
    private String areaName;
    @JsonProperty("StartTime")
    private String startTime;
    @JsonProperty("EndTime")
    private String endTime;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("RewardItems")
    private List<Item> items;
}
