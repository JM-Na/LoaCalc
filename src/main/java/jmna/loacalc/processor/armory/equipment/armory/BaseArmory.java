package jmna.loacalc.processor.armory.equipment.armory;

import lombok.Data;

@Data
public abstract class BaseArmory {
    private String name;
    private String type;
    private Integer honeLvl = 0;
    private Integer itemLvl;
    private Integer quality;
    private String icon;

    // 초월
    private Integer transcendenceLvl;
    private Integer transcendenceGrade;

    // 상급 재련
    private Integer advancedHone = 0;

    public BaseArmory() {

    }
}
