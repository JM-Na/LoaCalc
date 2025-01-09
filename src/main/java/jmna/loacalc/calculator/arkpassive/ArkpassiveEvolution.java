package jmna.loacalc.calculator.arkpassive;

import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ArkpassiveEvolution {
    CRIT(1, "치명") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setCrit(lvl * 50);
        }
    },
    SPECIALIZATION(1, "특화") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setSpecialization(lvl * 50);
        }
    },
    DOMINATION(1, "제압") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setDomination(lvl * 50);
        }
    },
    SWIFTNESS(1, "신속") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setSwiftness(lvl * 50);
        }
    },
    ENDURANCE(1, "인내") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setEndurance(lvl * 50);
        }
    },
    EXPERTISE(1, "숙련") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setExpertise(lvl * 50);
        }
    },
    BOUNDLESS_MANA(2, "끝없는 마나") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addManaSkillCooldown(lvl * 7);
            effect.addManaConsumption(lvl * 10);
        }
    },
    FORBIDDEN_SPELL(2, "금단의 주문") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 5);
            effect.addManaEvolutionDmg(lvl * 5);
            effect.addManaConsumption(lvl * 6);
        }
    },
    KEEN_SENSE(2, "예리한 감각") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 5);
            effect.addCritRate(lvl * 4);
        }
    },
    LIMIT_BREAKING(2, "한계 돌파") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 10);
        }
    },
    OPTIMIZATION_TRAINING(2, "최적화 훈련") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 5);
            effect.addSkillCooldown(lvl * 4);
        }
    },
    GODDESS_OF_BLESSING(2, "축복의 여신") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 3);
            effect.addMoveSpeed(lvl * 3);
        }
    },
    LIMITLESS_MAGICK(3, "무한한 마력") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 8);
            effect.addManaSkillCooldown(lvl * 7);
            effect.addManaConsumption(lvl * 8);
        }
    },
    FULL_FORCE_SMITE(3, "혼신의 강타") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addCritRate(lvl * 12);
            effect.addEvolutionDmg(lvl * 2);
        }
    },
    STRIKE(3, "일격") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addCritRate(lvl * 10);
            effect.addDirectionalSkillCritDmg(lvl * 16);
        }
    },
    JUGGERNAUT(3, "파괴전차") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 12);
            effect.addAtkSpeed(lvl * 4);
        }
    },
    TIME_DOMINATOR(3, "타이밍 지배") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addSkillCooldown(lvl * 5);
            effect.addEvolutionDmg(lvl * 8);
        }
    },
    DANCE_OF_PASSION(3, "정열의 춤") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addIdentityGain(lvl * 10);
            effect.addEvolutionDmg(lvl * 7);
        }
    },
    BLUNT_SPIKE(4, "뭉툭한 가시") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setBluntSpike(lvl);
            effect.addEvolutionDmg(lvl * 7.5);
        }
    },
    SONIC_BREAKTHROUGH(4, "음속 돌파") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setSonicBreakThrough(12);
        }
    },
    INFIGHTING(4, "인파이팅") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 9);
        }
    },
    STAND_UP_FIGHTING(4, "입식 타격가") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addEvolutionDmg(lvl * 10.5);
            effect.addBrandPower(lvl * 10);
        }
    },
    MANA_FORGE(4, "마나 용광로") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.setManaForge(lvl);
            effect.addBrandPower(lvl * 10);
        }
    },
    STABILITY(4, "안정된 관리자") {
        @Override
        void applyEffect(ArkpassiveEvolutionEffect effect, int lvl) {
            effect.addBrandPower(lvl * 10);
            effect.addIdentityGain(lvl * -3);
        }
    },
    ;

    private final int tier;
    private final String name;

    abstract void applyEffect(ArkpassiveEvolutionEffect effect, int lvl);

    ArkpassiveEvolution(int tier, String name) {
        this.tier = tier;
        this.name = name;
    }

    public static ArkpassiveEvolution of(String name) {
        return Arrays.stream(ArkpassiveEvolution.values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void applyEffect(String nodeName, int lvl, ArkpassiveEvolutionEffect effect) {
        ArkpassiveEvolution target = of(nodeName);
        target.applyEffect(effect, lvl);
    }
}
