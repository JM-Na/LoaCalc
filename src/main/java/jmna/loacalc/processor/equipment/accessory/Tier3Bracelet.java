package jmna.loacalc.processor.equipment.accessory;

import java.util.Arrays;
import java.util.Objects;

public enum Tier3Bracelet {

    // H: HEROIC(하), E: EPIC(중), L: LEGENDARY(상)
    CIRCULATE_H("순환", "3%", "H"), CIRCULATE_E("순환", "3.5%", "E"), CIRCULATE_L("순환", "4%", "L"),
    HAMMER_H("망치", "8%", "H"), HAMMER_E("망치", "10%", "E"), HAMMER_L("망치", "12%", "L"),
    FERVOR_H("열정", "3%", "H"), FERVOR_E("열정", "3.5%", "E"), FERVOR_L("열정", "4%", "L"),
    COOL_H("냉정", "3%", "H"), COOL_E("냉정", "3.5%", "E"), COOL_L("냉정", "4%", "L"),
    AMBUSH_H("기습", "3%", "H"), AMBUSH_E("기습", "3.5%", "E"), AMBUSH_L("기습", "4%", "L"),
    BATTLE_H("결투", "3%", "H"), BATTLE_E("결투", "3.5%", "E"), BATTLE_L("결투", "4%", "L"),
    PRECISE_H("정밀", "3%", "H"), PRECISE_E("정밀", "4%", "E"), PRECISE_L("정밀", "5%", "L"),
    EXPOSE_H("약점 노출", "1.8%", "H"), EXPOSE_E("약점 노출", "2.1%", "E"), EXPOSE_L("약점 노출", "2.5%", "L"),
    ENLIGHT_H("깨달음", "4%", "H"), ENLIGHT_E("깨달음", "5%", "E"), ENLIGHT_L("깨달음", "6%", "L"),
    CHEER_H("응원", "0.9%", "H"), CHEER_E("응원", "1.1%", "E"), CHEER_L("응원", "1.3%", "L"),
    DAGGER_H("비수", "1.8%", "H"), DAGGER_E("비수", "2.1%", "E"), DAGGER_L("비수", "2.5%", "L"),
    ASSAIL_H("습격", "6%", "H"), ASSAIL_E("습격", "8%", "E"), ASSAIL_L("습격", "10%", "L"),
    SUPERIOR_H("우월", "2%", "H"), SUPERIOR_E("우월", "2.5%", "E"), SUPERIOR_L("우월", "3%", "L"),
    WEDGE_H("쐐기", "0.35%", "H"), WEDGE_E("쐐기", "0.45%", "E"), WEDGE_L("쐐기", "0.5%", "L"),
    SAWTOOTH_H("상처악화", "3%", "H"), SAWTOOTH_E("상처악화", "5%", "E"), SAWTOOTH_L("상처악화", "7%", "L"),
    HARVEST_H("수확", "190", "H"), HARVEST_E("수확", "220", "E"), HARVEST_L("수확", "250", "L"),
    COMPENSATION_H("적립", "30", "H"), COMPENSATION_E("적립", "40", "E"), COMPENSATION_L("적립", "50", "L");


    private final String name;
    private final String effect;
    private final String grade;

    Tier3Bracelet(String name, String effect, String grade) {
        this.name = name;
        this.effect = effect;
        this.grade = grade;
    }

    public static Tier3Bracelet of(String name, String effect) {
        return Arrays.stream(values())
                .filter(value -> Objects.equals(value.name, name) && Objects.equals(value.effect, effect))
                .findFirst()
                .orElse(null);
    }

    public static String findGradeByNameAndEffect(String name, String effect) {
        Tier3Bracelet bracelet = Tier3Bracelet.of(name, effect);
        if (bracelet == null) {
            return null;
        }
        return bracelet.grade;
    }
}
