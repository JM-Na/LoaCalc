package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.elixir.ElixirSetDeal;
import jmna.loacalc.calculator.elixir.ElixirSetSup;
import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.ElixirData;
import jmna.loacalc.processor.equipment.armory.Weapon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ArmoryEffectCalculator {

    public TotalEffect calculateTotalArmoryEffect(List<BaseArmory> baseArmories, int totalTransc) {

        ArmoryEffect armoryEffect = calculateArmoryEffect(baseArmories);
        ElixirEffect totalElixirEffect = calculateElixirEffect(baseArmories);
        TranscendenceEffect totalTranscEffect = calculateTranscEffect(baseArmories, totalTransc);

        TotalEffect totalEffect = new TotalEffect();
        totalEffect.merge(armoryEffect, totalElixirEffect, totalTranscEffect);

        return totalEffect;
    }

    public ArmoryEffect calculateArmoryEffect(List<BaseArmory> baseArmories) {
        ArmoryEffect armoryEffect = new ArmoryEffect();

        for (BaseArmory baseArmory : baseArmories) {
            armoryEffect.add(baseArmory);
        }

        return armoryEffect;
    }

    public TranscendenceEffect calculateTranscEffect(List<BaseArmory> baseArmories, int totalTranscendence) {

        List<TranscendenceEffect> transcEffects = new ArrayList<>();

        for (BaseArmory baseArmory : baseArmories) {
            Integer transcLvl = baseArmory.getTranscendenceLvl();
            Integer transcGrade = baseArmory.getTranscendenceGrade();

            TranscendenceEffect transcEffect = new TranscendenceEffect();
            transcEffect.setArmoryType(baseArmory.getType());

            if (baseArmory.getClass().equals(Weapon.class) && transcLvl != null) {
                transcEffect.addWeaponPower(WeaponPowerByTranscendence.findWeaponPowerByLevel(transcLvl));
                if (transcGrade >= 5) {
                    transcEffect.addAttackPower(800);
                    transcEffect.addBrandPower(2);
                }
                if (transcGrade >= 10) {
                    transcEffect.addAttackPower(800);
                    transcEffect.addApBuffEfficiency(2);
                }
                if (transcGrade >= 15) {
                    transcEffect.addAttackPower(800);
                    transcEffect.addBrandPower(2);
                }
                if (transcGrade >= 20) {
                    transcEffect.addAttackPower(1125);
                    transcEffect.addBrandPower(4);
                }
            } else if (baseArmory.getClass().equals(Armor.class) && transcLvl != null) {
                transcEffect.addMainStat(MainStatByTranscendence.findMainStatByLevel(transcLvl));
                if (baseArmory.getType().contains("머리")) {
                    if (transcGrade >= 5) {
                        transcEffect.addHp(80 * totalTranscendence);
                        transcEffect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (transcGrade >= 10) {
                        transcEffect.addMainStat(55 * totalTranscendence);
                        transcEffect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (transcGrade >= 15) {
                        transcEffect.addWeaponPower(14 * totalTranscendence);
                        transcEffect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (transcGrade >= 20) {
                        transcEffect.addAttackPower(6 * totalTranscendence);
                        transcEffect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                } else if (baseArmory.getType().contains("어깨")) {
                    if (transcGrade >= 5) {
                        transcEffect.addWeaponPower(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                    if (transcGrade >= 10) {
                        transcEffect.addMagDefense(1800);
                    }
                    if (transcGrade >= 15) {
                        transcEffect.addWeaponPower(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                    if (transcGrade >= 20) {
                        transcEffect.addWeaponPower(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                } else if (baseArmory.getType().contains("상의")) {
                    if (transcGrade >= 5) {
                        transcEffect.addWeaponPower(2000);
                    }
                    if (transcGrade >= 10) {
                        transcEffect.addVitality(1400);
                    }
                    if (transcGrade >= 15) {
                        transcEffect.addWeaponPower(2000);
                    }
                    if (transcGrade >= 20) {
                        transcEffect.addWeaponPower(3200);
                    }
                } else if (baseArmory.getType().contains("하의")) {
                    if (transcGrade >= 5) {
                        transcEffect.addDmgReduction(3);
                    }
                    if (transcGrade >= 10) {
                        transcEffect.addSuccessorStrength(14000000);
                        transcEffect.addApBuffEfficiency(1.5);
                    }
                    if (transcGrade >= 15) {
                        transcEffect.addSuccessorStrength(14000000);
                        transcEffect.addApBuffEfficiency(1.5);
                    }
                    if (transcGrade >= 20) {
                        transcEffect.addOutgoingDmg(1.5);
                        transcEffect.addApBuffEfficiency(3);
                    }
                } else if (baseArmory.getType().contains("장갑")) {
                    if (transcGrade >= 5) {
                        transcEffect.addMainStat(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                    if (transcGrade >= 10) {
                        transcEffect.addPhyDefense(1800);
                    }
                    if (transcGrade >= 15) {
                        transcEffect.addMainStat(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                    if (transcGrade >= 20) {
                        transcEffect.addMainStat(1200);
                        transcEffect.addApBuffEfficiency(1);
                    }
                } else {
                    throw new IllegalStateException("Unexpected value: " + baseArmory.getType());
                }
            }
            transcEffects.add(transcEffect);
        }

        return transcEffects.stream().reduce(new TranscendenceEffect(), TranscendenceEffect::merge);
    }

    public ElixirEffect calculateElixirEffect(List<BaseArmory> baseArmories) {
        List<ElixirEffect> elixirEffects = new ArrayList<>();
        String helmElixirSet = null;
        String glovesElixirSet = null;
        int totalElixirLvl = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class)) {
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                ElixirEffect elixirEffect = new ElixirEffect();
                elixirEffect.setArmoryType(baseArmory.getType());
                for (ElixirData elixirData : elixirDataList) {
                    String effectName = elixirData.getEffectName();
                    totalElixirLvl += elixirData.getLevel();
                    if (effectName.equals("힘") || effectName.equals("민첩") || effectName.equals("지능")) {
                        elixirEffect.addMainStat(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("무기 공격력")) {
                        elixirEffect.addWeaponPower(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("공격력 ")) {
                        elixirEffect.addAttackPower(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("보스 피해")) {
                        elixirEffect.addOutgoingDmg(elixirData.getEffect());
                    }
                    if (effectName.equals("추가 피해")) {
                        elixirEffect.addAddDmg(elixirData.getEffect());
                    }
                    if (effectName.equals("치명타 피해")) {
                        elixirEffect.addCritDmg(elixirData.getEffect());
                    }
                    if (effectName.equals("받는 피해 감소")) {
                        elixirEffect.addDmgReduction(elixirData.getEffect());
                    }
                    if (effectName.equals("물리 방어력")) {
                        elixirEffect.addPhyDefense(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("마법 방어력")) {
                        elixirEffect.addMagDefense(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("최대 생명력")) {
                        elixirEffect.addMaxHP(elixirData.getEffect().intValue());
                    }
                    if (effectName.equals("아군 강화")) {
                        elixirEffect.addAtkBuffEfficiency(elixirData.getEffect());
                    }
                    if (effectName.equals("보호막 강화")) {
                        elixirEffect.addShieldEnhance(elixirData.getEffect());
                    }
                    if (effectName.equals("회복 강화")) {
                        elixirEffect.addHealingEnhance(elixirData.getEffect());
                    }
                    if (elixirData.getArmoryType().contains("머리")) {
                        Integer level = elixirData.getLevel();
                        if (effectName.equals("회심 (질서)") || effectName.equals("달인 (질서)")) {
                            List<Double> statByLevel = ElixirSetDeal.findStatByLevel(level);
                            elixirEffect.addAttackPowerPercent(statByLevel.get(1));
                            elixirEffect.addPhyDefense(statByLevel.get(2).intValue());
                            helmElixirSet = effectName;
                        }
                        if (effectName.equals("선각자 (질서)") || effectName.equals("진군 (질서)")) {
                            List<Double> statByLevel = ElixirSetSup.findStatByLevel(level);
                            elixirEffect.addAtkBuffEfficiency(statByLevel.get(1));
                            elixirEffect.addMaxHP(statByLevel.get(2).intValue());
                            helmElixirSet = effectName;
                        }
                    }
                    if (elixirData.getArmoryType().contains("장갑")) {
                        Integer level = elixirData.getLevel();
                        if (effectName.equals("회심 (혼돈)") || effectName.equals("달인 (혼돈)")) {
                            List<Double> statByLevel = ElixirSetDeal.findStatByLevel(level);
                            elixirEffect.addOutgoingDmg(statByLevel.get(0));
                            elixirEffect.addMagDefense(statByLevel.get(2).intValue());
                            glovesElixirSet = effectName;
                        }
                        if (effectName.equals("선각자 (혼돈)") || effectName.equals("진군 (혼돈)")) {
                            List<Double> statByLevel = ElixirSetSup.findStatByLevel(level);
                            elixirEffect.addHealingEnhance(statByLevel.get(0));
                            elixirEffect.addShieldEnhance(statByLevel.get(0));
                            elixirEffect.addHPRegen(statByLevel.get(3).intValue());
                            glovesElixirSet = effectName;
                        }
                    }
                }
                elixirEffects.add(elixirEffect);
            }
        }

        ElixirEffect totalElixirEffect = elixirEffects.stream().reduce(new ElixirEffect(), ElixirEffect::merge);
        totalElixirEffect.setArmoryType("SUM");
        totalElixirEffect.setTotalLvl(totalElixirLvl);

        String helmSetEffectName = helmElixirSet.split(" ")[0];
        String glovesSetEffectName = glovesElixirSet.split(" ")[0];

        if (helmElixirSet.contains("질서")
                && glovesElixirSet.contains("혼돈")
                && Objects.equals(helmSetEffectName, glovesSetEffectName)) {
            switch (helmSetEffectName) {
                case "회심" -> {
                    totalElixirEffect.setSetEffect("회심");
                    if (totalElixirLvl >= 35)
                        totalElixirEffect.addOutGoingDmgWhenCrit(6);
                    if (totalElixirLvl >= 40)
                        totalElixirEffect.addOutGoingDmgWhenCrit(6);
                }
                case "달인" -> {
                    totalElixirEffect.setSetEffect("달인");
                    if (totalElixirLvl >= 35)
                        totalElixirEffect.addCritRate(7);
                    if (totalElixirLvl >= 40)
                        totalElixirEffect.addAddDmg(8.5);
                }
                case "선각자" -> {
                    totalElixirEffect.setSetEffect("선각자");
                    if (totalElixirLvl >= 35)
                        totalElixirEffect.addAtkBuffEfficiency(8);
                    if (totalElixirLvl >= 40) {
                        totalElixirEffect.addCoolDownReduction(5);
                        totalElixirEffect.addAtkBuffEfficiency(6);
                    }
                }
                case "진군" -> {
                    totalElixirEffect.setSetEffect("진군");
                    if (totalElixirLvl >= 35)
                        totalElixirEffect.addAdvanceEtherWeaponPower(2230);
                    if (totalElixirLvl >= 40)
                        totalElixirEffect.addAtkBuffEfficiency(6);
                }
            }
        }
        return totalElixirEffect;
    }

    public void calculateSubEquipmentEffect(List<SubEquipment> subEquipments) {

        List<AccessoryEffect> accessoryEffects = new ArrayList<>();

        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class)) {
                AccessoryEffect accessoryEffect = new AccessoryEffect();
                BasicEffect basicEffect = ((Accessory) subEquipment).getBasicEffect();
                accessoryEffect.setMainStat(basicEffect.getMainStat());
                accessoryEffect.setVitality(basicEffect.getVitality());
                // T4일 경우 연마 효과를 계산함
                if (subEquipment.getTier() == 4) {
                    List<HoneEffect> honeEffects = ((Accessory) subEquipment).getHoneEffects();
                    for (HoneEffect honeEffect : honeEffects) {
                        String name = honeEffect.getName();
                        String effect = honeEffect.getEffect();
                        if (name.equals("공격력")) {
                            if (effect.contains("%")) {
                                accessoryEffect.setAtkPowerPercent(Double.parseDouble(effect.replace("%", "")));
                            }
                            else {
                                accessoryEffect.setAtkPower(Integer.parseInt(effect));
                            }
                        }
                        if (name.equals("무기 공격력")) {
                            if (effect.contains("%")) {
                                accessoryEffect.setWeaponPowerPercent(Double.parseDouble(effect.replace("%", "")));
                            }
                            else {
                                accessoryEffect.setWeaponPower(Integer.parseInt(effect));
                            }
                        }
                        if (name.equals("최대 생명력")) {
                            accessoryEffect.setMaxHP(Integer.parseInt(effect));
                        }
                        if (name.equals("최대 마나")) {
                            accessoryEffect.setMaxMP(Integer.parseInt(effect));
                        }
                        if (name.equals("전투 중 생명력 회복량")) {
                            accessoryEffect.setHpRecovery(Integer.parseInt(effect));
                        }
                        if (name.equals("상태이상 공격 지속시간")) {
                            accessoryEffect.setStatusEffectDuration(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("적에게 주는 피해")) {
                            accessoryEffect.setOutgoingDmg(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("추가 피해")) {
                            accessoryEffect.setAddDmg(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("낙인력")) {
                            accessoryEffect.setBrandPower(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("세레나데, 신성, 조화 게이지 획득량 증가")) {
                            accessoryEffect.setSupIdentitiyGain(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("파티원 보호막 효과")) {
                            accessoryEffect.setShieldEnhance(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("파티원 회복 효과")) {
                            accessoryEffect.setHealingEnhance(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("치명타 적중률")) {
                            accessoryEffect.setCritRate(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("치명타 피해")) {
                            accessoryEffect.setCritDmg(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("이군 공격력 강화 효과")) {
                            accessoryEffect.setApBuffEfficiency(Double.parseDouble(effect.replace("%", "")));
                        }
                        if (name.equals("아군 피해량 강화 효과")) {
                            accessoryEffect.setDmgBuffEfficiency(Double.parseDouble(effect.replace("%", "")));
                        }
                    }
                }
                accessoryEffects.add(accessoryEffect);
            }
            if (subEquipment.getClass().equals(Bracelet.class)) {
                List<BraceletData> braceletData = ((Bracelet) subEquipment).getBraceletData();
                System.out.println("braceletData = " + braceletData);
            }
        }

        AccessoryEffect totalAccessoryEffect = accessoryEffects.stream().reduce(new AccessoryEffect(), AccessoryEffect::merge);
        System.out.println("totalAccessoryEffect = " + totalAccessoryEffect);
    }
}
