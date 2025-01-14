package jmna.loacalc.calculator;

import lombok.Data;

import java.util.List;

@Data
public class SpecUpDto {
    private List<AccessorySpecUp> accessorySpecUpList;
    private List<EngravingSpecUp> engravingSpecUpList;
    private List<HoneSpecUp> weaponSpecUp;
    private List<HoneSpecUp> armorSpecUp;

    public SpecUpDto(List<AccessorySpecUp> accessorySpecUpList, List<EngravingSpecUp> engravingSpecUpList, List<HoneSpecUp> weaponSpecUp, List<HoneSpecUp> armorSpecUp) {
        this.accessorySpecUpList = accessorySpecUpList;
        this.engravingSpecUpList = engravingSpecUpList;
        this.weaponSpecUp = weaponSpecUp;
        this.armorSpecUp = armorSpecUp;
    }
}
