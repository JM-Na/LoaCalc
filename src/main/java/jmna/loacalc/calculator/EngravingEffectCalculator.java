package jmna.loacalc.calculator;

import jmna.loacalc.processor.CharacterEngraving;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EngravingEffectCalculator {

    public void calculateEngravingEffect(List<CharacterEngraving> characterEngravings) {
        if (characterEngravings.get(0).getIsTier4()) {

        } else {
            List<EngravingEffectT3> engravingEffectT3s = parseEngravingToEffectT3(characterEngravings);
        }
    }

    public List<EngravingEffectT3> parseEngravingToEffectT4(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffectT3> engravingEffectT3s = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            String name = characterEngraving.getName();
            int lvl = characterEngraving.getLvl();
            EngravingEffectT3 engravingEffectT3 = new EngravingEffectT3();
            switch (name) {
                case "각성" -> {
                    // 각성기 쿨감 10/25/50, 횟수 1/2/3
                    if (lvl == 1) {
                        engravingEffectT3.setAwakeningCooldown(10);
                        engravingEffectT3.setAwakeningCast(1);
                    } else if (lvl == 2) {
                        engravingEffectT3.setAwakeningCooldown(25);
                        engravingEffectT3.setAwakeningCast(2);
                    } else if (lvl == 3) {
                        engravingEffectT3.setAwakeningCooldown(50);
                        engravingEffectT3.setAwakeningCast(3);
                    }
                }
                case "결투의 대가", "기습의 대가" -> {
                    // 적추피 1/3/7, 헤드어택 성공시 피해량이 추가로 3/7/15
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(1);
                        engravingEffectT3.setAddBackHeadDmg(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setAddBackHeadDmg(7);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(7);
                        engravingEffectT3.setAddBackHeadDmg(15);
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
                        engravingEffectT3.setMasterTenacity(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.setMasterTenacity(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.setMasterTenacity(16);
                    }
                }
                case "돌격대장" -> {
                    // 이동속도 증가량의 10/22/45퍼센트 적추피 증가
                    if (lvl == 1) {
                        engravingEffectT3.setRaidCaptain(10);
                    } else if (lvl == 2) {
                        engravingEffectT3.setRaidCaptain(22);
                    } else if (lvl == 3) {
                        engravingEffectT3.setRaidCaptain(45);
                    }
                }
                case "마나의 흐름" -> {
                    // 마나 회복 속도 0.5/1.2/2.4, 최대 중첩시 쿨감 2/5/8
                    if (lvl == 1) {
                        engravingEffectT3.setMpRecovery(0.5);
                        engravingEffectT3.setCooldown(2);
                    } else if (lvl == 2) {
                        engravingEffectT3.setMpRecovery(1.2);
                        engravingEffectT3.setCooldown(5);
                    } else if (lvl == 3) {
                        engravingEffectT3.setMpRecovery(2.4);
                        engravingEffectT3.setCooldown(8);
                    }
                }
                case "마나 효율 증가" -> {
                    // 마나 회복 속도 4/10/20, 마나를 사용하는 스킬 적추피 3/7/15
                    if (lvl == 1) {
                        engravingEffectT3.setCooldown(4);
                        engravingEffectT3.setMpEfficiency(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.setCooldown(10);
                        engravingEffectT3.setMpEfficiency(7);
                    } else if (lvl == 3) {
                        engravingEffectT3.setCooldown(20);
                        engravingEffectT3.setMpEfficiency(15);
                    }
                }
                case "바리케이드", "안정된 상태", "추진력" -> {
                    // 실드 효과가 적용되는 동안 적추피 3/8/16
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                    }
                }
                case "속전속결" -> {
                    // 홀딩 및 캐스팅 스킬 속도 5/10/20 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffectT3.setHoldCastSpeed(5);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.setHoldCastSpeed(10);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.setHoldCastSpeed(20);
                        engravingEffectT3.addOutgoingDmg(20);
                    }
                }
                case "슈퍼 차지" -> {
                    // 차지 스킬 차징 속도 8/20/40 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffectT3.setChargeSpeed(8);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.setChargeSpeed(20);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.setChargeSpeed(40);
                        engravingEffectT3.addOutgoingDmg(20);
                    }
                }
                case "아드레날린" -> {
                    // 중첩당 공격력 % 0.3/0.6/1.0 (최대 6), 최대 중첩 도달시 치적 5/10/15
                    if (lvl == 1) {
                        engravingEffectT3.addAtkPowerPercent(1.8);
                        engravingEffectT3.addCritRate(5);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkPowerPercent(3.6);
                        engravingEffectT3.addCritRate(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkPowerPercent(6.0);
                        engravingEffectT3.addCritRate(15);
                    }
                }
                // 최대 체력 65 이상일 때, 3/8/16
                case "에테르 포식자" -> {
                    // 에테르 획득 시 공격력% 0.2/0.3/0.5, 방어력% 0.3/0.6/1.0 (최대 30중첩)
                    if (lvl == 1) {
                        engravingEffectT3.addAtkPowerPercent(6);
                        engravingEffectT3.addDefensePercent(9);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkPowerPercent(9);
                        engravingEffectT3.addDefensePercent(18);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkPowerPercent(15);
                        engravingEffectT3.addDefensePercent(30);
                    }
                }
                case "예리한 둔기" -> {
                    // 치명타 피해량 10/25/50 증가, 공격 시 10%의 확률로 20% 감소된 피해
                    if (lvl == 1) {
                        engravingEffectT3.addCritDmg(10);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addCritDmg(25);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addCritDmg(50);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    }
                }
                case "원한" -> {
                    // 보스 및 레이드 몬스터에게 주는 피해 4/10/20 증가, 받는 피해 20% 증가
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(4);
                        engravingEffectT3.setGrudge(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(10);
                        engravingEffectT3.setGrudge(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(20);
                        engravingEffectT3.setGrudge(true);
                    }
                }
                case "저주받은 인형" -> {
                    // 적에게 주는 피해 3/8/16 증가, 받는 생명력 회복 효과 25% 감소
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setCursedDoll(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                        engravingEffectT3.setCursedDoll(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                        engravingEffectT3.setCursedDoll(true);
                    }
                }
                case "전문의" -> {
                    // 쉴드, 회복 효과 6/14/24 증가, 대상 생명력 50% 미만 시 3/7/12 추가 증가
                    if (lvl == 1) {
                        engravingEffectT3.setHealShieldEfficiency(6);
                    } else if (lvl == 2) {
                        engravingEffectT3.setHealShieldEfficiency(14);
                    } else if (lvl == 3) {
                        engravingEffectT3.setHealShieldEfficiency(24);
                    }
                }
                case "정기 흡수" -> {
                    // 공격 및 이동속도 3/8/15 증가
                    if (lvl == 1) {
                        engravingEffectT3.addAtkSpeed(3);
                        engravingEffectT3.addMovementSpeed(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkSpeed(8);
                        engravingEffectT3.addMovementSpeed(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkSpeed(15);
                        engravingEffectT3.addMovementSpeed(15);
                    }
                }
                case "정밀 단도" -> {
                    // 치적 4/10/20 증가, 치피 6 감소
                    if (lvl == 1) {
                        engravingEffectT3.addCritRate(4);
                        engravingEffectT3.addCritDmg(-6);
                    } else if (lvl == 2) {
                        engravingEffectT3.addCritRate(10);
                        engravingEffectT3.addCritDmg(-6);
                    } else if (lvl == 3) {
                        engravingEffectT3.addCritRate(20);
                        engravingEffectT3.addCritDmg(-6);
                    }
                }
                case "중갑 착용" -> {
                    // 방어력 20/50/100 증가, 방어력 감소 효과 적용 X
                    if (lvl == 1) {
                        engravingEffectT3.addDefensePercent(20);
                        engravingEffectT3.setHeavyArmor(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addDefensePercent(50);
                        engravingEffectT3.setHeavyArmor(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addDefensePercent(100);
                        engravingEffectT3.setHeavyArmor(true);
                    }
                }
                case "질량 증가" -> {
                    // 공격속도 10 감소, 적에게 주는 피해 4/10/18 증가
                    if (lvl == 1) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(18);
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
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setHitMaster(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                        engravingEffectT3.setHitMaster(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                        engravingEffectT3.setHitMaster(true);
                    }
                }
            }
            engravingEffectT3s.add(engravingEffectT3);
        }
        return engravingEffectT3s;
    }

    public List<EngravingEffectT3> parseEngravingToEffectT3(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffectT3> engravingEffectT3s = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            String name = characterEngraving.getName();
            int lvl = characterEngraving.getLvl();
            EngravingEffectT3 engravingEffectT3 = new EngravingEffectT3();
            switch (name) {
                case "각성" -> {
                    // 각성기 쿨감 10/25/50, 횟수 1/2/3
                    if (lvl == 1) {
                        engravingEffectT3.setAwakeningCooldown(10);
                        engravingEffectT3.setAwakeningCast(1);
                    } else if (lvl == 2) {
                        engravingEffectT3.setAwakeningCooldown(25);
                        engravingEffectT3.setAwakeningCast(2);
                    } else if (lvl == 3) {
                        engravingEffectT3.setAwakeningCooldown(50);
                        engravingEffectT3.setAwakeningCast(3);
                    }
                }
                case "결투의 대가", "기습의 대가" -> {
                    // 적추피 1/3/7, 헤드어택 성공시 피해량이 추가로 3/7/15
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(1);
                        engravingEffectT3.setAddBackHeadDmg(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setAddBackHeadDmg(7);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(7);
                        engravingEffectT3.setAddBackHeadDmg(15);
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
                        engravingEffectT3.setMasterTenacity(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.setMasterTenacity(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.setMasterTenacity(16);
                    }
                }
                case "돌격대장" -> {
                    // 이동속도 증가량의 10/22/45퍼센트 적추피 증가
                    if (lvl == 1) {
                        engravingEffectT3.setRaidCaptain(10);
                    } else if (lvl == 2) {
                        engravingEffectT3.setRaidCaptain(22);
                    } else if (lvl == 3) {
                        engravingEffectT3.setRaidCaptain(45);
                    }
                }
                case "마나의 흐름" -> {
                    // 마나 회복 속도 0.5/1.2/2.4, 최대 중첩시 쿨감 2/5/8
                    if (lvl == 1) {
                        engravingEffectT3.setMpRecovery(0.5);
                        engravingEffectT3.setCooldown(2);
                    } else if (lvl == 2) {
                        engravingEffectT3.setMpRecovery(1.2);
                        engravingEffectT3.setCooldown(5);
                    } else if (lvl == 3) {
                        engravingEffectT3.setMpRecovery(2.4);
                        engravingEffectT3.setCooldown(8);
                    }
                }
                case "마나 효율 증가" -> {
                    // 마나 회복 속도 4/10/20, 마나를 사용하는 스킬 적추피 3/7/15
                    if (lvl == 1) {
                        engravingEffectT3.setCooldown(4);
                        engravingEffectT3.setMpEfficiency(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.setCooldown(10);
                        engravingEffectT3.setMpEfficiency(7);
                    } else if (lvl == 3) {
                        engravingEffectT3.setCooldown(20);
                        engravingEffectT3.setMpEfficiency(15);
                    }
                }
                case "바리케이드", "안정된 상태", "추진력" -> {
                    // 실드 효과가 적용되는 동안 적추피 3/8/16
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                    }
                }
                case "속전속결" -> {
                    // 홀딩 및 캐스팅 스킬 속도 5/10/20 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffectT3.setHoldCastSpeed(5);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.setHoldCastSpeed(10);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.setHoldCastSpeed(20);
                        engravingEffectT3.addOutgoingDmg(20);
                    }
                }
                case "슈퍼 차지" -> {
                    // 차지 스킬 차징 속도 8/20/40 증가, 피해량 4/10/20 증가
                    if (lvl == 1) {
                        engravingEffectT3.setChargeSpeed(8);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.setChargeSpeed(20);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.setChargeSpeed(40);
                        engravingEffectT3.addOutgoingDmg(20);
                    }
                }
                case "아드레날린" -> {
                    // 중첩당 공격력 % 0.3/0.6/1.0 (최대 6), 최대 중첩 도달시 치적 5/10/15
                    if (lvl == 1) {
                        engravingEffectT3.addAtkPowerPercent(1.8);
                        engravingEffectT3.addCritRate(5);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkPowerPercent(3.6);
                        engravingEffectT3.addCritRate(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkPowerPercent(6.0);
                        engravingEffectT3.addCritRate(15);
                    }
                }
                // 최대 체력 65 이상일 때, 3/8/16
                case "에테르 포식자" -> {
                    // 에테르 획득 시 공격력% 0.2/0.3/0.5, 방어력% 0.3/0.6/1.0 (최대 30중첩)
                    if (lvl == 1) {
                        engravingEffectT3.addAtkPowerPercent(6);
                        engravingEffectT3.addDefensePercent(9);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkPowerPercent(9);
                        engravingEffectT3.addDefensePercent(18);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkPowerPercent(15);
                        engravingEffectT3.addDefensePercent(30);
                    }
                }
                case "예리한 둔기" -> {
                    // 치명타 피해량 10/25/50 증가, 공격 시 10%의 확률로 20% 감소된 피해
                    if (lvl == 1) {
                        engravingEffectT3.addCritDmg(10);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addCritDmg(25);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addCritDmg(50);
                        engravingEffectT3.setKeenBluntWeapon(true);
                    }
                }
                case "원한" -> {
                    // 보스 및 레이드 몬스터에게 주는 피해 4/10/20 증가, 받는 피해 20% 증가
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(4);
                        engravingEffectT3.setGrudge(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(10);
                        engravingEffectT3.setGrudge(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(20);
                        engravingEffectT3.setGrudge(true);
                    }
                }
                case "저주받은 인형" -> {
                    // 적에게 주는 피해 3/8/16 증가, 받는 생명력 회복 효과 25% 감소
                    if (lvl == 1) {
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setCursedDoll(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                        engravingEffectT3.setCursedDoll(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                        engravingEffectT3.setCursedDoll(true);
                    }
                }
                case "전문의" -> {
                    // 쉴드, 회복 효과 6/14/24 증가, 대상 생명력 50% 미만 시 3/7/12 추가 증가
                    if (lvl == 1) {
                        engravingEffectT3.setHealShieldEfficiency(6);
                    } else if (lvl == 2) {
                        engravingEffectT3.setHealShieldEfficiency(14);
                    } else if (lvl == 3) {
                        engravingEffectT3.setHealShieldEfficiency(24);
                    }
                }
                case "정기 흡수" -> {
                    // 공격 및 이동속도 3/8/15 증가
                    if (lvl == 1) {
                        engravingEffectT3.addAtkSpeed(3);
                        engravingEffectT3.addMovementSpeed(3);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkSpeed(8);
                        engravingEffectT3.addMovementSpeed(8);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkSpeed(15);
                        engravingEffectT3.addMovementSpeed(15);
                    }
                }
                case "정밀 단도" -> {
                    // 치적 4/10/20 증가, 치피 6 감소
                    if (lvl == 1) {
                        engravingEffectT3.addCritRate(4);
                        engravingEffectT3.addCritDmg(-6);
                    } else if (lvl == 2) {
                        engravingEffectT3.addCritRate(10);
                        engravingEffectT3.addCritDmg(-6);
                    } else if (lvl == 3) {
                        engravingEffectT3.addCritRate(20);
                        engravingEffectT3.addCritDmg(-6);
                    }
                }
                case "중갑 착용" -> {
                    // 방어력 20/50/100 증가, 방어력 감소 효과 적용 X
                    if (lvl == 1) {
                        engravingEffectT3.addDefensePercent(20);
                        engravingEffectT3.setHeavyArmor(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addDefensePercent(50);
                        engravingEffectT3.setHeavyArmor(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addDefensePercent(100);
                        engravingEffectT3.setHeavyArmor(true);
                    }
                }
                case "질량 증가" -> {
                    // 공격속도 10 감소, 적에게 주는 피해 4/10/18 증가
                    if (lvl == 1) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(4);
                    } else if (lvl == 2) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(10);
                    } else if (lvl == 3) {
                        engravingEffectT3.addAtkSpeed(-10);
                        engravingEffectT3.addOutgoingDmg(18);
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
                        engravingEffectT3.addOutgoingDmg(3);
                        engravingEffectT3.setHitMaster(true);
                    } else if (lvl == 2) {
                        engravingEffectT3.addOutgoingDmg(8);
                        engravingEffectT3.setHitMaster(true);
                    } else if (lvl == 3) {
                        engravingEffectT3.addOutgoingDmg(16);
                        engravingEffectT3.setHitMaster(true);
                    }
                }
            }
            engravingEffectT3s.add(engravingEffectT3);
        }
        return engravingEffectT3s;
    }
}
