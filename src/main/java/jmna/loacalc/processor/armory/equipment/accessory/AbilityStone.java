package jmna.loacalc.processor.armory.equipment.accessory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@ToString(callSuper = true)
public class AbilityStone extends SubEquipment {

    private List<EngravingData> engravingData;

    public AbilityStone(String type, String name, Integer quality, String grade, int tier) {
        this.setType(type);
        this.setName(name);
        this.setQuality(quality);
        this.setGrade(grade);
        this.setTier(tier);
    }
}
