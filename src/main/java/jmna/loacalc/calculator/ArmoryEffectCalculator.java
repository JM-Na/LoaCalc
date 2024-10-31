package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.ElixirData;
import jmna.loacalc.processor.equipment.armory.Weapon;

import java.util.List;

public class ArmoryEffectCalculator {

    public void test(CharacterEquipment characterEquipment) {

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTransc = characterEquipment.getTotalTranscendence();

    }

    public void test2(List<BaseArmory> baseArmories) {
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getTranscendenceLvl() != null) {

            }
            if (baseArmory.getClass().equals(Armor.class)) {

            }
        }
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
            switch (baseArmory.getType()) {
                case "머리" -> {
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
                case "어깨" -> {
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
                case "상의" -> {
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
                case "하의" -> {
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
                case "장갑" -> {
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
            }
        }

        return transcEffect;
    }

    public void calculateElixirEffect(Armor armor) {
        List<ElixirData> elixirDataList = armor.getElixirData();
        ElixirEffect elixirEffect = new ElixirEffect();
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

            }
            if (effectName.equals("추가 피해")) {

            }
            if (effectName.equals("치명타 피해")) {

            }
        }
    }
}
