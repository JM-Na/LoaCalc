package jmna.loacalc.processor.equipment.accessory;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EngravingEffect {
    private String engravingName;
    private String engravingLvl;

    public EngravingEffect(String engravingName, String engravingLvl) {
        this.engravingName = engravingName;
        this.engravingLvl = engravingLvl;
    }
}
