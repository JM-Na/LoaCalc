package jmna.loacalc.calculator;

import lombok.Data;

import java.util.List;

@Data
public class SpecUpDto {
    private List<AccessorySpecUp> accessorySpecUpList;
    private List<EngravingSpecUp> engravingSpecUpList;
    private List<HoneSpecUp> weaponSpecUp;
    private List<HoneSpecUp> armorSpecUp;
    private List<GemSpecUp> gemSpecUpList;

    public SpecUpDto(List<AccessorySpecUp> accessorySpecUpList, List<EngravingSpecUp> engravingSpecUpList, List<HoneSpecUp> weaponSpecUp, List<HoneSpecUp> armorSpecUp, List<GemSpecUp> gemSpecUpList) {
        this.accessorySpecUpList = accessorySpecUpList;
        this.engravingSpecUpList = engravingSpecUpList;
        this.weaponSpecUp = weaponSpecUp;
        this.armorSpecUp = armorSpecUp;
        this.gemSpecUpList = gemSpecUpList;
    }
}
