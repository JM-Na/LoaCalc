package jmna.loacalc.calculator.hone;

import java.util.Arrays;
import java.util.List;

public enum T4ArmorHone {
    HEAD_10("머리", 10, 0, 0, 0, 0, 0, 0, 0,0, 0),
    HEAD_11("머리", 11, 1356, 108, 96, 107, 5250, 77, 49,21000, 6790),
    HEAD_12("머리", 12, 1401, 109, 98, 109, 5560, 91, 49, 25440, 7490),
    HEAD_13("머리", 13, 1513, 116, 104, 116, 10080, 168, 108, 54720, 14280),
    HEAD_14("머리", 14, 1565, 119, 106, 117,11160, 192, 108, 59040, 15840),
    HEAD_15("머리", 15, 1620, 120, 108, 120, 14280, 252, 154, 73920, 20440),
    HEAD_16("머리", 16, 1676, 123, 110, 122, 16380, 280, 154, 78960, 22400),
    HEAD_17("머리", 17, 1736, 125, 111, 124, 23760, 396, 270, 129600, 31680),
    HEAD_18("머리", 18, 1796, 127, 114, 126, 26460, 414, 270, 139320, 34740),
    HEAD_19("머리", 19, 1859, 129, 115, 128, 29160, 450, 270, 147960, 37980),
    HEAD_20("머리", 20, 1924, 132, 117, 130, 58410, 891, 693, 316800, 75900),
    HEAD_21("머리", 21, 1991, 134, 120, 133, 63360, 957, 693, 338580, 82500),
    HEAD_22("머리", 22, 2061, 136, 121, 135, 106560, 1488, 1008, 524160, 130080),
    HEAD_23("머리", 23, 2133, 139, 124, 138, 115200, 1632, 1008, 552960, 140160),
    HEAD_24("머리", 24, 2208, 142, 126, 139, 231840, 3312, 2760, 1126080, 289800),
    HEAD_25("머리", 25, 2285, 144, 128, 142, 248400, 3680, 2760, 1186800, 311880),
    SHOULDER_10("어깨", 10, 0, 0, 0, 0, 0, 0, 0,0, 0),
    SHOULDER_11("어깨", 11, 1444, 93, 107, 96, 5250, 77, 49,21000, 6790),
    SHOULDER_12("어깨", 12, 1491, 95, 109, 98, 5560, 91, 49, 25440, 7490),
    SHOULDER_13("어깨", 13, 1610, 101, 116, 104, 10080, 168, 108, 54720, 14280),
    SHOULDER_14("어깨", 14, 1666, 102, 117, 106,11160, 192, 108, 59040, 15840),
    SHOULDER_15("어깨", 15, 1724, 104, 120, 108, 14280, 252, 154, 73920, 20440),
    SHOULDER_16("어깨", 16, 1784, 107, 122, 110, 16380, 280, 154, 78960, 22400),
    SHOULDER_17("어깨", 17, 1847, 108, 124, 111, 23760, 396, 270, 129600, 31680),
    SHOULDER_18("어깨", 18, 1912, 110, 126, 114, 26460, 414, 270, 139320, 34740),
    SHOULDER_19("어깨", 19, 1978, 112, 128, 115, 29160, 450, 270, 147960, 37980),
    SHOULDER_20("어깨", 20, 2048, 114, 130, 117, 58410, 891, 693, 316800, 75900),
    SHOULDER_21("어깨", 21, 2119, 117, 133, 120, 63360, 957, 693, 338580, 82500),
    SHOULDER_22("어깨", 22, 2194, 118, 135, 121, 106560, 1488, 1008, 524160, 130080),
    SHOULDER_23("어깨", 23, 2270, 120, 138, 124, 115200, 1632, 1008, 552960, 140160),
    SHOULDER_24("어깨", 24, 2350, 123, 139, 126, 231840, 3312, 2760, 1126080, 289800),
    SHOULDER_25("어깨", 25, 2432, 125, 142, 128, 248400, 3680, 2760, 1186800, 311880),
    CHEST_10("상의", 10, 0, 0, 0, 0, 0, 0, 0,0, 0),
    CHEST_11("상의", 11, 1085, 143, 128, 118, 5250, 77, 49,21000, 6790),
    CHEST_12("상의", 12, 1121, 146, 131, 120, 5560, 91, 49, 25440, 7490),
    CHEST_13("상의", 13, 1210, 155, 139, 127, 10080, 168, 108, 54720, 14280),
    CHEST_14("상의", 14, 1252, 158, 141, 129, 11160, 192, 108, 59040, 15840),
    CHEST_15("상의", 15, 1296, 161, 144, 132, 14280, 252, 154, 73920, 20440),
    CHEST_16("상의", 16, 1342, 163, 146, 134, 16380, 280, 154, 78960, 22400),
    CHEST_17("상의", 17, 1388, 167, 149, 136, 23760, 396, 270, 129600, 31680),
    CHEST_18("상의", 18, 1437, 169, 151, 139, 26460, 414, 270, 139320, 34740),
    CHEST_19("상의", 19, 1487, 173, 154, 141, 29160, 450, 270, 147960, 37980),
    CHEST_20("상의", 20, 1539, 175, 156, 143, 58410, 891, 693, 316800, 75900),
    CHEST_21("상의", 21, 1593, 179, 160, 146, 63360, 957, 693, 338580, 82500),
    CHEST_22("상의", 22, 1649, 182, 162, 149, 106560, 1488, 1008, 524160, 130080),
    CHEST_23("상의", 23, 1707, 185, 165, 151, 115200, 1632, 1008, 552960, 140160),
    CHEST_24("상의", 24, 1766, 189, 167, 154, 231840, 3312, 2760, 1126080, 289800),
    CHEST_25("상의", 25, 1828, 192, 171, 156, 248400, 3680, 2760, 1186800, 311880),
    PANTS_10("하의", 10, 0, 0, 0, 0, 0, 0, 0,0, 0),
    PANTS_11("하의", 11, 1172, 122, 118, 128, 5250, 77, 49,21000, 6790),
    PANTS_12("하의", 12, 1211, 124, 120, 131, 5560, 91, 49, 25440, 7490),
    PANTS_13("하의", 13, 1307, 132, 127, 139, 10080, 168, 108, 54720, 14280),
    PANTS_14("하의", 14, 1353, 134, 129, 141,11160, 192, 108, 59040, 15840),
    PANTS_15("하의", 15, 1400, 137, 132, 144, 14280, 252, 154, 73920, 20440),
    PANTS_16("하의", 16, 1449, 139, 134, 146, 16380, 280, 154, 78960, 22400),
    PANTS_17("하의", 17, 1500, 141, 136, 149, 23760, 396, 270, 129600, 31680),
    PANTS_18("하의", 18, 1552, 144, 139, 151, 26460, 414, 270, 139320, 34740),
    PANTS_19("하의", 19, 1607, 147, 141, 154, 29160, 450, 270, 147960, 37980),
    PANTS_20("하의", 20, 1663, 149, 143, 156, 58410, 891, 693, 316800, 75900),
    PANTS_21("하의", 21, 1721, 152, 146, 160, 63360, 957, 693, 338580, 82500),
    PANTS_22("하의", 22, 1781, 155, 149, 162, 106560, 1488, 1008, 524160, 130080),
    PANTS_23("하의", 23, 1844, 157, 151, 165, 115200, 1632, 1008, 552960, 140160),
    PANTS_24("하의", 24, 1908, 161, 154, 167, 231840, 3312, 2760, 1126080, 289800),
    PANTS_25("하의", 25, 1978, 163, 156, 171, 248400, 3680, 2760, 1186800, 311880),
    GLOVES_10("장갑", 10, 0, 0, 0, 0, 0, 0, 0,0, 0),
    GLOVES_11("장갑", 11, 1627, 72, 85, 85, 5250, 77, 49,21000, 6790),
    GLOVES_12("장갑", 12, 1682, 73, 88, 88, 5560, 91, 49, 25440, 7490),
    GLOVES_13("장갑", 13, 1815, 77, 92, 92, 10080, 168, 108, 54720, 14280),
    GLOVES_14("장갑", 14, 1878, 79, 94, 94,11160, 192, 108, 59040, 15840),
    GLOVES_15("장갑", 15, 1944, 81, 96, 96, 14280, 252, 154, 73920, 20440),
    GLOVES_16("장갑", 16, 2012, 81, 98, 98, 16380, 280, 154, 78960, 22400),
    GLOVES_17("장갑", 17, 2083, 84, 99, 99, 23760, 396, 270, 129600, 31680),
    GLOVES_18("장갑", 18, 2155, 84, 100, 100, 26460, 414, 270, 139320, 34740),
    GLOVES_19("장갑", 19, 2231, 87, 103, 103, 29160, 450, 270, 147960, 37980),
    GLOVES_20("장갑", 20, 2308, 87, 104, 104, 58410, 891, 693, 316800, 75900),
    GLOVES_21("장갑", 21, 2390, 90, 107, 107, 63360, 957, 693, 338580, 82500),
    GLOVES_22("장갑", 22, 2473, 91, 108, 108, 106560, 1488, 1008, 524160, 130080),
    GLOVES_23("장갑", 23, 2560, 92, 110, 110, 115200, 1632, 1008, 552960, 140160),
    GLOVES_24("장갑", 24, 2649, 95, 111, 111, 231840, 3312, 2760, 1126080, 289800),
    GLOVES_25("장갑", 25, 2743, 96, 114, 114, 248400, 3680, 2760, 1186800, 311880),
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

    public static int findIncrementByTargetLevel(String partName, int targetLvl) {
        T4ArmorHone target = T4ArmorHone.of(partName, targetLvl);
        return target.mainStat;
    }

    public static double findTotalIncrementByTargetLevel(List<String> typeList, int targetLvl) {
        double sum = 0;

        for (String type : typeList) {
            sum += T4ArmorHone.findIncrementByTargetLevel(type, targetLvl);
        }

        return sum;
    }

    public static double findCostByTargetLevel(String partName, int targetLvl, Boolean isFragmentBound) {
        T4ArmorHone target = T4ArmorHone.of(partName, targetLvl);
        if (target == null) {
            return 0;
        }

        return HoneIngredientsData.getTotalCostByType(partName, isFragmentBound, target.guardStone, target.leapStone, target.fusionStone, target.fragment, target.gold);
    }



    public static double findTotalCostByTargetLevel(List<String> typeList, int targetLvl, Boolean isFragmentBound) {

        double sum = 0;

        for (String type : typeList) {
            sum += findCostByTargetLevel(type, targetLvl, isFragmentBound);
        }

        return sum;
    }
}
