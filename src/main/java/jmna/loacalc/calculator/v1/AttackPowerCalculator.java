package jmna.loacalc.calculator.v1;

import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.armory.ElixirData;
import jmna.loacalc.processor.armory.equipment.accessory.HoneEffect;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.Armor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.Math.sqrt;

@Component
@RequiredArgsConstructor
public class AttackPowerCalculator {

    public int calculateFinalAttackPower(CharacterEquipment characterEquipment, double mainStat, int weaponPower, double gemAttackPowerPercent) {

        // 무기 공격력과 주 스탯으로 계산한 기본 공격력
        int basicAttackPower = (int) sqrt(((mainStat) * (weaponPower+1696)) / 6.0);
        // 4티어 보석의 기본 공격력 증가 수치를 반영한 기본 공격력
        double basicAttackPower2 = calculateBasicAttackPowerIncreaseByGem(gemAttackPowerPercent, basicAttackPower);
        // 공격력 증가량
        double attackPowerIncrease = calculateAttackPowerIncrease(characterEquipment, basicAttackPower2);

        return (int) (basicAttackPower2 + attackPowerIncrease);
    }

    public double calculateBasicAttackPowerIncreaseByGem(double gemAttackPowerPercent, int basicAttackPower) {
        return basicAttackPower * (100 + gemAttackPowerPercent) / 100;
    }

    public double calculateAttackPowerIncrease(CharacterEquipment characterEquipment, double basicAttackPower2) {
        int totalTranscendence = characterEquipment.getTotalTranscendence();
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
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                for (ElixirData elixirData : elixirDataList) {
                    if (elixirData.getEffectName().equals("공격력")) {
                        Double effect = elixirData.getEffect();
                        sum += effect;
                    }
                    if (elixirData.getEffectName().contains("(질서)")) {
                        Double effect = elixirData.getEffect();
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

        return ((basicAttackPower2 + sum) * percentSum / 100) + sum;
    }

}
