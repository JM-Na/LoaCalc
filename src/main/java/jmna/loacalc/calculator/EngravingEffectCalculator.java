package jmna.loacalc.calculator;

import jmna.loacalc.processor.engraving.CharacterEngraving;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EngravingEffectCalculator {

    public EngravingEffect calculateEngravingEffect(List<CharacterEngraving> characterEngravings) {
        if (characterEngravings.get(0).getIsTier4()) {
            return parseEngravingToEffectT4(characterEngravings);
        } else {
            return parseEngravingToEffectT3(characterEngravings);
        }
    }

    public EngravingEffect parseEngravingToEffectT4(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffect> engravingEffects = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            String name = characterEngraving.getName();
            String grade = characterEngraving.getGrade();
            int lvl = characterEngraving.getLvl();
            int stoneLvl = characterEngraving.getAbilityStoneLvl();
            EngravingEffect engravingEffect = new EngravingEffect();
            double incrementByStone = 0;
            switch (name) {
                case "각성" -> {
                    // 각성기 쿨감 10/25/50, 횟수 1/2/3
                    if (stoneLvl == 1)
                        incrementByStone += 6;
                    if (stoneLvl == 2)
                        incrementByStone += 7.5;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;

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
                case "결투의 대가", "기습의 대가" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 2.7;
                    if (stoneLvl == 2)
                        incrementByStone += 3.4;
                    if (stoneLvl == 3)
                        incrementByStone += 4.7;
                    if (stoneLvl == 4)
                        incrementByStone += 5.4;
                    // 적추피 1/3/7, 헤드어택 성공시 피해량이 추가로 3/7/15
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
//            case "구슬동자" -> {
//                // 에테르 생성 60/30/10
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
//            case "급소 타격" -> {
//                // 무력화 수치 6/18/36
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
                // 적추피 1/3/7, 백어택 성공시 피해량이 추가로 3/7/15
                case "달인의 저력" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 생명력이 50미만, 3/8/16
                    switch (grade) {
                        case "영웅" -> engravingEffect.setMasterTenacity(8 + (0.75 * lvl) + incrementByStone);
                        case "전설" -> engravingEffect.setMasterTenacity(11 + (0.75 * lvl) + incrementByStone);
                        case "유물" -> engravingEffect.setMasterTenacity(14 + (0.75 * lvl) + incrementByStone);
                    }
                }
                case "돌격대장" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 7.5;
                    if (stoneLvl == 2)
                        incrementByStone += 9.4;
                    if (stoneLvl == 3)
                        incrementByStone += 13.2;
                    if (stoneLvl == 4)
                        incrementByStone += 15;
                    // 이동속도 증가량의 10/22/45퍼센트 적추피 증가
                    switch (grade) {
                        case "영웅" -> engravingEffect.setRaidCaptain(24 + (2 * lvl) + incrementByStone);
                        case "전설" -> engravingEffect.setRaidCaptain(32 + (2 * lvl) + incrementByStone);
                        case "유물" -> engravingEffect.setRaidCaptain(40 + (2 * lvl) + incrementByStone);
                    }
                }
                case "마나의 흐름" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 0.6;
                    if (stoneLvl == 2)
                        incrementByStone += 0.75;
                    if (stoneLvl == 3)
                        incrementByStone += 1.05;
                    if (stoneLvl == 4)
                        incrementByStone += 1.2;
                    // 마나 회복 속도 0.5/1.2/2.4, 최대 중첩시 쿨감 2/5/8
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
                case "마나 효율 증가" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 마나 회복 속도 4/10/20, 마나를 사용하는 스킬 적추피 3/7/15
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.setMpRecovery(12);
                            engravingEffect.setMpEfficiency(9 + (0.5 * lvl) + incrementByStone);
                        }
                        case "전설" -> {
                            engravingEffect.setMpRecovery(16);
                            engravingEffect.setMpEfficiency(11 + (0.5 * lvl) + incrementByStone);
                        }
                        case "유물" -> {
                            engravingEffect.setMpRecovery(20);
                            engravingEffect.setMpEfficiency(13 + (0.75 * lvl) + incrementByStone);
                        }
                    }
                }
                case "바리케이드", "안정된 상태", "추진력" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 실드 효과가 적용되는 동안 적추피 3/8/16
                    switch (grade) {
                        case "영웅" -> engravingEffect.addOutgoingDmg(8 + (0.75 * lvl) + incrementByStone);
                        case "전설" -> engravingEffect.addOutgoingDmg(11 + (0.75 * lvl) + incrementByStone);
                        case "유물" -> engravingEffect.addOutgoingDmg(14 + (0.75 * lvl) + incrementByStone);
                    }
                }
                case "속전속결" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    //TODO 속전속결 돌 효과 확인 필요
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 홀딩 및 캐스팅 스킬 속도 5/10/20 증가, 피해량 4/10/20 증가
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
                case "슈퍼 차지" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 차지 스킬 차징 속도 8/20/40 증가, 피해량 4/10/20 증가
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
                case "아드레날린" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 2.88;
                    if (stoneLvl == 2)
                        incrementByStone += 3.60;
                    if (stoneLvl == 3)
                        incrementByStone += 4.98;
                    if (stoneLvl == 4)
                        incrementByStone += 5.70;
                    // 중첩당 공격력 % 0.3/0.6/1.0 (최대 6), 최대 중첩 도달시 치적 5/10/15
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
                // 최대 체력 65 이상일 때, 3/8/16
                case "에테르 포식자" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.9;
                    if (stoneLvl == 3)
                        incrementByStone += 5.4;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 에테르 획득 시 공격력% 0.2/0.3/0.5, 방어력% 0.3/0.6/1.0 (최대 30중첩)
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
                case "예리한 둔기" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 7.5;
                    if (stoneLvl == 2)
                        incrementByStone += 9.4;
                    if (stoneLvl == 3)
                        incrementByStone += 13.2;
                    if (stoneLvl == 4)
                        incrementByStone += 15;
                    // 치명타 피해량 10/25/50 증가, 공격 시 10%의 확률로 20% 감소된 피해
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addCritDmg(28 + (2 * lvl) + incrementByStone);
                            engravingEffect.setKeenBluntWeapon(true);
                        }
                        case "전설" -> {
                            engravingEffect.addCritDmg(36 + (2 * lvl) + incrementByStone);
                            engravingEffect.setKeenBluntWeapon(true);
                        }
                        case "유물" -> {
                            engravingEffect.addCritDmg(44 + (2 * lvl) + incrementByStone);
                            engravingEffect.setKeenBluntWeapon(true);
                        }
                    }
                }
                case "원한" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 보스 및 레이드 몬스터에게 주는 피해 4/10/20 증가, 받는 피해 20% 증가
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addOutgoingDmg(12 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setGrudge(true);
                        }
                        case "전설" -> {
                            engravingEffect.addOutgoingDmg(15 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setGrudge(true);
                        }
                        case "유물" -> {
                            engravingEffect.addOutgoingDmg(18 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setGrudge(true);
                        }
                    }
                }
                case "저주받은 인형" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 적에게 주는 피해 3/8/16 증가, 받는 생명력 회복 효과 25% 감소
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addOutgoingDmg(8 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setCursedDoll(true);
                        }
                        case "전설" -> {
                            engravingEffect.addOutgoingDmg(11 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setCursedDoll(true);
                        }
                        case "유물" -> {
                            engravingEffect.addOutgoingDmg(14 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setCursedDoll(true);
                        }
                    }
                }
                case "전문의" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 4;
                    if (stoneLvl == 2)
                        incrementByStone += 5;
                    if (stoneLvl == 3)
                        incrementByStone += 7;
                    if (stoneLvl == 4)
                        incrementByStone += 8;
                    // 쉴드, 회복 효과 6/14/24 증가, 대상 생명력 50% 미만 시 3/7/12 추가 증가
                    switch (grade) {
                        case "영웅" -> engravingEffect.setHealShieldEfficiency(16 + lvl);
                        case "전설" -> engravingEffect.setHealShieldEfficiency(20 + lvl);
                        case "유물" -> engravingEffect.setHealShieldEfficiency(24 + lvl);
                    }
                }
                case "정기 흡수" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 공격 및 이동속도 3/8/15 증가
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
                case "정밀 단도" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 치적 4/10/20 증가, 치피 6 감소
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addCritRate(12 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.addCritDmg(-6);
                        }
                        case "전설" -> {
                            engravingEffect.addCritRate(15 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.addCritDmg(-6);
                        }
                        case "유물" -> {
                            engravingEffect.addCritRate(18 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.addCritDmg(-6);
                        }
                    }
                }
                case "중갑 착용" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 12;
                    if (stoneLvl == 2)
                        incrementByStone += 15;
                    if (stoneLvl == 3)
                        incrementByStone += 21;
                    if (stoneLvl == 4)
                        incrementByStone += 24;
                    // 방어력 20/50/100 증가, 방어력 감소 효과 적용 X
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addDefensePercent(58 + (4 * lvl) + incrementByStone);
                            engravingEffect.setHeavyArmor(true);
                        }
                        case "전설" -> {
                            engravingEffect.addDefensePercent(74 + (4 * lvl) + incrementByStone);
                            engravingEffect.setHeavyArmor(true);
                        }
                        case "유물" -> {
                            engravingEffect.addDefensePercent(90 + (4 * lvl) + incrementByStone);
                            engravingEffect.setHeavyArmor(true);
                        }
                    }
                }
                case "질량 증가" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 공격속도 10 감소, 적에게 주는 피해 4/10/18 증가
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addAtkSpeed(-10);
                            engravingEffect.addOutgoingDmg(10 + (0.75 * lvl) + incrementByStone);
                        }
                        case "전설" -> {
                            engravingEffect.addAtkSpeed(-10);
                            engravingEffect.addOutgoingDmg(13 + (0.75 * lvl) + incrementByStone);
                        }
                        case "유물" -> {
                            engravingEffect.addAtkSpeed(-10);
                            engravingEffect.addOutgoingDmg(16 + (0.75 * lvl) + incrementByStone);
                        }
                    }
                }
