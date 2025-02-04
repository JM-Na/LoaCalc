package jmna.loacalc.calculator.hone;


import lombok.Getter;

import java.awt.print.Pageable;
import java.util.Arrays;

public enum WeaponLevelData {
    LVL_1640(1640, 70036),
    LVL_1645(1645, 72384),
    LVL_1650(1650, 74811),
    LVL_1655(1655, 77429),
    LVL_1660(1660, 80139),
    LVL_1665(1665, 82944),
    LVL_1670(1670, 85847),
    LVL_1675(1675, 88851),
    LVL_1680(1680, 91961),
    LVL_1685(1685, 95180),
    LVL_1690(1690, 98511),
    LVL_1695(1695, 101959),
    LVL_1700(1700, 105527),
    LVL_1705(1705, 109221),
    LVL_1710(1710, 113044),
    LVL_1715(1715, 117000),
    LVL_1720(1720, 121095),
    LVL_1725(1725, 125333),
    LVL_1730(1730, 129720),
    LVL_1735(1735, 134260),
    LVL_1740(1740, 138959),
    LVL_1745(1745, 143823),
    LVL_1750(1750, 148857),
    LVL_1755(1755, 154000),
    ;
    private final int itemLvl;
    @Getter
    private final int weaponPower;

    WeaponLevelData(int itemLvl, int weaponPower) {
        this.itemLvl = itemLvl;
        this.weaponPower = weaponPower;
    }

    public static int getWeaponPowerByItemLvl(int itemLvl) {
        WeaponLevelData target = Arrays.stream(values()).filter(value -> value.itemLvl == itemLvl)
                .findFirst()
                .orElse(null);
        if (target != null)
            return target.getWeaponPower();
        else
            return 0;

    }
}
