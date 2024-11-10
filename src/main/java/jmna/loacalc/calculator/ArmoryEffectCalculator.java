package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.elixir.ElixirType;
import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.ElixirData;
import jmna.loacalc.processor.equipment.armory.Weapon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArmoryEffectCalculator {

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
        int totalElixirLvl = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class)) {
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                ElixirEffect elixirEffect = new ElixirEffect();
                elixirEffect.setArmoryType(baseArmory.getType());
                for (ElixirData elixirData : elixirDataList) {
                    String effectName = elixirData.getEffectName();
                    totalElixirLvl += elixirData.getLevel();

                    ElixirType.applyEffect(effectName, elixirEffect, elixirData);

                }
                elixirEffects.add(elixirEffect);
            }
        }
        ElixirEffect totalElixirEffect = elixirEffects.stream().reduce(new ElixirEffect(), ElixirEffect::merge);

        switch (totalElixirEffect.getSetEffect()) {
            case "회심" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addOutGoingDmgWhenCrit(6);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addOutGoingDmgWhenCrit(6);
            }
            case "달인" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addCritRate(7);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addAddDmg(8.5);
            }
            case "선각자" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addAtkBuffEfficiency(8);
                if (totalElixirLvl >= 40) {
                    totalElixirEffect.addCoolDownReduction(5);
                    totalElixirEffect.addAtkBuffEfficiency(6);
                }
            }
            case "진군" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addAdvanceEtherWeaponPower(2230);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addAtkBuffEfficiency(6);
            }
        }
        return totalElixirEffect;
    }

    public AccessoryEffect calculateAccessoryEffect(List<SubEquipment> subEquipments) {

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
                        switch (name) {
                            case "공격력" -> {
                                if (effect.contains("%")) {
                                    accessoryEffect.setAtkPowerPercent(Double.parseDouble(effect.replace("%", "")));
                                } else {
                                    accessoryEffect.setAtkPower(Integer.parseInt(effect));
                                }
                            }
                            case "무기 공격력" -> {
                                if (effect.contains("%")) {
                                    accessoryEffect.setWeaponPowerPercent(Double.parseDouble(effect.replace("%", "")));
                                } else {
                                    accessoryEffect.setWeaponPower(Integer.parseInt(effect));
                                }
                            }
                            case "최대 생명력" -> accessoryEffect.setMaxHP(Integer.parseInt(effect));
                            case "최대 마나" -> accessoryEffect.setMaxMP(Integer.parseInt(effect));
                            case "전투 중 생명력 회복량" -> accessoryEffect.setHpRecovery(Integer.parseInt(effect));
                            case "상태이상 공격 지속시간" ->
                                    accessoryEffect.setStatusEffectDuration(Double.parseDouble(effect.replace("%", "")));
                            case "적에게 주는 피해" ->
                                    accessoryEffect.setOutgoingDmg(Double.parseDouble(effect.replace("%", "")));
                            case "추가 피해" -> accessoryEffect.setAddDmg(Double.parseDouble(effect.replace("%", "")));
                            case "낙인력" -> accessoryEffect.setBrandPower(Double.parseDouble(effect.replace("%", "")));
                            case "세레나데, 신성, 조화 게이지 획득량 증가" ->
                                    accessoryEffect.setSupIdentityGain(Double.parseDouble(effect.replace("%", "")));
                            case "파티원 보호막 효과" ->
                                    accessoryEffect.setShieldEnhance(Double.parseDouble(effect.replace("%", "")));
                            case "파티원 회복 효과" ->
                                    accessoryEffect.setHealingEnhance(Double.parseDouble(effect.replace("%", "")));
                            case "치명타 적중률" -> accessoryEffect.setCritRate(Double.parseDouble(effect.replace("%", "")));
                            case "치명타 피해" -> accessoryEffect.setCritDmg(Double.parseDouble(effect.replace("%", "")));
                            case "이군 공격력 강화 효과" ->
                                    accessoryEffect.setApBuffEfficiency(Double.parseDouble(effect.replace("%", "")));
                            case "아군 피해량 강화 효과" ->
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
        return totalAccessoryEffect;
    }

    public void calculateBraceletEffect(Bracelet bracelet) {

    }
}
