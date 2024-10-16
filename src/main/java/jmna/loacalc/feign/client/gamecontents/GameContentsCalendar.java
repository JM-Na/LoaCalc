package jmna.loacalc.feign.client.gamecontents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GameContentsCalendar {

    @JsonProperty("CategoryName")
    private String categoryName;
    @JsonProperty("ContentsName")
    private String contentsName;
    @JsonProperty("ContentsIcon")
    private String contentsIcon;
    @JsonProperty("MinItemLevel")
    private Integer minItemLevel;
    @JsonProperty("StartTimes")
    private List<String> startTimes;
    @JsonProperty("Location")
    private String location;
    @JsonProperty("RewardItems")
    private List<RewardItem> rewardItems;
}