//            case "최대 마나 증가" -> {
//                // 최대 마나 5/15/30 증가
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
                // 이동기 사용 후 기본 공격 및 각성기를 제외한 피해 3/8/16 증가
                case "타격의 대가" -> {
                    if (stoneLvl == 1)
                        incrementByStone += 3;
                    if (stoneLvl == 2)
                        incrementByStone += 3.75;
                    if (stoneLvl == 3)
                        incrementByStone += 5.25;
                    if (stoneLvl == 4)
                        incrementByStone += 6;
                    // 비사멸 스킬 3/8/16 증가, 각성기는 제외
                    switch (grade) {
                        case "영웅" -> {
                            engravingEffect.addOutgoingDmg(8 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setHitMaster(true);
                        }
                        case "전설" -> {
                            engravingEffect.addOutgoingDmg(11 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setHitMaster(true);
                        }
                        case "유물" -> {
                            engravingEffect.addOutgoingDmg(14 + (0.75 * lvl) + incrementByStone);
                            engravingEffect.setHitMaster(true);
                        }
                    }
                }
            }
            engravingEffects.add(engravingEffect);
        }
        return engravingEffects.stream().reduce(new EngravingEffect(), EngravingEffect::merge);
    }

    public EngravingEffect parseEngravingToEffectT3(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffect> engravingEffects = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            String name = characterEngraving.getName();
            int lvl = characterEngraving.getLvl();
            EngravingEffect engravingEffect = new EngravingEffect();
            switch (name) {
                case "각성" -> {
                    // 각성기 쿨감 10/25/50, 횟수 1/2/3
                    if (lvl == 1) {
                        engravingEffect.setAwakeningCooldown(10);
                        engravingEffect.setAwakeningCast(1);
                    } else if (lvl == 2) {
                        engravingEffect.setAwakeningCooldown(25);
                        engravingEffect.setAwakeningCast(2);
                    } else if (lvl == 3) {
                        engravingEffect.setAwakeningCooldown(50);
                        engravingEffect.setAwakeningCast(3);
                    }
                }
                case "결투의 대가", "기습의 대가" -> {
                    // 적추피 1/3/7, 헤드어택 성공시 피해량이 추가로 3/7/15
                    if (lvl == 1) {
                        engravingEffect.addOutgoingDmg(1);
                        engravingEffect.setAddBackHeadDmg(3);
                    } else if (lvl == 2) {
                        engravingEffect.addOutgoingDmg(3);
                        engravingEffect.setAddBackHeadDmg(7);
                    } else if (lvl == 3) {
                        engravingEffect.addOutgoingDmg(7);
                        engravingEffect.setAddBackHeadDmg(15);
                    }
                }
//            case "구슬동자" -> {
//                // 에테르 생성 60/30/10
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
//            case "급소 타격" -> {
//                // 무력화 수치 6/18/36
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
                // 적추피 1/3/7, 백어택 성공시 피해량이 추가로 3/7/15
                case "달인의 저력" -> {
                    // 생명력이 50미만, 3/8/16
                    if (lvl == 1) {
                        engravingEffect.setMasterTenacity(3);
                    } else if (lvl == 2) {
                        engravingEffect.setMasterTenacity(8);
                    } else if (lvl == 3) {
                        engravingEffect.setMasterTenacity(16);
                    }
                }
                case "돌격대장" -> {
                    // 이동속도 증가량의 10/22/45퍼센트 적추피 증가
                    if (lvl == 1) {
                        engravingEffect.setRaidCaptain(10);
                    } else if (lvl == 2) {
                        engravingEffect.setRaidCaptain(22);
                    } else if (lvl == 3) {
                        engravingEffect.setRaidCaptain(45);
                    }
                }
                case "마나의 흐름" -> {
                    // 마나 회복 속도 0.5/1.2/2.4, 최대 중첩시 쿨감 2/5/8
                    if (lvl == 1) {
                        engravingEffect.setMpRecovery(0.5);
                        engravingEffect.setCooldownReduction(2);
                    } else if (lvl == 2) {
                        engravingEffect.setMpRecovery(1.2);
                        engravingEffect.setCooldownReduction(5);
                    } else if (lvl == 3) {
                        engravingEffect.setMpRecovery(2.4);
                        engravingEffect.setCooldownReduction(8);
                    }
                }
                case "마나 효율 증가" -> {
                    // 마나 회복 속도 4/10/20, 마나를 사용하는 스킬 적추피 3/7/15
                    if (lvl == 1) {
                        engravingEffect.setCooldownReduction(4);
                        engravingEffect.setMpEfficiency(3);
                    } else if (lvl == 2) {
                        engravingEffect.setCooldownReduction(10);
                        engravingEffect.setMpEfficiency(7);
                    } else if (lvl == 3) {
                        engravingEffect.setCooldownReduction(20);
                        engravingEffect.setMpEfficiency(15);
                    }
                }
                case "바리케이드", "안정된 상태", "추진력" -> {
                    // 실드 효과가 적용되는 동안 적추피 3/8/16
                    if (lvl == 1) {
                        engravingEffect.addOutgoingDmg(3);
                    } else if (lvl == 2) {
                        engravingEffect.addOutgoingDmg(8);
                    } else if (lvl == 3) {
                        engravingEffect.addOutgoingDmg(16);
                    }
                }
                case "속전속결" -> {
                    // 홀딩 및 캐스팅 스킬 속도 5/10/20 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffect.setHoldCastSpeed(5);
                        engravingEffect.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffect.setHoldCastSpeed(10);
                        engravingEffect.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffect.setHoldCastSpeed(20);
                        engravingEffect.addOutgoingDmg(20);
                    }
                }
                case "슈퍼 차지" -> {
                    // 차지 스킬 차징 속도 8/20/40 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffect.setChargeSpeed(8);
                        engravingEffect.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffect.setChargeSpeed(20);
                        engravingEffect.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffect.setChargeSpeed(40);
                        engravingEffect.addOutgoingDmg(20);
                    }
                }
                case "아드레날린" -> {
                    // 중첩당 공격력 % 0.3/0.6/1.0 (최대 6), 최대 중첩 도달시 치적 5/10/15
                    if (lvl == 1) {
                        engravingEffect.addAtkPowerPercent(1.8);
                        engravingEffect.addCritRate(5);
                    } else if (lvl == 2) {
                        engravingEffect.addAtkPowerPercent(3.6);
                        engravingEffect.addCritRate(10);
                    } else if (lvl == 3) {
                        engravingEffect.addAtkPowerPercent(6.0);
                        engravingEffect.addCritRate(15);
                    }
                }
                // 최대 체력 65 이상일 때, 3/8/16
                case "에테르 포식자" -> {
                    // 에테르 획득 시 공격력% 0.2/0.3/0.5, 방어력% 0.3/0.6/1.0 (최대 30중첩)
                    if (lvl == 1) {
                        engravingEffect.addAtkPowerPercent(6);
                        engravingEffect.addDefensePercent(9);
                    } else if (lvl == 2) {
                        engravingEffect.addAtkPowerPercent(9);
                        engravingEffect.addDefensePercent(18);
                    } else if (lvl == 3) {
                        engravingEffect.addAtkPowerPercent(15);
                        engravingEffect.addDefensePercent(30);
                    }
                }
                case "예리한 둔기" -> {
                    // 치명타 피해량 10/25/50 증가, 공격 시 10%의 확률로 20% 감소된 피해
                    if (lvl == 1) {
                        engravingEffect.addCritDmg(10);
                        engravingEffect.setKeenBluntWeapon(true);
                    } else if (lvl == 2) {
                        engravingEffect.addCritDmg(25);
                        engravingEffect.setKeenBluntWeapon(true);
                    } else if (lvl == 3) {
                        engravingEffect.addCritDmg(50);
                        engravingEffect.setKeenBluntWeapon(true);
                    }
                }
                case "원한" -> {
                    // 보스 및 레이드 몬스터에게 주는 피해 4/10/20 증가, 받는 피해 20% 증가
                    if (lvl == 1) {
                        engravingEffect.addOutgoingDmg(4);
                        engravingEffect.setGrudge(true);
                    } else if (lvl == 2) {
                        engravingEffect.addOutgoingDmg(10);
                        engravingEffect.setGrudge(true);
                    } else if (lvl == 3) {
                        engravingEffect.addOutgoingDmg(20);
                        engravingEffect.setGrudge(true);
                    }
                }
                case "저주받은 인형" -> {
                    // 적에게 주는 피해 3/8/16 증가, 받는 생명력 회복 효과 25% 감소
                    if (lvl == 1) {
                        engravingEffect.addOutgoingDmg(3);
                        engravingEffect.setCursedDoll(true);
                    } else if (lvl == 2) {
                        engravingEffect.addOutgoingDmg(8);
                        engravingEffect.setCursedDoll(true);
                    } else if (lvl == 3) {
                        engravingEffect.addOutgoingDmg(16);
                        engravingEffect.setCursedDoll(true);
                    }
                }
                case "전문의" -> {
                    // 쉴드, 회복 효과 6/14/24 증가, 대상 생명력 50% 미만 시 3/7/12 추가 증가
                    if (lvl == 1) {
                        engravingEffect.setHealShieldEfficiency(6);
                    } else if (lvl == 2) {
                        engravingEffect.setHealShieldEfficiency(14);
                    } else if (lvl == 3) {
                        engravingEffect.setHealShieldEfficiency(24);
                    }
                }
                case "정기 흡수" -> {
                    // 공격 및 이동속도 3/8/15 증가
                    if (lvl == 1) {
                        engravingEffect.addAtkSpeed(3);
                        engravingEffect.addMovementSpeed(3);
                    } else if (lvl == 2) {
                        engravingEffect.addAtkSpeed(8);
                        engravingEffect.addMovementSpeed(8);
                    } else if (lvl == 3) {
                        engravingEffect.addAtkSpeed(15);
                        engravingEffect.addMovementSpeed(15);
                    }
                }
                case "정밀 단도" -> {
                    // 치적 4/10/20 증가, 치피 6 감소
                    if (lvl == 1) {
                        engravingEffect.addCritRate(4);
                        engravingEffect.addCritDmg(-6);
                    } else if (lvl == 2) {
                        engravingEffect.addCritRate(10);
                        engravingEffect.addCritDmg(-6);
                    } else if (lvl == 3) {
                        engravingEffect.addCritRate(20);
                        engravingEffect.addCritDmg(-6);
                    }
                }
                case "중갑 착용" -> {
                    // 방어력 20/50/100 증가, 방어력 감소 효과 적용 X
                    if (lvl == 1) {
                        engravingEffect.addDefensePercent(20);
                        engravingEffect.setHeavyArmor(true);
                    } else if (lvl == 2) {
                        engravingEffect.addDefensePercent(50);
                        engravingEffect.setHeavyArmor(true);
                    } else if (lvl == 3) {
                        engravingEffect.addDefensePercent(100);
                        engravingEffect.setHeavyArmor(true);
                    }
                }
                case "질량 증가" -> {
                    // 공격속도 10 감소, 적에게 주는 피해 4/10/18 증가
                    if (lvl == 1) {
                        engravingEffect.addAtkSpeed(-10);
                        engravingEffect.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffect.addAtkSpeed(-10);
                        engravingEffect.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffect.addAtkSpeed(-10);
                        engravingEffect.addOutgoingDmg(18);
                    }
                }
//            case "최대 마나 증가" -> {
//                // 최대 마나 5/15/30 증가
//                if (lvl == 1) {
//
//                } else if (lvl == 2) {
//
//                } else if (lvl == 3) {
//
//                }
//            }
                // 이동기 사용 후 기본 공격 및 각성기를 제외한 피해 3/8/16 증가
                case "타격의 대가" -> {
                    // 비사멸 스킬 3/8/16 증가, 각성기는 제외
                    if (lvl == 1) {
                        engravingEffect.addOutgoingDmg(3);
                        engravingEffect.setHitMaster(true);
                    } else if (lvl == 2) {
                        engravingEffect.addOutgoingDmg(8);
                        engravingEffect.setHitMaster(true);
                    } else if (lvl == 3) {
                        engravingEffect.addOutgoingDmg(16);
                        engravingEffect.setHitMaster(true);
                    }
                }
            }
            engravingEffects.add(engravingEffect);
        }
        return engravingEffects.stream().reduce(new EngravingEffect(), EngravingEffect::merge);
    }
}
