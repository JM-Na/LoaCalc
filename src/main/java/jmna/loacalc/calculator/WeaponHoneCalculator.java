package jmna.loacalc.calculator;

import jmna.loacalc.calculator.engraving.*;
import jmna.loacalc.calculator.hone.AdvancedHone;
import jmna.loacalc.calculator.hone.T4ArmorHone;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeaponHoneCalculator {

    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final StatEffectCalculator statEffectCalculator;

    private static final List<String> ARMOR_TYPES = List.of("머리", "어깨", "상의", "하의", "장갑");

    /**
     * 4티어 무기를 1등급 강화했을 때의 효율을 계산하는 코드
     */
    public void calculateExpectedValue(TotalArmoryEffect totalArmoryEffect, int level) {
        System.out.println("weaponLevel = " + level);

        if (level == 25) {
            log.info("무기 강화 수치가 이미 최대입니다.");
        }

        int increment = T4WeaponHone.findIncrementByTargetLevel(level);
        log.info(level + "강 도달로 증가하는 무기 공격력 수치: " + increment);
        double cost = T4WeaponHone.findCostByTargetLevel(level, true);
        log.info((level + 1) + "강을 위한 소모 골드: " + cost);

        double incrementOnAtkPower = calculateHoneIncrementSpecUp(totalArmoryEffect, "무기", increment);
    }

    /**
     * 유물 각인서를 읽었을 때의 효율을 계산하는 코드
     */
    public void calculateExpectedValueByRelicEngravingBook(TotalArmoryEffect totalArmoryEffect,
                                                           List<CharacterEngraving> characterEngravingList,
                                                           CharacterProfile characterProfile) {

        for (CharacterEngraving characterEngraving : characterEngravingList) {
            String grade = characterEngraving.getGrade();
            String name = characterEngraving.getName();
            int lvl = characterEngraving.getLvl();
            log.info("각인: " + name + ", 등급: " + grade + ", 레벨: " + lvl);
            // 각인을 4레벨까지 올린다고 가정했을 때 소모되는 골드 계산
            Double price = RelicEngravingBook.getPriceByName(name + " 각인서");
            double totalPrice = price * (4 - lvl) * 5;
            log.info("소모 각인서 갯수: " + ((4 - lvl) * 5) + ", 예상 소모 각인서 가격: " + totalPrice);
            // 해당 각인의 효과를 가져오는 코드
            double incrementByStone = EngravingAbilityStone.getIncrementValue(characterEngraving);
            EngravingEffect engravingEffect = EngravingIncrementT4.applyEngravingEffect(characterEngraving, incrementByStone);

            // 각인의 레벨에 따라 올라가는 수치를 가져오는 코드
            EngravingLvlIncrement incrementByGrade = EngravingIncrementT4.getIncrementByGrade(name, grade);
            String incrementName = incrementByGrade.getName();
            double incrementByBook = incrementByGrade.getIncrement() * (4 - lvl);

            double expectedSpecUp = 0;


            // 상승값을 계산하는 코드
            switch (incrementName) {
//                case "Awakening Cooldown" -> ;
//                case "Awakening Cast" -> ;
                case "Outgoing Damage" -> {
                    log.info("적에게 주는 피해가 상승합니다. 상승량: " + incrementByBook);
                    Double previousIncrementByEngraving = engravingEffect.getOutgoingDmg().get(0);
                    System.out.println("previousIncrementByEngraving = " + previousIncrementByEngraving);
                    System.out.println("incrementByBook = " + incrementByBook);
                    expectedSpecUp += (incrementByBook / (previousIncrementByEngraving + 100));
                }
                case "Raid Captain" -> {
                    log.info("돌격 대장이 상승합니다. 상승량: " + incrementByBook);
                    double previousIncrementByEngraving = calculateRaidCaptainEffect(totalArmoryEffect.getRaidCaptain(), calculateSpeed(totalArmoryEffect, characterProfile, 0));
                    // 상승된 값을 적용하여 돌격대장의 데미지 상승량 계산
                    totalArmoryEffect.addRaidCaptain(incrementByBook);
                    double incrementByAppliedBook = calculateRaidCaptainEffect(totalArmoryEffect.getRaidCaptain(), calculateSpeed(totalArmoryEffect, characterProfile, 0));

                    expectedSpecUp += (incrementByAppliedBook / previousIncrementByEngraving) - 1;
                }
//                case "Mp Recovery" -> ;
//                case "Cooldown Reduction" -> ;
                case "Attack Power Percent" -> {
                    log.info("공격력 퍼센트가 상승합니다. 상승량: " + incrementByBook);
                    double atkPowerPercent = totalArmoryEffect.getAtkPowerPercent();
                    expectedSpecUp += incrementByBook / (atkPowerPercent + 100);
                }
                case "Crit Rate" -> {
                    log.info("치명타 확률이 상승합니다. 상승량: " + incrementByBook);
                    double critRate = totalArmoryEffect.getCritRate() + statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
                    double critDmg = (totalArmoryEffect.getCritDmg() + 200) / 100;
                    double outgoingDmgWhenCrit = totalArmoryEffect.getOutgoingDmgWhenCrit()
                            .stream().reduce(1.0, ((a, b) -> a * (b + 1)));

                    expectedSpecUp += incrementByBook * (critDmg * outgoingDmgWhenCrit) / (critRate * critDmg * outgoingDmgWhenCrit + 100 - critRate);
                }
//                case "Defense Percent" -> ;
                case "Crit Damage" -> {
                    log.info("치명타 피해량이 상승합니다. 상승량: " + incrementByBook);
                    double critRate = totalArmoryEffect.getCritRate() + statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
                    double critDmg = (totalArmoryEffect.getCritDmg() + 200) / 100;
                    double critDmgIncrement = incrementByBook / 100;
                    double outgoingDmgWhenCrit = totalArmoryEffect.getOutgoingDmgWhenCrit()
                            .stream().reduce(1.0, ((a, b) -> a * (b + 1)));

                    expectedSpecUp += (critRate * critDmgIncrement * outgoingDmgWhenCrit) / (critRate * critDmg * outgoingDmgWhenCrit + (1 - critRate));
                }
//                case "Heal Shield Efficiency" -> ;
//                case "Speed" -> ;
            }

            System.out.println("expectedSpecUp = " + expectedSpecUp);
        }
    }

    public double calculateSpeed(TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile, Integer characterBuff) {
        double speedBySwiftness = statEffectCalculator.calculateSwiftnessSpeed(characterProfile.getSwiftness());
        double atkSpeed = totalArmoryEffect.getAtkSpeed();
        double movementSpeed = totalArmoryEffect.getMovementSpeed();

        // 9 -> 서폿 정열의 춤, 5 -> 만찬 공이속
        return 100 + speedBySwiftness + movementSpeed + characterBuff + 9 + 5;
    }

    public double calculateRaidCaptainEffect(double raidCaptain, double movementSpeed) {
        // 이동 속도의 최대치는 140%로 고정되어 있음.
        double compensatedSpeed = Math.max(140, movementSpeed) - 100;

        return compensatedSpeed * raidCaptain / 100;
    }

    /**
     *
     */
    public void calculateArmorExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) throws Exception {

        if (level % 10 != 0)
            throw new Exception("올바르지 않은 재련 목표 레벨입니다. 재련 목표레벨은 25 미만이어야 합니다.");

        int increment = T4ArmorHone.findIncrementByTargetLevel(type, level);
        log.info(level + "강 도달로 증가하는 능력치: " + increment);
        double cost = T4ArmorHone.findCostByTargetLevel(type, level, true);
        log.info((level) + "강을 위한 소모 골드: " + cost);

        double incrementOnAtkPower = calculateHoneIncrementSpecUp(totalArmoryEffect, type, increment);
    }

    public double calculateAdvancedHoneExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) throws Exception {

        if (level % 10 != 0)
            throw new Exception("올바르지 않은 상급재련 목표 레벨입니다. 상급 재련 목표레벨은 10, 20이어야 합니다.");

        int increment = AdvancedHone.findIncrementByNameAndTargetLevel(type, level);
        log.info(type + " 부위 / " + level + "강 도달로 증가하는 능력치: " + increment);
        double cost = AdvancedHone.findCostByTargetLevel(type, level, true);
        log.info(type + " 부위 " + level + "강 도달까지 소모 예상 골드: " + cost);

        return calculateHoneIncrementSpecUp(totalArmoryEffect, type, increment);
    }

    /**
     * TotalArmoryEffect, 장비 종류, 스탯 상승량을 받아서 전체 스펙업 상승량을 계산하는 코드
     */
    private double calculateHoneIncrementSpecUp(TotalArmoryEffect totalArmoryEffect, String type, int increment) {
        double preAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        if (type.equals("무기")) {
            totalArmoryEffect.addWeaponPower(increment);
        } else {
            totalArmoryEffect.addMainStat(increment);
        }

        double laterAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        double incrementOnAtkPower = (laterAtkPower - preAtkPower) / preAtkPower;
        System.out.println("incrementOnAtkPower = " + incrementOnAtkPower * 100 + "%");
        return incrementOnAtkPower;
    }

    // 방어구의 재련, 상급 재련 단계를 확인하여 각각 어떤 식으로 재련을 행할지 제시하는 코드
    public void checkHoneArmory(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {

        double v1 = calculateArmorHoneExpectedValue(baseArmories, totalArmoryEffect);
        System.out.println("v1 = " + v1);
        double v2 = calculateArmorAdvancedHoneExpectedValue(baseArmories, totalArmoryEffect);
        System.out.println("v2 = " + v2);
    }

    /**
     * 장비 데이터를 받아서 방어구의 상급재련 상승 효율을 계산하는 코드
     */
    private double calculateArmorAdvancedHoneExpectedValue(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        BaseArmory highestAdvancedHone = baseArmories.stream().skip(1).skip(1).max(Comparator.comparing(BaseArmory::getAdvancedHone)).orElse(null);


        Integer maxAdvancedHone = highestAdvancedHone.getAdvancedHone();

        int totalIncrement = 0;
        double totalCost = 0;

        // 상급 재련이 전혀 진행되지 않은 경우
        if (maxAdvancedHone == 0) {
            System.out.println("상급 재련이 진행되지 않아 모든 부위 10강을 진행함");

            totalCost += AdvancedHone.findTotalCostByTargetLevel(ARMOR_TYPES, 10, true);
            System.out.println("모든 부위를 상급재련 10강 진행한 총 비용: " + totalCost);

            totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 10);
            System.out.println("totalIncrement = " + totalIncrement);
        } else {
            List<BaseArmory> targetAdvancedArmoryList = baseArmories.stream().filter(baseArmory -> baseArmory.getAdvancedHone() < maxAdvancedHone).toList();

            // 상급 재련이 이미 완료된 경우
            if (targetAdvancedArmoryList.isEmpty()) {
                if (maxAdvancedHone==20)
                    System.out.println("상급 재련이 이미 완료됨");
                else if (maxAdvancedHone == 10) {
                    System.out.println("상급 재련이 진행되지 않아 모든 부위 20강을 진행함");
                    totalCost += AdvancedHone.findTotalCostByTargetLevel(ARMOR_TYPES, 20, true);

                    System.out.println("모든 부위를 상급재련 20강 진행한 총 비용: " + totalCost);

                    totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 20);

                    System.out.println("totalIncrement = " + totalIncrement);
                }
            } else {
                // 상급 재련이 10 미만일 시 10, 20 미만일 시 20을 제안한다.
                for (BaseArmory baseArmory : targetAdvancedArmoryList) {

                    Integer honeLvl = baseArmory.getAdvancedHone();
                    String type = baseArmory.getType();

                    int targetLevel = honeLvl < 10 ? 10 : 20;
                    System.out.println("상급재련 " + targetLevel + "강을 진행함");

                    totalCost += AdvancedHone.findCostByTargetLevel(type, targetLevel, true);
                    totalIncrement += AdvancedHone.findIncrementByNameAndTargetLevel(type, targetLevel);
                }

                System.out.println("sum = " + totalCost);
                System.out.println("incrementSum = " + totalIncrement);
            }
        }

        return calculateHoneIncrementSpecUp(totalArmoryEffect, "방어구", totalIncrement);
    }

    /**
     * 장비 데이터를 받아서 방어구의 재련 등급 상승 효율을 계산하는 코드
     */
    private double calculateArmorHoneExpectedValue(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        // 재련 단계를 순회하며 최고 레벨을 찾는다.
        // 무기를 제외하기 위해 .skip(1) 을 중간에 추가한다.
        BaseArmory highestArmor = baseArmories.stream().skip(1).max(Comparator.comparing(BaseArmory::getHoneLvl)).get();

        Integer maxLvl = highestArmor.getHoneLvl();

        // 재련 단계를 순회하며 최고 레벨보다 낮은 레벨의 장비를 찾는다.
        List<BaseArmory> targetArmoryList = baseArmories.stream().skip(1).filter(baseArmory -> baseArmory.getHoneLvl() < maxLvl).toList();

        double totalCost = 0;
        int totalIncrement = 0;

        // 모든 방어구가 균등하게 강화되어 있는 경우
        if (targetArmoryList.isEmpty()) {
            System.out.println("모든 부위에 " + (maxLvl + 1) + "강 강화를 진행함");
            totalCost += T4ArmorHone.findTotalCostByTargetLevel(ARMOR_TYPES, maxLvl + 1, true);

            System.out.println("모든 부위를 " + (maxLvl + 1) + "강 강화를 진행한 총 비용: " + totalCost);

            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(ARMOR_TYPES, maxLvl + 1);

            System.out.println("totalIncrement = " + totalIncrement);

        } else {
            // 일부 방어구가 다른 방어구보다 높은 단계를 갖고 있을 경우
            List<String> typeList = targetArmoryList.stream().map(BaseArmory::getType).toList();

            totalCost += T4ArmorHone.findTotalCostByTargetLevel(typeList, maxLvl + 1, true);

            System.out.println(typeList + " 부위 장비에" + (maxLvl + 1) + "강 강화를 진행한 총 비용: " + totalCost);

            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(typeList, maxLvl + 1);

            System.out.println("totalIncrement = " + totalIncrement);
        }

        return calculateHoneIncrementSpecUp(totalArmoryEffect, "방어구", totalIncrement);
    }
}
