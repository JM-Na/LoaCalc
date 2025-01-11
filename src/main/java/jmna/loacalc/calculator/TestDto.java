package jmna.loacalc.calculator;

import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import lombok.Data;

@Data
public class TestDto {
    private CharacterProfile characterProfile;
    private CharacterEquipment characterEquipment;
    private TotalArmoryEffect totalArmoryEffect;

    public TestDto(CharacterProfile characterProfile, CharacterEquipment characterEquipment, TotalArmoryEffect totalArmoryEffect) {
        this.characterProfile = characterProfile;
        this.characterEquipment = characterEquipment;
        this.totalArmoryEffect = totalArmoryEffect;
    }
}
