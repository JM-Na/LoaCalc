package jmna.loacalc.calculator.hone;

import java.util.Arrays;

public enum T4ArmorHone {
    HEAD_10("머리 방어구", 10, 0, 0, 0, 0, 5250, 77, 49,21000, 6790),
    HEAD_11("머리 방어구", 11, 1356, 108, 96, 107, 5560, 91, 49, 25440, 7490),
    HEAD_12("머리 방어구", 12, 1401, 109, 98, 109, 10080, 168, 108, 54720, 14280),
    HEAD_13("머리 방어구", 13, 1513, 116, 104, 116, 11160, 192, 108, 59040, 15840),
    HEAD_14("머리 방어구", 14, 1565, 119, 106, 117,14280, 252, 154, 73920, 20440),
    HEAD_15("머리 방어구", 15, 1620, 120, 108, 120, 16380, 280, 154, 78960, 22400),
    HEAD_16("머리 방어구", 16, 1676, 123, 110, 122, 23760, 396, 270, 129600, 31680),
    HEAD_17("머리 방어구", 17, 1736, 125, 111, 124, 26460, 414, 270, 139320, 34740),
    HEAD_18("머리 방어구", 18, 1796, 127, 114, 126, 29160, 450, 270, 147960, 37980),
    HEAD_19("머리 방어구", 19, 1859, 129, 115, 128, 58410, 891, 693, 316800, 75900),
    HEAD_20("머리 방어구", 20, 1924, 132, 117, 130, 63360, 957, 693, 338580, 82500),
    HEAD_21("머리 방어구", 21, 1991, 134, 120, 133, 106560, 1488, 1008, 524160, 130080),
    HEAD_22("머리 방어구", 22, 2061, 136, 121, 135, 115200, 1632, 1008, 552960, 140160),
    HEAD_23("머리 방어구", 23, 2133, 139, 124, 138, 231840, 3312, 2760, 1126080, 289800),
    HEAD_24("머리 방어구", 24, 2208, 142, 126, 139, 248400, 3680, 2760, 1186800, 311880),
    HEAD_25("머리 방어구", 25, 2285, 144, 128, 142, 0, 0, 0, 0, 0),
    SHOULDER_10("어깨 방어구", 10, 0, 0, 0, 0, 5250, 77, 49,21000, 6790),
    SHOULDER_11("어깨 방어구", 11, 1444, 93, 107, 96, 5560, 91, 49, 25440, 7490),
    SHOULDER_12("어깨 방어구", 12, 1491, 95, 109, 98, 10080, 168, 108, 54720, 14280),
    SHOULDER_13("어깨 방어구", 13, 1610, 101, 116, 104, 11160, 192, 108, 59040, 15840),
    SHOULDER_14("어깨 방어구", 14, 1666, 102, 117, 106,14280, 252, 154, 73920, 20440),
    SHOULDER_15("어깨 방어구", 15, 1724, 104, 120, 108, 16380, 280, 154, 78960, 22400),
    SHOULDER_16("어깨 방어구", 16, 1784, 107, 122, 110, 23760, 396, 270, 129600, 31680),
    SHOULDER_17("어깨 방어구", 17, 1847, 108, 124, 111, 26460, 414, 270, 139320, 34740),
    SHOULDER_18("어깨 방어구", 18, 1912, 110, 126, 114, 29160, 450, 270, 147960, 37980),
    SHOULDER_19("어깨 방어구", 19, 1978, 112, 128, 115, 58410, 891, 693, 316800, 75900),
    SHOULDER_20("어깨 방어구", 20, 2048, 114, 130, 117, 63360, 957, 693, 338580, 82500),
    SHOULDER_21("어깨 방어구", 21, 2119, 117, 133, 120, 106560, 1488, 1008, 524160, 130080),
    SHOULDER_22("어깨 방어구", 22, 2194, 118, 135, 121, 115200, 1632, 1008, 552960, 140160),
    SHOULDER_23("어깨 방어구", 23, 2270, 120, 138, 124, 231840, 3312, 2760, 1126080, 289800),
    SHOULDER_24("어깨 방어구", 24, 2350, 123, 139, 126, 248400, 3680, 2760, 1186800, 311880),
    SHOULDER_25("어깨 방어구", 25, 2432, 125, 142, 128, 0, 0, 0, 0, 0),
    CHEST_10("상의 방어구", 10, 0, 0, 0, 0, 5250, 77, 49,21000, 6790),
    CHEST_11("상의 방어구", 11, 1085, 143, 128, 118, 5560, 91, 49, 25440, 7490),
    CHEST_12("상의 방어구", 12, 1121, 146, 131, 120, 10080, 168, 108, 54720, 14280),
    CHEST_13("상의 방어구", 13, 1210, 155, 139, 127, 11160, 192, 108, 59040, 15840),
    CHEST_14("상의 방어구", 14, 1252, 158, 141, 129, 4280, 252, 154, 73920, 20440),
    CHEST_15("상의 방어구", 15, 1296, 161, 144, 132, 16380, 280, 154, 78960, 22400),
    CHEST_16("상의 방어구", 16, 1342, 163, 146, 134, 23760, 396, 270, 129600, 31680),
    CHEST_17("상의 방어구", 17, 1388, 167, 149, 136, 26460, 414, 270, 139320, 34740),
    CHEST_18("상의 방어구", 18, 1437, 169, 151, 139, 29160, 450, 270, 147960, 37980),
    CHEST_19("상의 방어구", 19, 1487, 173, 154, 141, 58410, 891, 693, 316800, 75900),
    CHEST_20("상의 방어구", 20, 1539, 175, 156, 143, 63360, 957, 693, 338580, 82500),
    CHEST_21("상의 방어구", 21, 1593, 179, 160, 146, 106560, 1488, 1008, 524160, 130080),
    CHEST_22("상의 방어구", 22, 1649, 182, 162, 149, 115200, 1632, 1008, 552960, 140160),
    CHEST_23("상의 방어구", 23, 1707, 185, 165, 151, 231840, 3312, 2760, 1126080, 289800),
    CHEST_24("상의 방어구", 24, 1766, 189, 167, 154, 248400, 3680, 2760, 1186800, 311880),
    CHEST_25("상의 방어구", 25, 1828, 192, 171, 156, 0, 0, 0, 0, 0),
    PANTS_10("하의 방어구", 10, 0, 0, 0, 0, 5250, 77, 49,21000, 6790),
    PANTS_11("하의 방어구", 11, 1172, 122, 118, 128, 5560, 91, 49, 25440, 7490),
    PANTS_12("하의 방어구", 12, 1211, 124, 120, 131, 10080, 168, 108, 54720, 14280),
    PANTS_13("하의 방어구", 13, 1307, 132, 127, 139, 11160, 192, 108, 59040, 15840),
    PANTS_14("하의 방어구", 14, 1353, 134, 129, 141,14280, 252, 154, 73920, 20440),
    PANTS_15("하의 방어구", 15, 1400, 137, 132, 144, 16380, 280, 154, 78960, 22400),
    PANTS_16("하의 방어구", 16, 1449, 139, 134, 146, 23760, 396, 270, 129600, 31680),
    PANTS_17("하의 방어구", 17, 1500, 141, 136, 149, 26460, 414, 270, 139320, 34740),
    PANTS_18("하의 방어구", 18, 1552, 144, 139, 151, 29160, 450, 270, 147960, 37980),
    PANTS_19("하의 방어구", 19, 1607, 147, 141, 154, 58410, 891, 693, 316800, 75900),
    PANTS_20("하의 방어구", 20, 1663, 149, 143, 156, 63360, 957, 693, 338580, 82500),
    PANTS_21("하의 방어구", 21, 1721, 152, 146, 160, 106560, 1488, 1008, 524160, 130080),
    PANTS_22("하의 방어구", 22, 1781, 155, 149, 162, 115200, 1632, 1008, 552960, 140160),
    PANTS_23("하의 방어구", 23, 1844, 157, 151, 165, 231840, 3312, 2760, 1126080, 289800),
    PANTS_24("하의 방어구", 24, 1908, 161, 154, 167, 248400, 3680, 2760, 1186800, 311880),
    PANTS_25("하의 방어구", 25, 1978, 163, 156, 171, 0, 0, 0, 0, 0),
    GLOVES_10("장갑 방어구", 10, 0, 0, 0, 0, 5250, 77, 49,21000, 6790),
    GLOVES_11("장갑 방어구", 11, 1627, 72, 85, 85, 5560, 91, 49, 25440, 7490),
    GLOVES_12("장갑 방어구", 12, 1682, 73, 88, 88, 10080, 168, 108, 54720, 14280),
    GLOVES_13("장갑 방어구", 13, 1815, 77, 92, 92, 11160, 192, 108, 59040, 15840),
    GLOVES_14("장갑 방어구", 14, 1878, 79, 94, 94,14280, 252, 154, 73920, 20440),
    GLOVES_15("장갑 방어구", 15, 1944, 81, 96, 96, 16380, 280, 154, 78960, 22400),
    GLOVES_16("장갑 방어구", 16, 2012, 81, 98, 98, 23760, 396, 270, 129600, 31680),
    GLOVES_17("장갑 방어구", 17, 2083, 84, 99, 99, 26460, 414, 270, 139320, 34740),
    GLOVES_18("장갑 방어구", 18, 2155, 84, 100, 100, 29160, 450, 270, 147960, 37980),
    GLOVES_19("장갑 방어구", 19, 2231, 87, 103, 103, 58410, 891, 693, 316800, 75900),
    GLOVES_20("장갑 방어구", 20, 2308, 87, 104, 104, 63360, 957, 693, 338580, 82500),
    GLOVES_21("장갑 방어구", 21, 2390, 90, 107, 107, 106560, 1488, 1008, 524160, 130080),
    GLOVES_22("장갑 방어구", 22, 2473, 91, 108, 108, 115200, 1632, 1008, 552960, 140160),
    GLOVES_23("장갑 방어구", 23, 2560, 92, 110, 110, 231840, 3312, 2760, 1126080, 289800),
    GLOVES_24("장갑 방어구", 24, 2649, 95, 111, 111, 248400, 3680, 2760, 1186800, 311880),
    GLOVES_25("장갑 방어구", 25, 2743, 96, 114, 114, 0, 0, 0, 0, 0),
    ;

