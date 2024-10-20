package jmna.loacalc.processor.equipment;

import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CharacterEquipment {
    private List<BaseArmory> baseArmories;
    private List<SubEquipment> subEquipments;
}
