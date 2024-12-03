package jmna.loacalc.calculator.engraving;

import jmna.loacalc.processor.armory.engraving.CharacterEngraving;

import java.util.Arrays;

public enum EngravingIncrementT4 {

    AWAKENING("각성") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.setAwakeningCooldown(38 + (1.5 * lvl) + incrementByStone);
                    engravingEffect.setAwakeningCast(1);
                }
                case "전설" -> {
                    engravingEffect.setAwakeningCooldown(44 + incrementByStone);
                    engravingEffect.setAwakeningCast(1 + (lvl % 2));
                }
                case "고대" -> {
                    engravingEffect.setAwakeningCooldown(44 + incrementByStone);
                    engravingEffect.setAwakeningCast(3 + (lvl % 2));
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();

            switch (grade) {
                case "영웅" -> {
                    increment.setName("Awakening Cooldown");
                    increment.setIncrement(1.5);
                }
                case "전설", "유물" -> {
                    increment.setName("Awakening Cast");
                    increment.setIncrement(2);
                }
            }

            return increment;
        }
    },
    AMBUSH_MASTER("결투의 대가", "기습의 대가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.addOutgoingDmg(3.2 + (0.2 * lvl) + incrementByStone);
                    engravingEffect.setAddBackHeadDmg(9 + (0.75 * lvl));
                }
                case "전설" -> {
                    engravingEffect.addOutgoingDmg(4 + (0.2 * lvl) + incrementByStone);
                    engravingEffect.setAddBackHeadDmg(12 + (0.75 * lvl));
                }
                case "유물" -> {
                    engravingEffect.addOutgoingDmg(4.8 + (0.7 * lvl) + incrementByStone);
                    engravingEffect.setAddBackHeadDmg(15);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");

            switch (grade) {
                case "영웅", "전설" -> increment.setIncrement(0.2);
                case "유물" -> increment.setIncrement(0.7);
            }

            return increment;
        }
    },
    MASTER_TENACITY("달인의 저력", "바리케이드", "안정된 상태", "추진력", "저주받은 인형") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            if (name.equals("달인의 저력"))
                engravingEffect.setMasterTenacity(true);
            else if (name.equals("저주받은 인형"))
                engravingEffect.setCursedDoll(true);
            switch (grade) {
                case "영웅" -> engravingEffect.addOutgoingDmg(8 + (0.75 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addOutgoingDmg(11 + (0.75 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addOutgoingDmg(14 + (0.75 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    RAID_CAPTAIN("돌격대장") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> engravingEffect.setRaidCaptain(24 + (2 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.setRaidCaptain(32 + (2 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.setRaidCaptain(40 + (2 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Raid Captain");
            increment.setIncrement(2);
            return increment;
        }

    },
    MAGICK_STREAM("마나의 흐름") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.setMpRecovery(1.4 + (0.15 * lvl) + incrementByStone);
                    engravingEffect.setCooldownReduction(4);
                }
                case "전설" -> {
                    engravingEffect.setMpRecovery(2 + incrementByStone);
                    engravingEffect.setCooldownReduction(4 + (0.75 * lvl));
                }
                case "유물" -> {
                    engravingEffect.setMpRecovery(2.4 + incrementByStone);
                    engravingEffect.setCooldownReduction(7 + (0.75 * lvl));
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();

            switch (grade) {
                case "영웅" -> {
                    increment.setName("Mp Recovery");
                    increment.setIncrement(0.15);
                }
                case "전설", "유물" -> {
                    increment.setName("Cooldown Reduction");
                    increment.setIncrement(0.75);
                }
            }

            return increment;
        }
    },
    MANA_EFFICIENCY("마나 효율 증가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.setMpEfficiency(true);
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.setMpRecovery(12);
                    engravingEffect.addOutgoingDmg(9 + (0.5 * lvl) + incrementByStone);
                }
                case "전설" -> {
                    engravingEffect.setMpRecovery(16);
                    engravingEffect.addOutgoingDmg(11 + (0.5 * lvl) + incrementByStone);
                }
                case "유물" -> {
                    engravingEffect.setMpRecovery(20);
                    engravingEffect.addOutgoingDmg(13 + (0.75 * lvl) + incrementByStone);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");

            switch (grade) {
                case "영웅", "전설" -> increment.setIncrement(0.5);
                case "유물" -> increment.setIncrement(0.75);
            }

            return increment;
        }
    },
    ALL_OUT_ATTACK("속전속결") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.setHoldCastSpeed(12 + lvl);
                    engravingEffect.addOutgoingDmg(14 + (0.5 * lvl) + incrementByStone);
                }
                case "전설" -> {
                    engravingEffect.setHoldCastSpeed(16 + lvl);
                    engravingEffect.addOutgoingDmg(16 + (0.5 * lvl) + incrementByStone);
                }
                case "유물" -> {
                    engravingEffect.setHoldCastSpeed(20);
                    engravingEffect.addOutgoingDmg(18 + (0.75 * lvl) + incrementByStone);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");

            switch (grade) {
                case "영웅", "전설" -> increment.setIncrement(0.5);
                case "유물" -> increment.setIncrement(0.75);
            }

            return increment;
        }
    },
    SUPER_CHARGE("슈퍼 차지") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.setChargeSpeed(24 + (2 * lvl));
                    engravingEffect.addOutgoingDmg(4 + (0.5 * lvl) + incrementByStone);
                }
                case "전설" -> {
                    engravingEffect.setChargeSpeed(32 + (2 * lvl));
                    engravingEffect.addOutgoingDmg(10 + (0.5 * lvl) + incrementByStone);
                }
                case "유물" -> {
                    engravingEffect.setChargeSpeed(40);
                    engravingEffect.addOutgoingDmg(20 + (0.75 * lvl) + incrementByStone);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");

            switch (grade) {
                case "영웅", "전설" -> increment.setIncrement(0.5);
                case "유물" -> increment.setIncrement(0.75);
            }

            return increment;
        }
    },
    ADRENALINE("아드레날린") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.addAtkPowerPercent(2.4 + (0.75 * lvl) + incrementByStone);
                    engravingEffect.addCritRate(8);
                }
                case "전설" -> {
                    engravingEffect.addAtkPowerPercent(5.4 + incrementByStone);
                    engravingEffect.addCritRate(8 + (1.5 * lvl));
                }
                case "유물" -> {
                    engravingEffect.addAtkPowerPercent(5.4 + incrementByStone);
                    engravingEffect.addCritRate(14 + (1.5 * lvl));
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();

            switch (grade) {
                case "영웅" -> {
                    increment.setName("Attack Power Percent");
                    increment.setIncrement(0.75);
                }
                case "전설", "유물" -> {
                    increment.setName("Crit Rate");
                    increment.setIncrement(1.5);
                }
            }

            return increment;
        }
    },
    ETHER_PREDATOR("에테르 포식자") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.addAtkPowerPercent(12.6 + incrementByStone);
                    engravingEffect.addDefensePercent(12 + (4.5 * lvl));
                }
                case "전설" -> {
                    engravingEffect.addAtkPowerPercent(12.6 + incrementByStone);
                    engravingEffect.addDefensePercent(30);
                }
                case "유물" -> {
                    engravingEffect.addAtkPowerPercent(12.6 + (0.9 * lvl) + incrementByStone);
                    engravingEffect.addDefensePercent(30);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();

            switch (grade) {
                case "영웅" -> {
                    increment.setName("Defense Percent");
                    increment.setIncrement(4.5);
                }
                case "유물" -> {
                    increment.setName("Attack Power Percent");
                    increment.setIncrement(0.9);
                }
            }

            return increment;
        }
    },
    KEEN_BLUNT_WEAPON("예리한 둔기") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.setKeenBluntWeapon(true);
            switch (grade) {
                case "영웅" -> engravingEffect.addCritDmg(28 + (2 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addCritDmg(36 + (2 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addCritDmg(44 + (2 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Crit Damage");
            increment.setIncrement(2);
            return increment;
        }
    },
    GRUDGE("원한") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.setGrudge(true);
            switch (grade) {
                case "영웅" -> engravingEffect.addOutgoingDmg(12 + (0.75 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addOutgoingDmg(15 + (0.75 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addOutgoingDmg(18 + (0.75 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    EXPERT("전문의") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> engravingEffect.setHealShieldEfficiency(16 + lvl);
                case "전설" -> engravingEffect.setHealShieldEfficiency(20 + lvl);
                case "유물" -> engravingEffect.setHealShieldEfficiency(24 + lvl);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Heal Shield Efficiency");
            increment.setIncrement(1);
            return increment;
        }
    },
    SPIRIT_ABSORPTION("정기 흡수") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            switch (grade) {
                case "영웅" -> {
                    engravingEffect.addAtkSpeed(7 + (0.75 * lvl) + incrementByStone);
                    engravingEffect.addMovementSpeed(7 + (0.75 * lvl) + incrementByStone);
                }
                case "전설" -> {
                    engravingEffect.addAtkSpeed(10 + (0.75 * lvl) + incrementByStone);
                    engravingEffect.addMovementSpeed(10 + (0.75 * lvl) + incrementByStone);
                }
                case "유물" -> {
                    engravingEffect.addAtkSpeed(13 + (0.75 * lvl) + incrementByStone);
                    engravingEffect.addMovementSpeed(13 + (0.75 * lvl) + incrementByStone);
                }
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Speed");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    PRECISE_DAGGER("정밀 단도") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.addCritDmg(-6);
            switch (grade) {
                case "영웅" -> engravingEffect.addCritRate(12 + (0.75 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addCritRate(15 + (0.75 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addCritRate(18 + (0.75 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Crit Rate");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    HEAVY_ARMOR("중갑 착용") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.setHeavyArmor(true);
            switch (grade) {
                case "영웅" -> engravingEffect.addDefensePercent(58 + (4 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addDefensePercent(74 + (4 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addDefensePercent(90 + (4 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Defense Percent");
            increment.setIncrement(4);
            return increment;
        }
    },
    MASS_INCREASE("질량 증가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.addAtkSpeed(-10);
            switch (grade) {
                case "영웅" -> engravingEffect.addOutgoingDmg(10 + (0.75 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addOutgoingDmg(13 + (0.75 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addOutgoingDmg(16 + (0.75 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    HIT_MASTER("타격의 대가") {
        @Override
        void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone) {
            engravingEffect.setHitMaster(true);
            switch (grade) {
                case "영웅" -> engravingEffect.addOutgoingDmg(8 + (0.75 * lvl) + incrementByStone);
                case "전설" -> engravingEffect.addOutgoingDmg(11 + (0.75 * lvl) + incrementByStone);
                case "유물" -> engravingEffect.addOutgoingDmg(14 + (0.75 * lvl) + incrementByStone);
            }
        }

        @Override
        EngravingLvlIncrement getIncrement(String grade) {
            EngravingLvlIncrement increment = new EngravingLvlIncrement();
            increment.setName("Outgoing Damage");
            increment.setIncrement(0.75);
            return increment;
        }
    },
    ;

    private final String[] name;

    EngravingIncrementT4(String... name) {
        this.name = name;
    }

    abstract void applyEffect(String name, EngravingEffect engravingEffect, String grade, int lvl, double incrementByStone);

    abstract EngravingLvlIncrement getIncrement(String grade);

    private static EngravingIncrementT4 of(String name) {
        return Arrays.stream(values())
                .filter(value -> Arrays.asList(value.name).contains(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static EngravingEffect applyEngravingEffect(CharacterEngraving characterEngraving, double incrementByStone) {
        EngravingIncrementT4 target = of(characterEngraving.getName());
        EngravingEffect engravingEffect = new EngravingEffect();
        target.applyEffect(characterEngraving.getName(), engravingEffect, characterEngraving.getGrade(), characterEngraving.getLvl(), incrementByStone);
        return engravingEffect;
    }

    public static EngravingLvlIncrement getIncrementByGrade(String name, String grade) {
        EngravingIncrementT4 target = of(name);
        return target.getIncrement(grade);
    }
}
