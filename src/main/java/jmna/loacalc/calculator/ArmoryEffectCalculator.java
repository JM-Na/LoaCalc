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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                BraceletEffect braceletEffect = calculateBraceletEffect(bracelet.getBraceletData());
                //TODO 팔찌 효과를 악세서리 효과에 어떻게 추가할지?
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

    public BraceletEffect calculateBraceletEffect(List<BraceletData> braceletData) {
        List<BraceletEffect> braceletEffectList = new ArrayList<>();
        for (BraceletData braceletDatum : braceletData) {
            BraceletEffect braceletEffect = new BraceletEffect();
            if (braceletDatum.getIsHidden()) {
                if (braceletDatum.getTier() == 3)
                    BraceletEffectT3.applyBraceletHiddenEffect(braceletDatum, braceletEffect);
                else if (braceletDatum.getTier() == 4) {
                    String effect = braceletDatum.getEffect();

                    String[][] patterns = {
                            {"crit_critOutDmg", "치명타 적중률이 (\\S{1,4})% 증가한다. 공격이 치명타로 적중 시 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"OutDmg", "적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"critDmg_critOutDmg", "치명타 피해가 (\\S{1,4})% 증가한다. 공격이 치명타로 적중 시 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"OutDmg_StagDmg", "적에게 주는 피해가 (\\S{1,4})% 증가하며, 무력화 상태의 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"AddDmg_DaemonDmg", "추가 피해가 (\\S{1,4})% 증가한다. 악마 및 대악마 계열 피해량이 (\\S{1,4})% 증가한다"},
                            {"AddCool_OutDmg", "스킬의 재사용 대기 시간이 (\\S{1,4})% 증가하지만, 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"Weapon_Speed_Stack", "공격 적중 시 매 초마다 10초 동안 무기 공격력이 (\\S{1,4}), 공격 및 이동 속도가 1% 증가한다. (최대 6중첩)"},
                            {"Weapon_Stable", "무기 공격력이 (\\S{1,4}) 증가한다. 자신의 생명력이 50% 이상일 경우 적에게 공격 적중 시 5초 동안 무기 공격력이 (\\S{1,4}) 증가한다"},
                            {"Weapon_Weapon_Stack", "무기 공격력이 (\\S{1,4}) 증가한다. 공격 적중 시 30초 마다 120초 동안 무기 공격력이 (\\S{1,4}) 증가한다. (최대 30중첩)"},
                            {"BackOutDmg", "백어택 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"HeadOutDmg", "헤드어택 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                            {"NonOutDmg", "방향성 공격이 아닌 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다. 각성기는 적용되지않는다"},
                            {"AddDmg", "추가 피해 (\\S{1,4})%"},
                            {"Crit", "치명타 적중률 (\\S{1,4})%"},
                            {"CritDmg", "치명타 피해 (\\S{1,4})%"},
                            {"Weapon", "무기 공격력 (\\S{1,4})"},
                            {"ArmorReduction", "적에게 공격 적중 시 8초 동안 대상의 방어력을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                            {"CritReduction", "적에게 공격 적중 시 8초 동안 대상의 치명타 저항을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                            {"CritDmgReduction", "적에게 공격 적중 시 8초 동안 대상의 치명타 피해 저항을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                            {"BuffOutDmgReduction", "파티 효과로 보호 효과(보호막, 생명력 회복, 받는 피해 감소)가 적용된 대상이 5초 동안 적에게 주는 피해가 (\\S{1,4})% 증가한다. 해당 효과는 한 파티 당 하나만 적용된다, 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                            {"Shield_Heal", "파티원 보호 및 회복 효과가 (\\S{1,4})% 증가한다"},
                            {"Buff_Attack", "아군 공격력 강화 효과 (\\S{1,4})%"},
                            {"Buff_Dmg", "아군 피해량 강화 효과 (\\S{1,4})%"}
                    };

                    String closestVarName = null;
                    String closestPattern = null;
                    int closestStartIndex = Integer.MAX_VALUE; // 가장 작은 시작 인덱스를 찾기 위해 큰 값으로 초기화
                    String[] closestMatches = null;

                    // 모든 패턴을 순회하며 매칭
                    for (String[] patternInfo : patterns) {
                        String varName = patternInfo[0]; // 변수명
                        String regex = patternInfo[1];   // 정규식 패턴

                        Pattern compiledPattern = Pattern.compile(regex);
                        Matcher matcher = compiledPattern.matcher(effect);

                        // 매칭된 결과가 있을 때
                        while (matcher.find()) {
                            int startIndex = matcher.start(); // 매칭 시작 인덱스
                            System.out.println("startIndex = " + startIndex);

                            // 더 가까운 매칭이 발견되면 갱신
                            if (startIndex < closestStartIndex) {
                                closestStartIndex = startIndex;
                                closestVarName = varName;
                                closestPattern = regex;
                                closestMatches = new String[matcher.groupCount()];
                                for (int i = 1; i <= matcher.groupCount(); i++) {
                                    closestMatches[i - 1] = matcher.group(i);
                                }
                            }
                        }
                    }
                    if (closestVarName != null && closestMatches[0] != null) {
                        switch (closestVarName) {
                            case "crit_critOutDmg" -> {
                                braceletEffect.addCrtRate(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addOutgoingDmgWhenCrit(Double.parseDouble(closestMatches[1]));
                            }
                            case "OutDmg" -> braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[0]));
                            case "critDmg_critOutDmg" -> {
                                braceletEffect.addCritDmg(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addOutgoingDmgWhenCrit(Double.parseDouble(closestMatches[1]));
                            }
                            case "OutDmg_StagDmg" -> {
                                braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addStaggerDmg(Double.parseDouble(closestMatches[1]));
                            }
                            case "AddDmg_DaemonDmg" -> {
                                braceletEffect.addAddDmg(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addDaemonDmg(Double.parseDouble(closestMatches[1]));
                            }
                            case "AddCool_OutDmg" -> {
                                braceletEffect.addCooldown(Double.parseDouble(closestMatches[0]) * -1);
                                braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[1]));
                            }
                            case "Weapon_Speed_Stack" -> {
                                braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) * 6);
                                braceletEffect.addSpeed(6);
                            }
                            case "Weapon_Stable" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) + Integer.parseInt(closestMatches[1]));
                            case "Weapon_Weapon_Stack" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) + Integer.parseInt(closestMatches[1]) * 30);
                            case "BackOutDmg" -> braceletEffect.setBackDmg(Double.parseDouble(closestMatches[0]));
                            case "HeadOutDmg" -> braceletEffect.setHeadDmg(Double.parseDouble(closestMatches[0]));
                            case "NonOutDmg" -> braceletEffect.setHitDmg(Double.parseDouble(closestMatches[0]));
                            case "AddDmg" -> braceletEffect.addAddDmg(Double.parseDouble(closestMatches[0]));
                            case "Crit" -> braceletEffect.addCrtRate(Double.parseDouble(closestMatches[0]));
                            case "CritDmg" -> braceletEffect.addCritDmg(Double.parseDouble(closestMatches[0]));
                            case "Weapon" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]));
                            case "ArmorReduction" -> {
                                braceletEffect.setArmorReductionSynergy(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                            }
                            case "CritReduction" -> {
                                braceletEffect.setCritRateSynergy(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                            }
                            case "CritDmgReduction" -> {
                                braceletEffect.setCritDmgSynergy(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                            }
                            case "BuffOutDmgReduction" -> {
                                braceletEffect.setOutgoingDmgSynergy(Double.parseDouble(closestMatches[0]));
                                braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                            }
                            case "Shield_Heal" -> braceletEffect.addShieldHeal(Double.parseDouble(closestMatches[0]));
                            case "Buff_Attack" -> braceletEffect.addApBuff(Double.parseDouble(closestMatches[0]));
                            case "Buff_Dmg" -> braceletEffect.addDmgBuff(Double.parseDouble(closestMatches[0]));
                        }
                    }
                }
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
        BraceletEffect totalBraceletEffect = braceletEffectList.stream().reduce(new BraceletEffect(), BraceletEffect::merge);

        return totalBraceletEffect;
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
