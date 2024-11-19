package jmna.loacalc.processor.armory.equipment;

import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@RequiredArgsConstructor
public class CharacterEquipment {
    private List<BaseArmory> baseArmories;
    private List<SubEquipment> subEquipments;
    private int totalTranscendence;

    public void setTotalTranscendence() {
        this.totalTranscendence = baseArmories.stream()
                .mapToInt(armory ->
                        Optional.ofNullable(armory.getTranscendenceGrade()).orElse(0)) // 널 방어
                .sum();
    }
}
