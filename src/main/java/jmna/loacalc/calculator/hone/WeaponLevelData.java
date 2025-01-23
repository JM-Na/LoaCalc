package jmna.loacalc.calculator.hone;


public enum WeaponLevelData {
    LVL_1640(70036),
    LVL_1645(72384),
    LVL_1650(74811),
    LVL_1655(77429),
    LVL_1660(80139),
    LVL_1665(82944),
    LVL_1670(85847),
    LVL_1675(88851),
    LVL_1680(91961),
    LVL_1685(95180),
    LVL_1690(98511),
    LVL_1695(101959),
    LVL_1700(105527),
    LVL_1705(109221),
    LVL_1710(113044),
    LVL_1715(117000),
    LVL_1720(121095),
    LVL_1725(125333),
    LVL_1730(129720),
    LVL_1735(134260),
    LVL_1740(138959),
    LVL_1745(143823),
    LVL_1750(148857),
    LVL_1755(154000),
    ;

    private final int weaponPower;
    WeaponLevelData(int weaponPower) {
        this.weaponPower = weaponPower;
    }
}
