package jmna.loacalc.calculator.specup;

import lombok.Data;

import java.util.List;

@Data
public class SpecUpDto {
    private List<AccessorySpecUp> accessorySpecUpList;
    private List<EngravingSpecUp> engravingSpecUpList;
    private List<HoneSpecUp> honeSpecUp;
    private List<GemSpecUp> gemSpecUpList;

    public SpecUpDto(List<AccessorySpecUp> accessorySpecUpList, List<EngravingSpecUp> engravingSpecUpList, List<HoneSpecUp> honeSpecUp, List<GemSpecUp> gemSpecUpList) {
        this.accessorySpecUpList = accessorySpecUpList;
        this.engravingSpecUpList = engravingSpecUpList;
        this.honeSpecUp = honeSpecUp;
        this.gemSpecUpList = gemSpecUpList;
    }
}
