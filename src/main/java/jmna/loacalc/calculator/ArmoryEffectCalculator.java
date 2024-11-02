package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.elixir.ElixirSet;
import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.ElixirData;
import jmna.loacalc.processor.equipment.armory.Weapon;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArmoryEffectCalculator {

    public void test(CharacterEquipment characterEquipment) {

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTransc = characterEquipment.getTotalTranscendence();

    }

    public TotalEffect calculateTotalArmoryEffect(List<BaseArmory> baseArmories, int totalTransc) {

        List<TranscendenceEffect> transcendenceEffects = new ArrayList<>();
        List<ElixirEffect> elixirEffects = new ArrayList<>();

        ArmoryEffect armoryEffect = calculateArmoryEffect(baseArmories);

        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getTranscendenceLvl() != null) {
                transcendenceEffects.add(calculateTranscEffect(baseArmory, totalTransc));
            }
            if (baseArmory.getClass().equals(Armor.class)) {
                elixirEffects.add(calculateElixirEffect((Armor) baseArmory));
            }
        }
        TranscendenceEffect totalTranscEffect = transcendenceEffects.stream().reduce(new TranscendenceEffect(), TranscendenceEffect::merge);
        ElixirEffect totalElixirEffect = elixirEffects.stream().reduce(new ElixirEffect(), ElixirEffect::merge);

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

    public TranscendenceEffect calculateTranscEffect(BaseArmory baseArmory, int totalTranscendence) {
        Integer transcLvl = baseArmory.getTranscendenceLvl();
        Integer transcGrade = baseArmory.getTranscendenceGrade();

        TranscendenceEffect transcEffect = new TranscendenceEffect();
        transcEffect.setArmoryType(baseArmory.getType());

        if (baseArmory.getClass().equals(Weapon.class)) {
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
        }
        else if (baseArmory.getClass().equals(Armor.class)) {
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

        return transcEffect;
    }

    // TODO 엘릭서 세트 효과를 계산하는 코드가 필요함. ex) 회심, 선각자 등
    public ElixirEffect calculateElixirEffect(Armor armor) {
        List<ElixirData> elixirDataList = armor.getElixirData();
        ElixirEffect elixirEffect = new ElixirEffect();
        elixirEffect.setArmoryType(armor.getType());
        for (ElixirData elixirData : elixirDataList) {
            String effectName = elixirData.getEffectName();
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
            if (elixirData.getPart().equals("투구")){
                Integer level = elixirData.getLevel();
                if(effectName.contains("회심") || effectName.contains("달인")){
                    List<Double> statByLevel = ElixirSet.findStatByLevel("deal", level);
                    elixirEffect.addOutgoingDmg(statByLevel.get(0));
                    elixirEffect.addAttackPowerPercent(statByLevel.get(1));
                    elixirEffect.addMagDefense(statByLevel.get(2).intValue());
                }
                if(effectName.contains("선각자") || effectName.contains("진군")){
                    List<Double> statByLevel = ElixirSet.findStatByLevel("sup", level);
                    elixirEffect.addHealingEnhance(statByLevel.get(0));
                    elixirEffect.addShieldEnhance(statByLevel.get(0));
                    elixirEffect.addAtkBuffEfficiency(statByLevel.get(1));
                    elixirEffect.addHPRegen(statByLevel.get(0).intValue());
                }
            }
        }
        return elixirEffect;
    }
}
