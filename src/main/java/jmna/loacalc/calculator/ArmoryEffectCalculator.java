package jmna.loacalc.calculator;

import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.Weapon;

import java.util.List;

public class ArmoryEffectCalculator {

    public void test(CharacterEquipment characterEquipment) {

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();

    }

    public void test2(List<BaseArmory> baseArmories) {
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getTranscendenceLvl() != null) {

            }
        }
    }

    public void calculateTranscendenceEffect(BaseArmory baseArmory, int totalTranscendence) {
        Integer lvl = baseArmory.getTranscendenceLvl();
        Integer grade = baseArmory.getTranscendenceGrade();

        TranscendenceEffect effect = new TranscendenceEffect();
        effect.setArmoryType(baseArmory.getType());

        if (baseArmory.getClass().equals(Weapon.class)) {
            effect.addWeaponPower(WeaponPowerByTranscendence.findWeaponPowerByLevel(lvl));
            if (grade >= 5) {
                effect.addAttackPower(800);
                effect.addBrandPower(2);
            }
            if (grade >= 10) {
                effect.addAttackPower(800);
                effect.addApBuffEfficiency(2);
            }
            if (grade >= 15) {
                effect.addAttackPower(800);
                effect.addBrandPower(2);
            }
            if (grade >= 20) {
                effect.addAttackPower(1125);
                effect.addBrandPower(4);
            }
        }
        else if (baseArmory.getClass().equals(Armor.class)) {
            effect.addMainStat(MainStatByTranscendence.findMainStatByLevel(lvl));
            switch (baseArmory.getType()) {
                case "머리" -> {
                    if (grade >= 5) {
                        effect.addHp(80 * totalTranscendence);
                        effect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (grade >= 10) {
                        effect.addMainStat(55 * totalTranscendence);
                        effect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (grade >= 15) {
                        effect.addWeaponPower(14 * totalTranscendence);
                        effect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                    if (grade >= 20) {
                        effect.addAttackPower(6 * totalTranscendence);
                        effect.addApBuffEfficiency(0.01 * totalTranscendence);
                    }
                }
                case "어깨" -> {
                    if (grade >= 5) {
                        effect.addWeaponPower(1200);
                        effect.addApBuffEfficiency(1);
                    }
                    if (grade >= 10) {
                        effect.addMagDefense(1800);
                    }
                    if (grade >= 15) {
                        effect.addWeaponPower(1200);
                        effect.addApBuffEfficiency(1);
                    }
                    if (grade >= 20) {
                        effect.addWeaponPower(1200);
                        effect.addApBuffEfficiency(1);
                    }
                }
                case "상의" -> {
                    if (grade >= 5) {
                        effect.addWeaponPower(2000);
                    }
                    if (grade >= 10) {
                        effect.addVitality(1400);
                    }
                    if (grade >= 15) {
                        effect.addWeaponPower(2000);
                    }
                    if (grade >= 20) {
                        effect.addWeaponPower(3200);
                    }
                }
                case "하의" -> {
                    if (grade >= 5) {
                        effect.addDmgReduction(3);
                    }
                    if (grade >= 10) {
                        effect.addSuccessorStrength(14000000);
                        effect.addApBuffEfficiency(1.5);
                    }
                    if (grade >= 15) {
                        effect.addSuccessorStrength(14000000);
                        effect.addApBuffEfficiency(1.5);
                    }
                    if (grade >= 20) {
                        effect.addOutgoingDmg(1.5);
                        effect.addApBuffEfficiency(3);
                    }
                }
                case "장갑" -> {
                    if (grade >= 5) {
                        effect.addMainStat(1200);
                        effect.addApBuffEfficiency(1);
                    }
                    if (grade >= 10) {
                        effect.addPhyDefense(1800);
                    }
                    if (grade >= 15) {
                        effect.addMainStat(1200);
                        effect.addApBuffEfficiency(1);
                    }
                    if (grade >= 20) {
                        effect.addMainStat(1200);
                        effect.addApBuffEfficiency(1);
                    }
                }
            }
        }
    }
}
