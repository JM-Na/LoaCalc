package jmna.loacalc.feign.client.gamecontents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChallengeGuardianRaid {
    @JsonProperty("Raids")
    private List<Raid> raids;
    @JsonProperty("RewardTimes")
    private List<RewardItem> rewardItems;
}
