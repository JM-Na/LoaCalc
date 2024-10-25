package jmna.loacalc.calculator;

import jmna.loacalc.processor.GemProcessor;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.Accessory;
import jmna.loacalc.processor.equipment.accessory.ElixirEffect;
import jmna.loacalc.processor.equipment.accessory.HoneEffect;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.sqrt;

@Component
@RequiredArgsConstructor
public class AttackPowerCalculator {

    private final MainStatCalculator mainStatCalculator;
    private final WeaponPowerCalculator weaponPowerCalculator;
    private final EquipmentProcessor equipmentProcessor;
    private final GemProcessor gemProcessor;

    public void calculateBasicAttackPower(String characterName) {
        Double mainStat = (double) mainStatCalculator.calculateMainStat(characterName);
        Double weaponPower = (double) weaponPowerCalculator.calculateTotalWeaponPower(characterName);

        // 무기 공격력과 주 스탯으로 계산한 기본 공격력
        int basicAttackPower = (int) sqrt(((mainStat) * (weaponPower+1696)) / 6.0);
        System.out.println("basicAttackPower = " + basicAttackPower);
        // 4티어 보석의 기본 공격력 증가 수치를 반영한 기본 공격력
        double basicAttackPower2 = calculateBasicAttackPowerIncreaseByGem(characterName, basicAttackPower);
        System.out.println("basicAttackPower2 = " + basicAttackPower2);
        // 공격력 증가량
        double attackPowerIncrease = calculateAttackPowerIncrease(characterName, basicAttackPower2);
        System.out.println("attackPowerIncrease = " + attackPowerIncrease);

        int finalAttackPower = (int) (basicAttackPower2 + attackPowerIncrease);
        System.out.println("finalAttackPower = " + finalAttackPower);
    }

    public double calculateBasicAttackPowerIncreaseByGem(String characterName, int basicAttackPower) {
        double basicWeaponPowerIncrease = gemProcessor.getGemBasicWeaponPowerIncrease(characterName);

        return basicAttackPower * (100 + basicWeaponPowerIncrease) / 100;
    }

    public double calculateAttackPowerIncrease(String characterName, double basicAttackPower2) {
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(characterName);
        Integer totalTranscendence = characterEquipment.getTotalTranscendence();
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        int sum = 0;
        double percentSum = 0;

        for (BaseArmory baseArmory : baseArmories) {
            Integer grade = baseArmory.getTranscendenceGrade();
            if (baseArmory.getClass().equals(Weapon.class) && grade != null) {
                if (grade >= 5)
                    sum += 800;
                if (grade >= 10)
                    sum += 800;
                if (grade >= 15)
                    sum += 800;
                if (grade >= 20)
                    sum += 1125;
            } else if (baseArmory.getClass().equals(Armor.class)) {
                if (baseArmory.getType().contains("머리") && grade != null) {
                    if (grade >= 20)
                        sum += 6 * totalTranscendence;
                }
                List<ElixirEffect> elixirEffects = ((Armor) baseArmory).getElixirEffects();
                for (ElixirEffect elixirEffect : elixirEffects) {
                    if (elixirEffect.getEffectName().equals("공격력")) {
                        Double effect = elixirEffect.getEffect();
                        sum += effect;
                    }
                    if (elixirEffect.getEffectName().contains("(질서)")) {
                        Double effect = elixirEffect.getEffect();
                        percentSum += effect;
                    }
                }
            }
        }

        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class) && subEquipment.getTier() == 4) {
                List<HoneEffect> honeEffects = ((Accessory) subEquipment).getHoneEffects();
                for (HoneEffect honeEffect : honeEffects) {
                    if (honeEffect.getName().equals("공격력")) {
                        String effect = honeEffect.getEffect();
                        if (effect.contains("%")) {
                            percentSum += Double.parseDouble(effect.replace("%", ""));
                        } else {
                            sum += Integer.parseInt(effect);
                        }
                    }
                }
            }
        }

        double result = ((basicAttackPower2 + sum) * percentSum / 100) + sum;

        return result;
    }

}
