package jmna.loacalc.calculator.v2;

import jmna.loacalc.calculator.TotalArmoryEffect;
import jmna.loacalc.calculator.TotalArmoryEffectCalculator;
import jmna.loacalc.calculator.hone.AdvancedHone;
import jmna.loacalc.calculator.hone.T4ArmorHone;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.calculator.hone.ArmoryLevelData;
import jmna.loacalc.calculator.specup.HoneSpecUp;
import jmna.loacalc.processor.armory.equipment.armory.Armor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import jmna.loacalc.repository.HoneIngredientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeaponHoneCalculator {

    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final HoneIngredientRepository honeIngredientRepository;

    private static final List<String> ARMOR_TYPES = List.of("머리", "어깨", "상의", "하의", "장갑");

    /**
     * 4티어 무기를 1등급 강화했을 때의 효율을 계산하는 코드
     */
    public HoneSpecUp calculateExpectedValue(TotalArmoryEffect totalArmoryEffect, int level) {
        System.out.println("weaponLevel = " + level);

        if (level == 25) {
            log.info("무기 강화 수치가 이미 최대입니다.");
            return new HoneSpecUp();
        }

        int increment = T4WeaponHone.findIncrementByTargetLevel(level);
        log.info((level + 1) + "강 도달로 증가하는 무기 공격력 수치: " + increment);
        double cost = calculateHoneCost("무기", level);

        log.info((level + 1) + "강을 위한 소모 골드: " + cost);

        double incrementOnAtkPower = calculateHoneIncrementSpecUp(totalArmoryEffect, "무기", increment);

        return new HoneSpecUp("무기 " + (level + 1) + "강 강화", List.of("무기"), incrementOnAtkPower * 100, cost);
    }

    public List<HoneSpecUp> checkWeaponHone(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        Weapon weapon = (Weapon) baseArmories.get(0);

        Integer advancedHone = weapon.getAdvancedHone();
        Integer honeLvl = weapon.getHoneLvl();
        return List.of(calculateExpectedValue(totalArmoryEffect, honeLvl), calculateAdvancedHoneExpectedValue(totalArmoryEffect, "무기", advancedHone));
    }

    public void calculateArmorExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) throws Exception {

        if (level % 10 != 0)
            throw new Exception("올바르지 않은 재련 목표 레벨입니다. 재련 목표레벨은 25 미만이어야 합니다.");

        int increment = T4ArmorHone.findIncrementByTargetLevel(type, level);
        log.info(level + "강 도달로 증가하는 능력치: " + increment);


        double cost = calculateHoneCost(type, level);

        log.info((level) + "강을 위한 소모 골드: " + cost);

        double incrementOnAtkPower = calculateHoneIncrementSpecUp(totalArmoryEffect, type, increment);
    }

    public HoneSpecUp calculateAdvancedHoneExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) {

        if (level < 10)
            level = 10;
        else
            level = 20;

        if (level != 20) {
            int increment = AdvancedHone.findIncrementByNameAndTargetLevel(type, level);
//            log.info(type + " 부위 / " + level + "강 도달로 증가하는 능력치: " + increment);

            double cost = calculateAdvancedHoneCost(type, level);

//            log.info(type + " 부위 " + level + "강 도달까지 소모 예상 골드: " + cost);
            return new HoneSpecUp("무기 상급재련 " + level + "단계", List.of("무기"), calculateHoneIncrementSpecUp(totalArmoryEffect, type, increment), cost);
        }

        return new HoneSpecUp("", List.of(""), 0, 0);
    }

    private double calculateAdvancedHoneCost(String type, int level) {
        if (!Objects.equals(type, "무기")) {
            type = "방어구";
        }
        AdvancedHone target = AdvancedHone.of(type, level);

        int gold = target.getGold();
        double fragmentPrice = target.getFragment() * honeIngredientRepository.findByName("운명의 파편 주머니(중)").get().getPrice() / 1000.0;
        double destStonePrice = target.getDestGuard();
        if (type.equals("무기"))
            destStonePrice *= honeIngredientRepository.findByName("운명의 파괴석").get().getPrice() / 10.0;
        else
            destStonePrice *= honeIngredientRepository.findByName("운명의 수호석").get().getPrice() / 10.0;
        double leapStonePrice = target.getLeapStone() * honeIngredientRepository.findByName("운명의 돌파석").get().getPrice();
        double fusionStone = target.getFusionStone() * honeIngredientRepository.findByName("아비도스 융화 재료").get().getPrice();

        return gold + destStonePrice + leapStonePrice + fusionStone;
    }

    /**
     * TotalArmoryEffect, 장비 종류, 스탯 상승량을 받아서 전체 스펙업 상승량을 계산하는 코드
     */
    private double calculateHoneIncrementSpecUp(TotalArmoryEffect totalArmoryEffect, String type, int increment) {
        double preAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        double laterAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, type, increment);

        double incrementOnAtkPower = (laterAtkPower - preAtkPower) / preAtkPower;
        System.out.println("incrementOnAtkPower = " + incrementOnAtkPower * 100 + "%");
        return incrementOnAtkPower;
    }

    // 방어구의 재련, 상급 재련 단계를 확인하여 각각 어떤 식으로 재련을 행할지 제시하는 코드
    public List<HoneSpecUp> checkHoneArmory(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        List<HoneSpecUp> honeSpecUpList = List.of(calculateArmorHoneExpectedValue(baseArmories, totalArmoryEffect), calculateArmorAdvancedHoneExpectedValue(baseArmories, totalArmoryEffect));
        System.out.println("honeSpecUpList = " + honeSpecUpList);
        return honeSpecUpList;
    }

    /**
     * 장비 데이터를 받아서 방어구의 상급재련 상승 효율을 계산하는 코드
     */
    private HoneSpecUp calculateArmorAdvancedHoneExpectedValue(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        BaseArmory highestAdvancedHone = baseArmories.stream().skip(1).skip(1).max(Comparator.comparing(BaseArmory::getAdvancedHone)).orElse(null);


        int maxAdvancedHone = 0;
        if (highestAdvancedHone != null) {
            maxAdvancedHone += highestAdvancedHone.getAdvancedHone();
        }

        int totalIncrement = 0;
        double totalCost = 0;
        List<String> targetList = new ArrayList<>();
        String description = "";

        // 상급 재련이 전혀 진행되지 않은 경우
        if (maxAdvancedHone == 0) {
            description = "상급 재련 10단계";
            totalCost += calculateAdvancedHoneCost("상의", 10) * 5;
            totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 10);
            targetList.addAll(ARMOR_TYPES);
        } else {
            int finalMaxAdvancedHone = maxAdvancedHone;
            List<BaseArmory> targetAdvancedArmoryList = baseArmories.stream().filter(baseArmory -> baseArmory.getAdvancedHone() < finalMaxAdvancedHone).toList();

            // 상급 재련 균등하게 이루어진 경우
            if (targetAdvancedArmoryList.isEmpty()) {
                if (maxAdvancedHone == 20)
                    System.out.println("상급 재련이 이미 완료됨");
                else if (maxAdvancedHone == 10) {
                    description = "상급 재련 20단계";
                    totalCost += calculateAdvancedHoneCost("상의", 20) * 5;
                    totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 20);
                    targetList.addAll(ARMOR_TYPES);
                }
            } else {
                if (maxAdvancedHone == 10) {
                    description = "상급 재련 10단계";
                    for (BaseArmory baseArmory : targetAdvancedArmoryList) {

                        Integer honeLvl = baseArmory.getAdvancedHone();
                        String type = baseArmory.getType();

                        if (honeLvl < 10) {
                            targetList.add(type);
                            totalCost += calculateAdvancedHoneCost(type, 10);
                            totalIncrement += AdvancedHone.findIncrementByNameAndTargetLevel(type, 10);
                        }
                    }
                } else if (maxAdvancedHone == 20) {
                    description = "상급 재련 20단계";
                    for (BaseArmory baseArmory : targetAdvancedArmoryList) {

                        Integer honeLvl = baseArmory.getAdvancedHone();
                        String type = baseArmory.getType();

                        if (honeLvl < 20) {
                            targetList.add(type);
                            totalCost += calculateAdvancedHoneCost(type, 20);
                            totalIncrement += AdvancedHone.findIncrementByNameAndTargetLevel(type, 20);
                        }
                    }
                }
            }
        }
        return new HoneSpecUp(description, targetList, calculateHoneIncrementSpecUp(totalArmoryEffect, "방어구", totalIncrement) * 100, totalCost);
    }

    /**
     * 장비 데이터를 받아서 방어구의 재련 등급 상승 효율을 계산하는 코드
     */
    private HoneSpecUp calculateArmorHoneExpectedValue(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        // 재련 단계를 순회하며 최고 레벨을 찾는다.
        // 무기를 제외하기 위해 .skip(1) 을 중간에 추가한다.
        BaseArmory highestArmor = baseArmories.stream().skip(1).max(Comparator.comparing(BaseArmory::getHoneLvl)).get();

        Integer maxLvl = highestArmor.getHoneLvl();

        // 재련 단계를 순회하며 최고 레벨보다 낮은 레벨의 장비를 찾는다.
        List<BaseArmory> targetArmoryList = baseArmories.stream().skip(1).filter(baseArmory -> baseArmory.getHoneLvl() < maxLvl).toList();

        double totalCost = 0;
        int totalIncrement = 0;
        List<String> targetList = new ArrayList<>();
        String description = "";

        // 모든 방어구가 균등하게 강화되어 있는 경우
        if (targetArmoryList.isEmpty()) {
            description = "모든 부위를 " + (maxLvl + 1) + "강 강화";
            targetList.addAll(ARMOR_TYPES);
            totalCost += calculateHoneCost("방어구", maxLvl + 1) * 5;
            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(ARMOR_TYPES, maxLvl + 1);

        } else {
            description = "대상 부위를 " + (maxLvl + 1) + "강 강화";
            // 일부 방어구가 다른 방어구보다 높은 단계를 갖고 있을 경우
            List<String> typeList = targetArmoryList.stream().map(BaseArmory::getType).toList();
            targetList.addAll(typeList);
            for (String type : typeList) {
                totalCost += calculateHoneCost(type, maxLvl + 1);
            }
            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(typeList, maxLvl + 1);
        }

        double expectedSpecUp = calculateHoneIncrementSpecUp(totalArmoryEffect, "방어구", totalIncrement);

        return new HoneSpecUp(description, targetList, expectedSpecUp * 100, totalCost);
    }

    /**
     * 현재 무기의 아이템 레벨을 받아서 재련 방식을 추천하는 코드 (v.상급재련 정상화 패치 이후)
     */
    public void test(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {

        for (BaseArmory baseArmory : baseArmories) {
            String partName = baseArmory.getType();
            System.out.println("partName = " + partName);
            Integer itemLvl = baseArmory.getItemLvl(); // 아이템 레벨
            Integer honeLvl = baseArmory.getHoneLvl(); // 재련 단계
            Integer advancedHone = baseArmory.getAdvancedHone(); // 상급 재련 단계
            int stat = 0;

            if (baseArmory instanceof Weapon) {
                stat += ((Weapon) baseArmory).getWeaponPower();
            } else if (baseArmory instanceof Armor) {
                stat += ((Armor) baseArmory).getMainStat();
            }

            if (honeLvl < 25) {
                int finalStat = ArmoryLevelData.getStatByItemLvlAndPartName(partName, itemLvl + 5);
                if (advancedHone == 30)
                    finalStat *= 1.02;
                if (advancedHone == 40)
                    finalStat *= 1.05;
                System.out.println("increment = " + (finalStat - stat));
                double increment = calculateHoneIncrementSpecUp(totalArmoryEffect, partName, finalStat - stat);

                double cost = calculateHoneCost(partName, honeLvl + 1);

                HoneSpecUp honeSpecUp = new HoneSpecUp("", List.of(partName), increment, cost);
            }

            for (int i = 1; i <= 4; i++) {

                int expectedAdvancedHone = i * 10;
                int finalStat = 0;

                if (advancedHone < expectedAdvancedHone) {
                    finalStat += ArmoryLevelData.getStatByItemLvlAndPartName(partName, itemLvl - advancedHone + expectedAdvancedHone);
                    if (expectedAdvancedHone == 30)
                        finalStat *= 1.02;
                    if (expectedAdvancedHone == 40)
                        finalStat *= 1.05;

                    System.out.println(expectedAdvancedHone + " 상급재련 강화");
                    double increment = calculateHoneIncrementSpecUp(totalArmoryEffect, partName, finalStat - stat);

                    double cost = calculateAdvancedHoneCost(partName, expectedAdvancedHone);

                    HoneSpecUp honeSpecUp = new HoneSpecUp("", List.of(partName), increment, cost);
                }

            }

        }

        /*
        상급 재련 소모 재료 정리 (단위: 회)

        0~20
        노숨노책 : 일반재료 56.54271
        선조숨 : 일반재료 50.505255, 숨 8.362152
        선조책 : 일반재료 47.928893, 책 7.930776
        선조숨책 : 일반재료 43.522805, 숨책 7.195192

        20~40
        노숨 : 일반재료 53.184752
        숨 : 일반재료47.82945 숨 8.588448
         */
    }

    private double calculateHoneCost(String type, int honeLvl) {

        if (type.equals("무기")) {
            T4WeaponHone target = T4WeaponHone.of(honeLvl);

            int gold = target.getGold();
            double fragmentPrice = target.getFragment() * honeIngredientRepository.findByName("운명의 파편 주머니(중)").get().getPrice() / 1000.0;
            double destStonePrice = target.getDestStone() * honeIngredientRepository.findByName("운명의 파괴석").get().getPrice() / 10.0;
            double leapStonePrice = target.getLeapStone() * honeIngredientRepository.findByName("운명의 돌파석").get().getPrice();
            double fusionStone = target.getFusionStone() * honeIngredientRepository.findByName("아비도스 융화 재료").get().getPrice();

            return gold + destStonePrice + leapStonePrice + fusionStone;
        } else if (type.equals("방어구")) {
            T4ArmorHone target = T4ArmorHone.of(type, honeLvl);
            int gold = target.getGold();
            int leapStonePrice = target.getLeapStone() * honeIngredientRepository.findByName("운명의 돌파석").get().getPrice();
            int guardStonePrice = target.getGuardStone() * honeIngredientRepository.findByName("운명의 수호석").get().getPrice() / 10;
            int fusionStonePrice = target.getFusionStone() * honeIngredientRepository.findByName("아비도스 융화 재료").get().getPrice();
            int fragmentPrice = target.getFragment() * honeIngredientRepository.findByName("운명의 파편 주머니(중)").get().getPrice() / 1000;

            return gold + leapStonePrice + guardStonePrice + fusionStonePrice;
        }

        return 0;
    }

}
