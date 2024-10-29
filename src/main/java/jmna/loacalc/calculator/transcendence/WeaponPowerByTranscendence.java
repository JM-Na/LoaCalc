package jmna.loacalc.calculator.transcendence;

import java.util.Arrays;

public enum WeaponPowerByTranscendence {
    TRANSCENDENCE_1(1, 300),
    TRANSCENDENCE_2(2, 640),
    TRANSCENDENCE_3(3, 1020),
    TRANSCENDENCE_4(4, 1440),
    TRANSCENDENCE_5(5, 1900),
    TRANSCENDENCE_6(6, 2400),
    TRANSCENDENCE_7(7, 2940);

    private final int level;
    private final int weaponPower;

    WeaponPowerByTranscendence(int level, int weaponPower) {
        this.level = level;
        this.weaponPower = weaponPower;
    }

    public static WeaponPowerByTranscendence of(int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level)
                .findFirst()
                .orElse(null);
    }

    public static int findWeaponPowerByLevel(int level) {
        WeaponPowerByTranscendence mainStat = WeaponPowerByTranscendence.of(level);
        if (mainStat == null) {
            return 0;
        }
        return mainStat.weaponPower;
    }
}
