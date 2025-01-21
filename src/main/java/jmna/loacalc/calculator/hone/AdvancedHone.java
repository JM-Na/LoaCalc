package jmna.loacalc.calculator.hone;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum AdvancedHone {
    WEAPON_LEVEL_10("무기", 10, 4185, 31200, 832, 1300, 260000, 58500, 108),
    WEAPON_LEVEL_20("무기", 20, 4470, 57200, 1144, 1404, 520000, 130000, 162),
    HELMET_LEVEL_10("머리", 10, 2417, 26000, 624, 780, 156000, 49400, 108),
    HELMET_LEVEL_20("머리", 20, 2582, 46800, 832, 832, 312000, 93600, 162),
    SHOULDER_LEVEL_10("어깨", 10, 2573, 26000, 624, 780, 156000, 49400, 108),
    SHOULDER_LEVEL_20("어깨", 20, 2747, 46800, 832, 832, 312000, 93600, 162),
    CHEST_LEVEL_10("상의", 10, 1934, 26000, 624, 780, 156000, 49400, 108),
    CHEST_LEVEL_20("상의", 20, 2065, 46800, 832, 832, 312000, 93600, 162),
    PANTS_LEVEL_10("하의", 10, 2089, 26000, 624, 780, 156000, 49400, 108),
    PANTS_LEVEL_20("하의", 20, 2231, 46800, 832, 832, 312000, 93600, 162),
    GLOVES_LEVEL_10("장갑", 10, 2900, 26000, 624, 780, 156000, 49400, 108),
    GLOVES_LEVEL_20("장갑", 20, 3098, 46800, 832, 832, 312000, 93600, 162),
    ;

    private final String type;
    private final int honeLvl;
    private final int increment;

    private final int destGuard;
    private final int leapStone;
    private final int fusionStone;
    private final int fragment;
    private final int gold;
    private final int supIngredient;

    AdvancedHone(String type, int honeLvl, int increment, int destGuard, int leapStone, int fusionStone, int fragment, int gold, int supIngredient) {
        this.type = type;
        this.honeLvl = honeLvl;
        this.increment = increment;
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
        if (target == null) {
            return 0;
        }
        return target.increment;
    }

    public static int findTotalIncrementByNameAndTargetLevel(List<String> typeList, int targetLvl) {
        int sum = 0;

        for (String type : typeList) {
            sum += findIncrementByNameAndTargetLevel(type, targetLvl);
        }

        return sum;
    }
}
