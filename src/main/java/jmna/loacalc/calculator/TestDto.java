package jmna.loacalc.calculator;

import jmna.loacalc.calculator.specup.SpecUpDto;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import lombok.Data;

@Data
public class TestDto {
    private CharacterProfile characterProfile;
    private CharacterEquipment characterEquipment;
    private TotalArmoryEffect totalArmoryEffect;
    private SpecUpDto specUpDto;

    public TestDto(CharacterProfile characterProfile, CharacterEquipment characterEquipment, TotalArmoryEffect totalArmoryEffect, SpecUpDto specUpDto) {
        this.characterProfile = characterProfile;
        this.characterEquipment = characterEquipment;
        this.totalArmoryEffect = totalArmoryEffect;
        this.specUpDto = specUpDto;
    }
}
