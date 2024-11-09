package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryTotalForEffect {
    @JsonProperty("ArmoryProfile")
    private ArmoryProfile armoryProfile;
    @JsonProperty("ArmoryEquipment")
    private List<ArmoryEquipment> armoryEquipments;
    @JsonProperty("ArmoryAvatars")
    private List<ArmoryAvatar> armoryAvatars;
    @JsonProperty("ArmoryEngraving")
    private ArmoryEngravings armoryEngravings;
    @JsonProperty("ArmoryGem")
    private ArmoryGem armoryGem;
    @JsonProperty("ArkPassive")
    private ArmoryArkPassive armoryArkPassive;
}
