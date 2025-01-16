package jmna.loacalc.calculator.engraving;

import jmna.loacalc.processor.armory.engraving.CharacterEngraving;

import java.util.Arrays;

public enum EngravingAbilityStone {

    AWAKENING(6, 1.5, "각성"),
    AMBUSH_MASTER(2.7, 0.675, "기습의 대가", "결투의 대가"),
    MASTER_TENACITY(3, 0.75,
            "원한", "저주받은 인형", "타격의 대가", "안정된 상태", "질량 증가", "추진력", "정기 흡수",
            "정밀 단도", "속전속결", "바리케이드", "달인의 저력", "슈퍼 차지", "마나 효율 증가", "에테르 포식자"),
    RAID_CAPTAIN(7.5, 1.875, "돌격대장", "예리한 둔기"),
    MAGICK_STREAM(0.6, 0.15, "마나의 흐름"),
    ADRENALINE(2.88, 0.705, "아드레날린"),
    EXPERT(4, 0.1, "전문의"),
    HEAVY_ARMOR(12, 3, "중갑 착용")
    ;


    private final String[] name;
    private final double base;
    private final double increment;
    private static final int[] mValues = {0, 0, 1, 3, 4};

    EngravingAbilityStone(double base, double increment, String... name) {
        this.name = name;
        this.base = base;
        this.increment = increment;
    }

    private static EngravingAbilityStone of(String name) {
        return Arrays.stream(values())
                .filter(value -> Arrays.asList(value.name).contains(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static double getIncrementValue(CharacterEngraving characterEngraving) {
        EngravingAbilityStone target = of(characterEngraving.getName());
        return target.base + target.increment * mValues[characterEngraving.getAbilityStoneLvl()];
    }
}
