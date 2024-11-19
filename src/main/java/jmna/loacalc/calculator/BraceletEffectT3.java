package jmna.loacalc.calculator;

import jmna.loacalc.calculator.subequipments.BraceletEffect;
import jmna.loacalc.processor.armory.equipment.accessory.BraceletData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BraceletEffectT3 {

    //TODO 순환 효과 재점검 필요
    CIRCULATE(Map.of(3.0, "H", 3.5, "E", 4.0, "L"), "순환") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addOutgoingDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    HAMMER(Map.of(8.0, "H", 10.0, "E", 12.0, "L"), "망치") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {

            braceletEffect.addOutgoingDmg(Double.parseDouble(braceletData.getEffect()));

        }
    },
    FERVOR(Map.of(3.0, "H", 3.5, "E", 4.0, "L"), "열정") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {

            braceletEffect.addOutgoingDmg(Double.parseDouble(braceletData.getEffect()));

        }
    },
    AMBUSH(Map.of(3.0, "H", 3.5, "E", 4.0, "L"), "기습") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {

            braceletEffect.setBackDmg(Double.parseDouble(braceletData.getEffect()));

        }
    },
    BATTLE(Map.of(3.0, "H", 3.5, "E", 4.0, "L"), "결투") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {

            braceletEffect.setHeadDmg(Double.parseDouble(braceletData.getEffect()));

        }
    },
    PRECISE(Map.of(3.0, "H", 4.0, "E", 5.0, "L"), "정밀") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addCrtRate(Double.parseDouble(braceletData.getEffect()));

        }
    },
    EXPOSE(Map.of(1.8, "H", 2.1, "E", 2.5, "L"), "약점 노출") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.setCritRateSynergy(Double.parseDouble(braceletData.getEffect()));
        }
    },
    ENLIGHTEN(Map.of(4.0, "H", 5.0, "E", 6.0, "L"), "깨달음") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.setIdentityGain(Double.parseDouble(braceletData.getEffect()));
        }
    },
    CHEER(Map.of(0.9, "H", 1.1, "E", 1.3, "L"), "응원") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.setOutgoingDmgSynergy(Double.parseDouble(braceletData.getEffect()));
        }
    },
    DAGGER(Map.of(1.8, "H", 2.1, "E", 2.5, "L"), "비수") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.setArmorReductionSynergy(Double.parseDouble(braceletData.getEffect()));
        }
    },
    ASSAIL(Map.of(6.0, "H", 8.0, "E", 10.0, "L"), "습격") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addCritDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    SUPERIOR(Map.of(2.0, "H", 2.5, "E", 3.0, "L"), "우월") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addOutgoingDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    WEDGE(Map.of(2.8, "H", 3.6, "E", 4.0, "L"), "쐐기") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.setAddDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    SAWTOOTH(Map.of(3.0, "H", 5.0, "E", 7.0, "L"), "상처악화") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addCritDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    HARVEST(Map.of(1900.0, "H", 2200.0, "E", 2500.0, "L"), "수확") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addWeaponPower(Integer.parseInt(braceletData.getEffect()));
        }
    },
    COMPENSATION(Map.of(1.0, "H", 1.5, "E", 2.0, "L"), "적립") {
        @Override
        void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
            braceletEffect.addCritDmg(Double.parseDouble(braceletData.getEffect()));
        }
    },
    ;

    private final String[] name;
    private final Map<Double, String> effectGrade = new HashMap<>();

    abstract void applyEffect(BraceletData braceletData, BraceletEffect braceletEffect);

    BraceletEffectT3(Map<Double, String> effectGrade, String... name) {
        this.name = name;
        this.effectGrade.putAll(effectGrade);
    }

    public static BraceletEffectT3 of(String name) {
        return Arrays.stream(values())
                .filter(value -> Arrays.asList(value.name).contains(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static void applyBraceletHiddenEffect(BraceletData braceletData, BraceletEffect braceletEffect) {
        BraceletEffectT3 target = of(braceletData.getName());
        target.applyEffect(braceletData, braceletEffect);
    }

    public static String findGradeByNameAndEffect(String name, String effect) {
        BraceletEffectT3 target = of(name);
        return target.effectGrade.get(Double.parseDouble(effect));
    }
}
