package jmna.loacalc.calculator.hone;

import java.util.Arrays;

public enum AdvancedHone {
//    LEVEL_1(1, 405),
//    LEVEL_2(2, 409),
//    LEVEL_3(3, 412),
//    LEVEL_4(4, 415),
//    LEVEL_5(5,417),
//    LEVEL_6(6,419),
//    LEVEL_7(7,422),
//    LEVEL_8(8,425),
//    LEVEL_9(9,429),
//    LEVEL_10(10, 432),
//    LEVEL_11(11,433),
//    LEVEL_12(12,436),
//    LEVEL_13(13,440),
//    LEVEL_14(14,443),
//    LEVEL_15(15,446),
//    LEVEL_16(16,448),
//    LEVEL_17(17,451),
//    LEVEL_18(18,454),
//    LEVEL_19(19,458),
//    LEVEL_20(20,461),
    LEVEL_10(10, 4185),
    LEVEL_20(20, 4470),;

    private final int honeLvl;
    private final int increment;

    AdvancedHone(int honeLvl, int increment) {
        this.honeLvl = honeLvl;
        this.increment = increment;
    }

    public static AdvancedHone of(int honeLvl) {
        return Arrays.stream(values())
                .filter(value -> value.honeLvl == honeLvl)
                .findFirst()
                .orElse(null);
    }

    public static int findIncrementByTargetLevel(int targetLvl) {
        AdvancedHone target = AdvancedHone.of(targetLvl);
        if (target == null) {
            return 0;
        }
        return target.increment;
    }
}
