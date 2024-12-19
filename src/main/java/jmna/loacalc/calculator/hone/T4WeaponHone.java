package jmna.loacalc.calculator.hone;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public enum T4WeaponHone {
    LEVEL_10(10, 0, 0, 0, 0, 0, 0),
    LEVEL_11(11, 2348, 8750, 126, 84, 35000, 11340),
    LEVEL_12(12, 2427, 9100, 147, 84, 37100, 12530),
    LEVEL_13(13, 2618, 16800, 288, 180, 91200, 23880),
    LEVEL_14(14, 2710, 18600, 324, 180, 98400, 26400),
    LEVEL_15(15, 2805, 23800, 420, 252, 123200, 34020),
    LEVEL_16(16, 2903, 27300, 462, 252, 131600, 37380),
    LEVEL_17(17, 3004, 39600, 648, 450, 216000, 52920),
    LEVEL_18(18, 3110, 44100, 702, 450, 232200, 57960),
    LEVEL_19(19, 3219, 48600, 756, 450, 246600, 63180),
    LEVEL_20(20, 3331, 97350, 1485, 1155, 528000, 126390),
    LEVEL_21(21, 3448, 105600, 1584, 1155, 564300, 137280),
    LEVEL_22(22, 3568, 177600, 2496, 1680, 873600, 216480),
    LEVEL_23(23, 3694, 192000, 2688, 1680, 921600, 233760),
    LEVEL_24(24, 3823, 386400, 5520, 4600, 1876800, 483000),
    LEVEL_25(25, 3956, 414000, 5980, 4600, 1978000, 519800);

    private final int honeLvl;
    private final int increment;
    private final int destStone; // 파괴강석
    private final int leapStone; // 돌파석
    private final int fusionStone; // 아비도스
    private final int fragment; // 운명의 파편
    private final int gold; // 골드

    T4WeaponHone(int honeLvl, int increment, int destStone, int leapStone, int fusionStone, int fragment, int gold) {
        this.honeLvl = honeLvl;
        this.increment = increment;
        this.destStone = destStone;
        this.leapStone = leapStone;
        this.fusionStone = fusionStone;
        this.fragment = fragment;
        this.gold = gold;
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

    public static double findCostByTargetLevel(int targetLvl, Boolean isFragmentBound) {
        T4WeaponHone target = T4WeaponHone.of(targetLvl);
        if (target == null) {
            return 0;
        }

        return HoneIngredients.getTotalCostByType("무기", isFragmentBound, target.destStone, target.leapStone, target.fusionStone, target.fragment, target.gold);
    }
}