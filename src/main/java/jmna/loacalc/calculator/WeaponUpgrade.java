package jmna.loacalc.calculator;

public enum WeaponUpgrade {
    LEVEL_10(70036, 0),
    LEVEL_11(72384, 2348),
    LEVEL_12(74811, 2427),
    LEVEL_13(77429, 2618),
    LEVEL_14(80139, 2710),
    LEVEL_15(82944, 2805),
    LEVEL_16(85847, 2903),
    LEVEL_17(88851, 3004),
    LEVEL_18(91961, 3110),
    LEVEL_19(95180, 3219),
    LEVEL_20(98511, 3331),
    LEVEL_21(101959, 3448),
    LEVEL_22(105527, 3568),
    LEVEL_23(109221, 3694),
    LEVEL_24(113044, 3823),
    LEVEL_25(117000, 3956);

    private final int attackPower;
    private final int increment;

    WeaponUpgrade(int attackPower, int increment) {
        this.attackPower = attackPower;
        this.increment = increment;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getIncrement() {
        return increment;
    }
}