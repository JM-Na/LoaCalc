package jmna.loacalc.calculator.hone;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Deprecated
public enum HoneIngredientsData {
    T3_DESTRUCTION("정제된 파괴강석"),
    T4_DESTRUCTION("운명의 파괴석"),
    T3_GUARDIAN("정제된 수호강석"),
    T4_GUARDIAN("운명의 수호석"),
    T3_LEAP("찬란한 명예의 돌파석"),
    T4_LEAP("운명의 돌파석"),
    T3_FRAGMENT("명예의 파편 주머니(중)"),
    T4_FRAGMENT("운명의 파편 주머니(중)"),
    T3_FUSION("최상급 오레하 융화 재료"),
    T4_FUSION("아비도스 융화 재료"),
    T3_SUP_RARE("태양의 은총"),
    T3_SUP_HEROIC("태양의 축복"),
    T3_SUP_EPIC("태양의 가호"),
    T4_SUP_WEAPON("용암의 숨결"),
    T4_SUP_ARMOR("빙하의 숨결"),
    ;

    @Getter
    private final String name;
    @Getter
    @Setter
    private String icon;
    @Getter
    @Setter
    private double price;

    HoneIngredientsData(String name) {
        this.name = name;
    }

    public static HoneIngredientsData of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void setIconAndPrice(String name, String icon, double price) {
        HoneIngredientsData target = of(name);
        if (target != null) {
            target.setIcon(icon);
            target.setPrice(price);
        }
    }

    public static double findPriceByName(String name) {
        HoneIngredientsData target = of(name);
        return target.price;
    }

    public static double getTotalCostByType(String type, Boolean isFragmentBound,
                                            int destGuard, int leapStone, int fusionStone, int fragment, int gold) {
        double destGuardPrice = 0;

        if (type.equals("무기")) {
            destGuardPrice += HoneIngredientsData.findPriceByName("운명의 파괴석") * destGuard / 10;
        } else {
            destGuardPrice += HoneIngredientsData.findPriceByName("운명의 수호석") * destGuard / 10;
        }
        double leapStonePrice = HoneIngredientsData.findPriceByName("운명의 돌파석") * leapStone;
        double fusionPrice = HoneIngredientsData.findPriceByName("아비도스 융화 재료") * fusionStone;
        double fragmentPrice = HoneIngredientsData.findPriceByName("운명의 파편 주머니(중)") * fragment / 1000;
        double totalCost = destGuardPrice + leapStonePrice + fusionPrice + gold;
        if (!isFragmentBound)
            totalCost += fragmentPrice;

        return totalCost;
    }
}
