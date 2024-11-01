package jmna.loacalc.processor.equipment.armory;

import lombok.Data;

@Data
public abstract class BaseArmory {
    private String name;
    private String type;
    private Integer honeLvl;
    private Integer itemLvl;
    private Integer quality;
    private String icon;

    // 초월
    private Integer transcendenceLvl;
    private Integer transcendenceGrade;

    // 상급 재련
    private Integer advancedHone;

    public BaseArmory() {

    }
}
