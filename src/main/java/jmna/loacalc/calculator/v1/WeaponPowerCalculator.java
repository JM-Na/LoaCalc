package jmna.loacalc.calculator.v1;

import jmna.loacalc.calculator.transcendence.WeaponPowerByTranscendence;
import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.arkpassives.Point;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.ElixirData;
import jmna.loacalc.processor.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeaponPowerCalculator {

    public int getTotalWeaponPower(CharacterEquipment characterEquipment, ArmoryArkPassive armoryArkPassive) {

        WeaponPowerDto weaponPowerDto = calculate(characterEquipment, armoryArkPassive);

        return calculateFinalWeaponPower(weaponPowerDto);
    }

    public int calculateFinalWeaponPower(WeaponPowerDto weaponPowerDto) {
        return (int) (weaponPowerDto.getBaseWeaponPower()* ((100.0 + weaponPowerDto.getWeaponPowerPercent()) / 100.0));
    }

    public WeaponPowerDto calculateBaseWeaponPowerAndPercent(CharacterEquipment characterEquipment, ArmoryArkPassive armoryArkPassive) {
        return calculate(characterEquipment, armoryArkPassive);
    }

    public WeaponPowerDto calculate(CharacterEquipment equipment, ArmoryArkPassive armoryArkPassive) {
        List<BaseArmory> baseArmories = equipment.getBaseArmories();
        Integer totalTranscendence = equipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = equipment.getSubEquipments();

        int wpSum = 0;
        double wpPercent = 0;

        wpSum += calculateEquipmentWeaponPower(baseArmories);
        wpSum += calculateElixirWeaponPower(baseArmories);
        wpSum += calculateTranscendenceWeaponPower(baseArmories, totalTranscendence);
        WeaponPowerDto weaponPowerDto = getAccessoriesWeaponPower(subEquipments);
        wpSum += weaponPowerDto.getBaseWeaponPower();
        wpPercent += weaponPowerDto.getWeaponPowerPercent();
        wpPercent += getKarmaWeaponPower(subEquipments, armoryArkPassive);

        return new WeaponPowerDto(wpSum, wpPercent);
    }

    public int calculateEquipmentWeaponPower(List<BaseArmory> baseArmories) {
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Weapon.class)) {
                return ((Weapon) baseArmory).getWeaponPower();
            }
        }
        return 0;
    }

    public int calculateElixirWeaponPower(List<BaseArmory> baseArmories) {
        int sum = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class) && ((Armor) baseArmory).getElixirData() != null) {
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                for (ElixirData elixirData : elixirDataList) {
                    if (elixirData.getEffectName().equals("무기 공격력")) {
                        Double effect = elixirData.getEffect();
                        sum += effect;
                    }
                }
            }
        }
        return sum;
    }

    public int calculateTranscendenceWeaponPower(List<BaseArmory> baseArmories, Integer total) {
        int sum = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Weapon.class) && baseArmory.getTranscendenceLvl() != null) {
                Integer transcendenceLvl = baseArmory.getTranscendenceLvl();
                Integer weaponPower = WeaponPowerByTranscendence.findWeaponPowerByLevel(transcendenceLvl);
                if (weaponPower != null) {
                    sum += weaponPower;
                }
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

        return sum;
    }

    public WeaponPowerDto getAccessoriesWeaponPower(List<SubEquipment> subEquipments) {
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
                List<BraceletData> braceletDataList = ((Bracelet) subEquipment).getBraceletData();
                for (BraceletData braceletData : braceletDataList) {
                    if (braceletData.getName().equals("무기 공격력")) {
                        sum += Integer.parseInt(braceletData.getEffect());
                    }
                }
            }
        }

        return new WeaponPowerDto(sum, percentSum);
    }

    public double getKarmaWeaponPower(List<SubEquipment> subEquipments, ArmoryArkPassive armoryArkPassive) {
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
            return ((value - arkpassivePointSum - 31) * 4 * 0.1) + 0.1;
        return 0;
    }
}
