package jmna.loacalc.calculator.hone;

import java.util.Arrays;

public enum T4WeaponHone {
    LEVEL_10(10, 0),
    LEVEL_11(11, 2348),
    LEVEL_12(12, 2427),
    LEVEL_13(13, 2618),
    LEVEL_14(14, 2710),
    LEVEL_15(15, 2805),
    LEVEL_16(16, 2903),
    LEVEL_17(17, 3004),
    LEVEL_18(18, 3110),
    LEVEL_19(19, 3219),
    LEVEL_20(20, 3331),
    LEVEL_21(21, 3448),
    LEVEL_22(22, 3568),
    LEVEL_23(23, 3694),
    LEVEL_24(24, 3823),
    LEVEL_25(25, 3956);

    private final int honeLvl;
    private final int increment;

    T4WeaponHone(int honeLvl, int increment) {
        this.honeLvl = honeLvl;
        this.increment = increment;
    }

    public static T4WeaponHone of(int honeLvl) {
        return Arrays.stream(values())
                .filter(value -> value.honeLvl == honeLvl)
                .findFirst()
                .orElse(null);
    }

    public static int findIncrementByTargetLevel(int targetLvl) {
        T4WeaponHone target = T4WeaponHone.of(targetLvl);
        if (target == null) {
            return 0;
        }
        return target.increment;
    }
}