    private final String partName;
    private final int lvl;
    private final int mainStat; // 주 능력치
    private final int vitality; // 체력
    private final int phyDef; // 물리 방어력
    private final int magDef; // 마법 방어력
    private final int guardStone; // 수호강석
    private final int leapStone; // 돌파석
    private final int fusionStone; // 아비도스
    private final int fragment; // 운명의 파편
    private final int gold; // 골드

    T4ArmorHone(String partName, int lvl, int mainStat, int vitality, int phyDef, int magDef, int guardStone, int leapStone, int fusionStone, int fragment, int gold) {
        this.partName = partName;
        this.lvl = lvl;
        this.mainStat = mainStat;
        this.vitality = vitality;
        this.phyDef = phyDef;
        this.magDef = magDef;
        this.guardStone = guardStone;
        this.leapStone = leapStone;
        this.fusionStone = fusionStone;
        this.fragment = fragment;
        this.gold = gold;
    }

    public static T4ArmorHone of(String partName, int targetLvl) {
        return Arrays.stream(values())
                .filter(value -> (value.lvl == targetLvl)&&(value.partName.equals(partName)))
                .findFirst()
                .orElse(null);
    }

    public static T4ArmorIncrement findIncrementByTargetLevel(String partName, int targetLvl) {
        T4ArmorHone target = T4ArmorHone.of(partName, targetLvl);
        if (target == null) {
            return null;
        }
        return new T4ArmorIncrement(target.mainStat, target.vitality, target.phyDef, target.magDef);
    }

    public static double findCostByTargetLevel(String partName, int targetLvl, Boolean isFragmentBound) {
        T4ArmorHone target = T4ArmorHone.of(partName, targetLvl);
        if (target == null) {
            return 0;
        }

        return HoneIngredients.getTotalCostByType(partName, isFragmentBound, target.guardStone, target.leapStone, target.fusionStone, target.fragment, target.gold);
    }
}
