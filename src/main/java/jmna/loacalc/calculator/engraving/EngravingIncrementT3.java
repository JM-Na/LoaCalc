package jmna.loacalc.calculator.engraving;

import jmna.loacalc.processor.engraving.CharacterEngraving;

import java.util.Arrays;

public enum EngravingIncrementT3 {

    AWAKENING("각성") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.setAwakeningCooldown(10);
                    engravingEffect.setAwakeningCast(1);
                }
                case 2 -> {
                    engravingEffect.setAwakeningCooldown(25);
                    engravingEffect.setAwakeningCast(2);
                }
                case 3 -> {
                    engravingEffect.setAwakeningCooldown(50);
                    engravingEffect.setAwakeningCast(3);
                }
            }
        }
    },
    AMBUSH_MASTER("결투의 대가", "기습의 대가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.addOutgoingDmg(1);
                    engravingEffect.setAddBackHeadDmg(3);
                }
                case 2 -> {
                    engravingEffect.addOutgoingDmg(3);
                    engravingEffect.setAddBackHeadDmg(7);
                }
                case 3 -> {
                    engravingEffect.addOutgoingDmg(7);
                    engravingEffect.setAddBackHeadDmg(15);
                }
            }
        }
    },
    MASTER_TENACITY("달인의 저력", "바리케이드", "안정된 상태", "추진력", "저주받은 인형") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            if (name.equals("달인의 저력"))
                engravingEffect.setMasterTenacity(true);
            else if (name.equals("저주받은 인형"))
                engravingEffect.setCursedDoll(true);
            switch (lvl) {
                case 1 -> engravingEffect.addOutgoingDmg(3);
                case 2 -> engravingEffect.addOutgoingDmg(8);
                case 3 -> engravingEffect.addOutgoingDmg(16);
            }
        }
    },
    RAID_CAPTAIN("돌격대장") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> engravingEffect.setRaidCaptain(10);
                case 2 -> engravingEffect.setRaidCaptain(22);
                case 3 -> engravingEffect.setRaidCaptain(45);
            }
        }
    },
    MAGICK_STREAM("마나의 흐름") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.setMpRecovery(0.5);
                    engravingEffect.setCooldownReduction(2);
                }
                case 2 -> {
                    engravingEffect.setMpRecovery(1.2);
                    engravingEffect.setCooldownReduction(5);
                }
                case 3 -> {
                    engravingEffect.setMpRecovery(2.4);
                    engravingEffect.setCooldownReduction(8);
                }
            }
        }
    },
    MANA_EFFICIENCY("마나 효율 증가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.setMpEfficiency(true);
            if (lvl == 1) {
                engravingEffect.setCooldownReduction(4);
                engravingEffect.addOutgoingDmg(3);
            } else if (lvl == 2) {
                engravingEffect.setCooldownReduction(10);
                engravingEffect.addOutgoingDmg(7);
            } else if (lvl == 3) {
                engravingEffect.setCooldownReduction(20);
                engravingEffect.addOutgoingDmg(15);
            }
        }
    },
    ALL_OUT_ATTACK("속전속결") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.setHoldCastSpeed(5);
                    engravingEffect.addOutgoingDmg(4);
                }
                case 2 -> {
                    engravingEffect.setHoldCastSpeed(10);
                    engravingEffect.addOutgoingDmg(10);
                }
                case 3 -> {
                    engravingEffect.setHoldCastSpeed(20);
                    engravingEffect.addOutgoingDmg(20);
                }
            }
        }
    },
    SUPER_CHARGE("슈퍼 차지") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.setChargeSpeed(8);
                    engravingEffect.addOutgoingDmg(4);
                }
                case 2 -> {
                    engravingEffect.setChargeSpeed(20);
                    engravingEffect.addOutgoingDmg(10);
                }
                case 3 -> {
                    engravingEffect.setChargeSpeed(40);
                    engravingEffect.addOutgoingDmg(20);
                }
            }
        }
    },
    ADRENALINE("아드레날린") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.addAtkPowerPercent(1.8);
                    engravingEffect.addCritRate(5);
                }
                case 2 -> {
                    engravingEffect.addAtkPowerPercent(3.6);
                    engravingEffect.addCritRate(10);
                }
                case 3 -> {
                    engravingEffect.addAtkPowerPercent(6.0);
                    engravingEffect.addCritRate(15);
                }
            }
        }
    },
    ETHER_PREDATOR("에테르 포식자") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.addAtkPowerPercent(6);
                    engravingEffect.addDefensePercent(9);
                }
                case 2 -> {
                    engravingEffect.addAtkPowerPercent(9);
                    engravingEffect.addDefensePercent(18);
                }
                case 3 -> {
                    engravingEffect.addAtkPowerPercent(15);
                    engravingEffect.addDefensePercent(30);
                }
            }
        }
    },
    KEEN_BLUNT_WEAPON("예리한 둔기") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.setKeenBluntWeapon(true);
            switch (lvl) {
                case 1 -> engravingEffect.addCritDmg(10);
                case 2 -> engravingEffect.addCritDmg(25);
                case 3 -> engravingEffect.addCritDmg(50);
            }
        }
    },
    GRUDGE("원한") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.setGrudge(true);

            switch (lvl) {
                case 1 -> engravingEffect.addOutgoingDmg(4);
                case 2 -> engravingEffect.addOutgoingDmg(10);
                case 3 -> engravingEffect.addOutgoingDmg(20);
            }
        }
    },
    EXPERT("전문의") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> engravingEffect.setHealShieldEfficiency(6);
                case 2 -> engravingEffect.setHealShieldEfficiency(14);
                case 3 -> engravingEffect.setHealShieldEfficiency(24);
            }
        }
    },
    SPIRIT_ABSORPTION("정기 흡수") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    engravingEffect.addAtkSpeed(3);
                    engravingEffect.addMovementSpeed(3);
                }
                case 2 -> {
                    engravingEffect.addAtkSpeed(8);
                    engravingEffect.addMovementSpeed(8);
                }
                case 3 -> {
                    engravingEffect.addAtkSpeed(15);
                    engravingEffect.addMovementSpeed(15);
                }
            }
        }
    },
    PRECISE_DAGGER("정밀 단도") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.addCritDmg(-6);
            switch (lvl) {
                case 1 -> engravingEffect.addCritRate(4);
                case 2 -> engravingEffect.addCritRate(10);
                case 3 -> engravingEffect.addCritRate(20);
            }
        }
    },
    HEAVY_ARMOR("중갑 착용") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.setHeavyArmor(true);
            switch (lvl) {
                case 1 -> engravingEffect.addDefensePercent(20);
                case 2 -> engravingEffect.addDefensePercent(50);
                case 3 -> engravingEffect.addDefensePercent(100);
            }
        }
    },
    MASS_INCREASE("질량 증가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.addAtkSpeed(-10);
            switch (lvl) {
                case 1 -> engravingEffect.addOutgoingDmg(4);
                case 2 -> engravingEffect.addOutgoingDmg(10);
                case 3 -> engravingEffect.addOutgoingDmg(18);
            }
        }
    },
    HIT_MASTER("타격의 대가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, int lvl) {
            engravingEffect.setHitMaster(true);
            switch (lvl) {
                case 1 -> engravingEffect.addOutgoingDmg(3);
                case 2 -> engravingEffect.addOutgoingDmg(8);
                case 3 -> engravingEffect.addOutgoingDmg(16);
            }
        }
    },
    ;

    private final String[] name;

    EngravingIncrementT3(String... name) {
        this.name = name;
    }

    abstract void applyEffect(String name, EngravingEffect engravingEffect, int lvl);

    private static EngravingIncrementT3 of(String name) {
        return Arrays.stream(values())
                .filter(value -> Arrays.asList(value.name).contains(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static EngravingEffect applyEngravingEffect(CharacterEngraving characterEngraving) {
        EngravingIncrementT3 target = of(characterEngraving.getName());
        EngravingEffect engravingEffect = new EngravingEffect();
        target.applyEffect(characterEngraving.getName(), engravingEffect, characterEngraving.getLvl());
        return engravingEffect;
    }

}
