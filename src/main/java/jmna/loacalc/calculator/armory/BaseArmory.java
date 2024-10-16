package jmna.loacalc.calculator.armory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public abstract class BaseArmory {
    private Integer honeLvl;
    private String name;
    private String type;
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
