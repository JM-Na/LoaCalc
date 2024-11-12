package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.elixir.ElixirType;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.subequipments.BraceletEffect;
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
                applyWeaponTranscEffect(transcGrade, transcEffect);
            } else if (baseArmory.getClass().equals(Armor.class) && transcLvl != null) {
                transcEffect.addMainStat(MainStatByTranscendence.findMainStatByLevel(transcLvl));
                switch (baseArmory.getType()) {
                    case "머리" -> applyHeadTranscEffect(totalTranscendence, transcGrade, transcEffect);
                    case "상의" -> applyChestTranscEffect(transcGrade, transcEffect);
                    case "하의" -> applyPantsTranscEffect(transcGrade, transcEffect);
                    case "장갑" -> applyGlovesTranscEffect(transcGrade, transcEffect);
                    case "어깨" -> applyShoulderTranscEffect(transcGrade, transcEffect);
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

        applyElixirSetEffect(totalElixirLvl, totalElixirEffect);
        return totalElixirEffect;
    }

    public AccessoryEffect calculateAccessoryEffect(List<SubEquipment> subEquipments) {

        List<AccessoryEffect> accessoryEffects = new ArrayList<>();

        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment instanceof Accessory accessory) {
                AccessoryEffect accessoryEffect = new AccessoryEffect();
                BasicEffect basicEffect = accessory.getBasicEffect();
                accessoryEffect.setMainStat(basicEffect.getMainStat());
                accessoryEffect.setVitality(basicEffect.getVitality());
                // T4일 경우 연마 효과를 계산함
                if (subEquipment.getTier() == 4) {
                    for (HoneEffect honeEffect : accessory.getHoneEffects()) {
                        applyAccessoryHoneEffect(accessoryEffect, honeEffect.getName(), honeEffect.getEffect());
                    }
                }
                accessoryEffects.add(accessoryEffect);
            }
            if (subEquipment instanceof Bracelet bracelet) {
                calculateBraceletEffect(bracelet.getBraceletData());
            }
        }

        AccessoryEffect totalAccessoryEffect = accessoryEffects.stream().reduce(new AccessoryEffect(), AccessoryEffect::merge);
        System.out.println("totalAccessoryEffect = " + totalAccessoryEffect);
        return totalAccessoryEffect;
    }

    private static void applyAccessoryHoneEffect(AccessoryEffect accessoryEffect, String name, String effect) {
        if (effect.contains("%")) {
            double parsedEffect = Double.parseDouble(effect.replace("%", ""));
            switch (name) {
                case "공격력" -> accessoryEffect.setAtkPowerPercent(parsedEffect);
                case "무기 공격력" -> accessoryEffect.setWeaponPowerPercent(parsedEffect);
                case "상태이상 공격 지속시간" -> accessoryEffect.setStatusEffectDuration(parsedEffect);
                case "적에게 주는 피해" -> accessoryEffect.setOutgoingDmg(parsedEffect);
                case "추가 피해" -> accessoryEffect.setAddDmg(parsedEffect);
                case "낙인력" -> accessoryEffect.setBrandPower(parsedEffect);
                case "세레나데, 신성, 조화 게이지 획득량 증가" -> accessoryEffect.setSupIdentityGain(parsedEffect);
                case "파티원 보호막 효과" -> accessoryEffect.setShieldEnhance(parsedEffect);
                case "파티원 회복 효과" -> accessoryEffect.setHealingEnhance(parsedEffect);
                case "치명타 적중률" -> accessoryEffect.setCritRate(parsedEffect);
                case "치명타 피해" -> accessoryEffect.setCritDmg(parsedEffect);
                case "이군 공격력 강화 효과" -> accessoryEffect.setApBuffEfficiency(parsedEffect);
                case "아군 피해량 강화 효과" -> accessoryEffect.setDmgBuffEfficiency(parsedEffect);
            }
        } else {
            int parsedEffect = Integer.parseInt(effect);
            switch (name) {
                case "공격력" -> accessoryEffect.setAtkPower(parsedEffect);
                case "무기 공격력" -> accessoryEffect.setWeaponPower(parsedEffect);
                case "최대 생명력" -> accessoryEffect.setMaxHP(parsedEffect);
                case "최대 마나" -> accessoryEffect.setMaxMP(parsedEffect);
                case "전투 중 생명력 회복량" -> accessoryEffect.setHpRecovery(parsedEffect);
            }
        }
    }

    public void calculateBraceletEffect(List<BraceletData> braceletData) {
        List<BraceletEffect> braceletEffectList = new ArrayList<>();
        for (BraceletData braceletDatum : braceletData) {
            BraceletEffect braceletEffect = new BraceletEffect();
            if (braceletDatum.getIsHidden()) {
                if (braceletDatum.getTier() == 3)
                    BraceletEffectT3.applyBraceletHiddenEffect(braceletDatum, braceletEffect);
//                else if (braceletDatum.getTier() == 4)
            } else {
                String name = braceletDatum.getName();
                String effect = braceletDatum.getEffect();
                switch (name) {
                    case "힘", "민첩", "지능" -> braceletEffect.setMainStat(Integer.parseInt(effect));
                    case "치명" -> braceletEffect.setCrit(Integer.parseInt(effect));
                    case "특화" -> braceletEffect.setSpecialize(Integer.parseInt(effect));
                    case "신속" -> braceletEffect.setSwift(Integer.parseInt(effect));
                    case "무기 공격력" -> braceletEffect.addWeaponPower(Integer.parseInt(effect));
                    case "체력" -> braceletEffect.setVitality(Integer.parseInt(effect));
                }
            }
            braceletEffectList.add(braceletEffect);
        }
    }


    private static void applyWeaponTranscEffect(Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyGlovesTranscEffect(Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyPantsTranscEffect(Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyChestTranscEffect(Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyShoulderTranscEffect(Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyHeadTranscEffect(int totalTranscendence, Integer transcGrade, TranscendenceEffect transcEffect) {
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
    }

    private static void applyElixirSetEffect(int totalElixirLvl, ElixirEffect totalElixirEffect) {
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
    }
}
