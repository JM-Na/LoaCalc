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
    WEAPON_LEVEL_10("무기",10, 4185),
    WEAPON_LEVEL_20("무기", 20, 4470),
    HELMET_LEVEL_10("머리 방어구",10, 2417),
    HELMET_LEVEL_20("머리 방어구", 20, 2582),
    SHOULDER_LEVEL_10("어깨 방어구",10, 2573),
    SHOULDER_LEVEL_20("어깨 방어구", 20, 2747),
    CHEST_LEVEL_10("상의 방어구",10, 1934),
    CHEST_LEVEL_20("상의 방어구", 20, 2065),
    PANTS_LEVEL_10("하의 방어구",10, 2089),
    PANTS_LEVEL_20("하의 방어구", 20, 2231),
    GLOVES_LEVEL_10("장갑 방어구",10, 2900),
    GLOVES_LEVEL_20("장갑 방어구", 20, 3098),
    ;

    private final String type;
    private final int honeLvl;
    private final int increment;

    AdvancedHone(String type, int honeLvl, int increment) {
        this.type = type;
        this.honeLvl = honeLvl;
        this.increment = increment;
    }

    public static AdvancedHone of(String type, int honeLvl) {
        return Arrays.stream(values())
                .filter(value -> value.type.equals(type)&&(value.honeLvl == honeLvl))
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
}
