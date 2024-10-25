package jmna.loacalc.calculator;

import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.arkpassives.Point;
import jmna.loacalc.processor.ArkpassiveProcessor;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeaponPowerCalculator {

    private final EquipmentProcessor equipmentProcessor;
    private final ArkpassiveProcessor arkpassiveProcessor;
    Integer SUM = 0;
    Double WP_PERCENT = 0.0;

    public int calculateTotalWeaponPower(String characterName) {
        CharacterEquipment equipment = equipmentProcessor.parseEquipmentInfo(characterName);
        ArmoryArkPassive arkpassiveData = arkpassiveProcessor.getArkpassiveData(characterName);

        List<BaseArmory> baseArmories = equipment.getBaseArmories();
        Integer totalTranscendence = equipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = equipment.getSubEquipments();

        calculateEquipmentWeaponPower(baseArmories);
        calculateElixirWeaponPower(baseArmories);
        calculateTranscendenceWeaponPower(baseArmories, totalTranscendence);
        getAccessoriesWeaponPower(subEquipments);
        getKarmaWeaponPower(subEquipments, arkpassiveData);

        int finalWeaponPower = (int) (SUM * ((100.0 + WP_PERCENT) / 100.0));
        System.out.println("finalWeaponPower = " + finalWeaponPower);
        return finalWeaponPower;
    }

    public void calculateEquipmentWeaponPower(List<BaseArmory> baseArmories) {
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Weapon.class)) {
                SUM += ((Weapon) baseArmory).getWeaponPower();
            }
        }
    }

    public void calculateElixirWeaponPower(List<BaseArmory> baseArmories) {
        int sum = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class) && ((Armor) baseArmory).getElixirEffects() != null) {
                List<ElixirEffect> elixirEffects = ((Armor) baseArmory).getElixirEffects();
                for (ElixirEffect elixirEffect : elixirEffects) {
                    if (elixirEffect.getEffectName().equals("무기 공격력")) {
                        Double effect = elixirEffect.getEffect();
                        sum += effect;
                    }
                }
            }
        }
        SUM += sum;
    }

    public void calculateTranscendenceWeaponPower(List<BaseArmory> baseArmories, Integer total) {
        int sum = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Weapon.class) && baseArmory.getTranscendenceLvl() != null) {
                Integer transcendenceLvl = baseArmory.getTranscendenceLvl();
                Integer weaponPower = WeaponPowerByTranscendence.findWeaponPowerByLevel(transcendenceLvl);
                sum += weaponPower;

            } else if (baseArmory.getClass().equals(Armor.class) && baseArmory.getTranscendenceLvl() != null) {
                Integer grade = baseArmory.getTranscendenceGrade();
                if (baseArmory.getType().contains("머리") && grade >= 15) {
                    sum += (14 * total);
                }
                if (baseArmory.getType().contains("어깨")) {
                    if (grade >= 5)
                        sum += 1200;
                    if (grade >= 15)
                        sum += 1200;
                    if (grade >= 20)
                        sum += 1200;
                }
                if (baseArmory.getType().contains("상의")) {
                    if (grade >= 5)
                        sum += 2000;
                    if (grade >= 15)
                        sum += 2000;
                    if (grade >= 20)
                        sum += 3200;
                }
            }
        }

        SUM += sum;
    }

    public void getAccessoriesWeaponPower(List<SubEquipment> subEquipments) {
        // 기본 5개 악세서리에는 무공 +%, 팔찌에는 고정 수치 증가가 존재한다.
        double percentSum = 0;
        int sum = 0;

        // 4티어 악세서리의 경우
        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getTier() == 3) {
                break;
            } else if (subEquipment.getClass().equals(Accessory.class)) {
                List<HoneEffect> honeEffects = ((Accessory) subEquipment).getHoneEffects();
                for (HoneEffect honeEffect : honeEffects) {
                    if (honeEffect.getName().contains("무기 공격력")) {
                        String effect = honeEffect.getEffect();
                        if (effect.contains("%")) {
                            percentSum += Double.parseDouble(effect.replace("%", ""));
                        } else {
                            sum += Integer.parseInt(effect);
                        }
                    }
                }
            } else if (subEquipment.getClass().equals(Bracelet.class)) {
                List<BraceletEffect> braceletEffects = ((Bracelet) subEquipment).getBraceletEffects();
                for (BraceletEffect braceletEffect : braceletEffects) {
                    if (braceletEffect.getName().equals("무기 공격력")) {
                        sum += Integer.parseInt(braceletEffect.getEffect());
                    }
                }
            }
        }

        SUM += sum;
        WP_PERCENT += percentSum;
    }

    public void getKarmaWeaponPower(List<SubEquipment> subEquipments, ArmoryArkPassive armoryArkPassive) {
        int arkpassivePointSum = 0;
        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class) && subEquipment.getTier() == 4) {
                Integer arkpassiveEffect = ((Accessory) subEquipment).getArkpassiveEffect();
                arkpassivePointSum += arkpassiveEffect;
            }
        }

        int value = 0;
        List<Point> points = armoryArkPassive.getPoints();
        for (Point point : points) {
            if (point.getName().equals("깨달음")) {
                value = point.getValue();
            }
        }
        // 가능한 깨달음 물약을 모두 획득하고 전투 레벨이 70이라고 가정
        if (value - arkpassivePointSum - 31 > 0)
            WP_PERCENT += ((value - arkpassivePointSum - 31) * 4 * 0.1) + 0.1;
    }
}
