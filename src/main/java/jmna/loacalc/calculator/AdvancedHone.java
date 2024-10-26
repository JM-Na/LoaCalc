package jmna.loacalc.calculator;

public enum AdvancedHone {
    LEVEL_1(405),
    LEVEL_2(409),
    LEVEL_3(412),
    LEVEL_4(415),
    LEVEL_5(417),
    LEVEL_6(419),
    LEVEL_7(422),
    LEVEL_8(425),
    LEVEL_9(429),
    LEVEL_10(432),
    LEVEL_11(433),
    LEVEL_12(436),
    LEVEL_13(440),
    LEVEL_14(443),
    LEVEL_15(446),
    LEVEL_16(448),
    LEVEL_17(451),
    LEVEL_18(454),
    LEVEL_19(458),
    LEVEL_20(461);

    private final int cost;

    AdvancedHone(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public static int getCostRange(int startLevel, int endLevel) {
        int totalCost = 0;
        for (int i = startLevel; i <= endLevel; i++) {
            totalCost += AdvancedHone.values()[i - 1].getCost();
        }
        return totalCost;
    }
}
