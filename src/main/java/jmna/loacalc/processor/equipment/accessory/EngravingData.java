package jmna.loacalc.processor.equipment.accessory;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EngravingData {
    private String engravingName;
    private String engravingLvl;

    public EngravingData(String engravingName, String engravingLvl) {
        this.engravingName = engravingName;
        this.engravingLvl = engravingLvl;
    }
}
