package jmna.loacalc.calculator.hone;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AdvancedHone {
    WEAPON_LEVEL_10("무기", 10, 31200, 832, 1300, 260000, 58500, 84),
    WEAPON_LEVEL_20("무기", 20, 57200, 1144, 1404, 520000, 130000, 126),
    WEAPON_LEVEL_30("무기", 30, 62400, 1300, 1456, 598000, 156000, 140),
    WEAPON_LEVEL_40("무기", 40, 72800, 1664, 1560, 676000, 208000, 168),
    HELMET_LEVEL_10("방어구", 10, 24725, 593, 742, 148348, 46977, 100),
    HELMET_LEVEL_20("방어구", 20, 44504, 731, 791, 296697, 89009, 151),
    HELMET_LEVEL_30("방어구", 30, 49449, 890, 841, 346146, 98899, 167),
    HELMET_LEVEL_40("방어구", 40, 59339, 1137, 940, 395595, 118679, 201),
    ;

    private final String type;
    private final int honeLvl;

    private final int destGuard;
    private final int leapStone;
    private final int fusionStone;
    private final int fragment;
    private final int gold;
    private final int supIngredient;

    AdvancedHone(String type, int honeLvl, int destGuard, int leapStone, int fusionStone, int fragment, int gold, int supIngredient) {
        this.type = type;
        this.honeLvl = honeLvl;
        this.destGuard = destGuard;
        this.leapStone = leapStone;
        this.fusionStone = fusionStone;
        this.fragment = fragment;
        this.gold = gold;
        this.supIngredient = supIngredient;
    }

    public static AdvancedHone of(String type, int honeLvl) {
        return Arrays.stream(values())
                .filter(value -> value.type.equals(type) && (value.honeLvl == honeLvl))
                .findFirst()
                .orElse(null);
    }

    public static int findIncrementByNameAndTargetLevel(String type, int targetLvl) {
        AdvancedHone target = AdvancedHone.of(type, targetLvl);
        return 0;
    }

    public static int findTotalIncrementByNameAndTargetLevel(List<String> typeList, int targetLvl) {
        int sum = 0;

        for (String type : typeList) {
            sum += findIncrementByNameAndTargetLevel(type, targetLvl);
        }

        return sum;
    }
}
