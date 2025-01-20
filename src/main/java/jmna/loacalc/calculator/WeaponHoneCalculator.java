package jmna.loacalc.calculator;

import jmna.loacalc.calculator.engraving.*;
import jmna.loacalc.calculator.hone.AdvancedHone;
import jmna.loacalc.calculator.hone.T4ArmorHone;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.accessory.HoneEffect;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import jmna.loacalc.processor.auction.AccessoryOptionType;
import jmna.loacalc.processor.auction.T4AccessoryData;
import jmna.loacalc.processor.auction.T4GemData;
import jmna.loacalc.repository.RelicEngravingBookRepository;
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
    private final StatEffectCalculator statEffectCalculator;
    private final RelicEngravingBookRepository engravingBookRepository;

    private static final List<String> ARMOR_TYPES = List.of("머리", "어깨", "상의", "하의", "장갑");

    /**
     * 4티어 무기를 1등급 강화했을 때의 효율을 계산하는 코드
     *
     * @return
     */
    public HoneSpecUp calculateExpectedValue(TotalArmoryEffect totalArmoryEffect, int level) {
        System.out.println("weaponLevel = " + level);

        if (level == 25) {
            log.info("무기 강화 수치가 이미 최대입니다.");
            return new HoneSpecUp();
        }

        int increment = T4WeaponHone.findIncrementByTargetLevel(level);
        log.info((level + 1) + "강 도달로 증가하는 무기 공격력 수치: " + increment);
        double cost = T4WeaponHone.findCostByTargetLevel(level, true);
        log.info((level + 1) + "강을 위한 소모 골드: " + cost);

        double incrementOnAtkPower = calculateHoneIncrementSpecUp(totalArmoryEffect, "무기", increment);

        return new HoneSpecUp("무기 " + (level + 1) + "강 강화", List.of("무기"), incrementOnAtkPower * 100, cost);
    }

    public List<HoneSpecUp> checkWeaponHone(List<BaseArmory> baseArmories, TotalArmoryEffect totalArmoryEffect) {
        Weapon weapon = (Weapon) baseArmories.get(0);

        Integer advancedHone = weapon.getAdvancedHone();
        Integer honeLvl = weapon.getHoneLvl();
        List<HoneSpecUp> honeSpecUpList = List.of(calculateExpectedValue(totalArmoryEffect, honeLvl), calculateAdvancedHoneExpectedValue(totalArmoryEffect, "무기", advancedHone));
        return honeSpecUpList;
    }

    /**
     * 유물 각인서를 읽었을 때의 효율을 계산하는 코드
     *
     * @return
     */
    public List<EngravingSpecUp> calculateExpectedValueByRelicEngravingBook(TotalArmoryEffect totalArmoryEffect,
                                                                            List<CharacterEngraving> characterEngravingList,
                                                                            CharacterProfile characterProfile) {
        List<EngravingSpecUp> engravingSpecUpList = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravingList) {
            String grade = characterEngraving.getGrade();
            String name = characterEngraving.getName();
            int lvl = characterEngraving.getLvl();
            log.info("각인: " + name + ", 등급: " + grade + ", 레벨: " + lvl);
            // 각인을 4레벨까지 올린다고 가정했을 때 소모되는 골드 계산
//            Double price = RelicEngravingBookData.getPriceByName(name + " 각인서");
            Double price = engravingBookRepository.findByName(name + " 각인서").get().getPrice();
            int number = (4 - lvl) * 5;
            double totalPrice = price * number;
            log.info("소모 각인서 갯수: " + number + ", 예상 소모 각인서 가격: " + totalPrice);
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
                    expectedSpecUp += (incrementByBook / (previousIncrementByEngraving + 100));
                }
                case "Raid Captain" -> {
                    log.info("돌격 대장이 상승합니다. 상승량: " + incrementByBook);
                    double previousIncrementByEngraving = calculateRaidCaptainEffect(totalArmoryEffect.getRaidCaptain(), calculateSpeed(totalArmoryEffect, characterProfile, 0));
                    // 상승된 값을 적용하여 돌격대장의 데미지 상승량 계산
                    double incrementByAppliedBook = calculateRaidCaptainEffect(totalArmoryEffect.getRaidCaptain() + incrementByBook, calculateSpeed(totalArmoryEffect, characterProfile, 0));
                    expectedSpecUp += ((incrementByAppliedBook - previousIncrementByEngraving) / (previousIncrementByEngraving + 100));
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

                    expectedSpecUp += (incrementByBook * critDmg * outgoingDmgWhenCrit - incrementByBook) / (critRate * critDmg * outgoingDmgWhenCrit + (100 - critRate));
                }
//                case "Defense Percent" -> ;
                case "Crit Damage" -> {
                    log.info("치명타 피해량이 상승합니다. 상승량: " + incrementByBook);
                    double critRate = totalArmoryEffect.getCritRate() + statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
                    double critDmg = (totalArmoryEffect.getCritDmg() + 200) / 100;
                    double critDmgIncrement = incrementByBook / 100;
                    double outgoingDmgWhenCrit = totalArmoryEffect.getOutgoingDmgWhenCrit()
                            .stream().reduce(1.0, ((a, b) -> a * (b + 1)));

                    expectedSpecUp += (critRate * critDmgIncrement * outgoingDmgWhenCrit) / (critRate * critDmg * outgoingDmgWhenCrit + (100 - critRate));
                }
//                case "Heal Shield Efficiency" -> ;
//                case "Speed" -> ;
            }

            EngravingSpecUp engravingSpecUp = new EngravingSpecUp(grade + " " + name + " 각인서 " + number + "장", name, expectedSpecUp * 100, totalPrice);
            engravingSpecUpList.add(engravingSpecUp);
        }
        return engravingSpecUpList;
    }

    public double calculateSpeed(TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile, Integer characterBuff) {
        double speedBySwiftness = statEffectCalculator.calculateSwiftnessSpeed(characterProfile.getSwiftness());
        double atkSpeed = totalArmoryEffect.getAtkSpeed();
        double movementSpeed = totalArmoryEffect.getMoveSpeed();
        // 9 -> 서폿 정열의 춤, 5 -> 만찬 공이속
        return 100 + speedBySwiftness + movementSpeed + characterBuff + 9 + 5;
    }

    public double calculateRaidCaptainEffect(double raidCaptain, double movementSpeed) {
        // 이동 속도의 최대치는 140%로 고정되어 있음.
        double compensatedSpeed = Math.min(140, movementSpeed) - 100;
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

    public HoneSpecUp calculateAdvancedHoneExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) {

        if (level < 10)
            level = 10;
        else
            level = 20;


        if (level != 20) {
            int increment = AdvancedHone.findIncrementByNameAndTargetLevel(type, level);
//            log.info(type + " 부위 / " + level + "강 도달로 증가하는 능력치: " + increment);
            double cost = AdvancedHone.findCostByTargetLevel(type, level, true);
//            log.info(type + " 부위 " + level + "강 도달까지 소모 예상 골드: " + cost);
            return new HoneSpecUp("무기 상급재련 " + level + "단계", List.of("무기"), calculateHoneIncrementSpecUp(totalArmoryEffect, type, increment), cost);
        }

        return new HoneSpecUp("", List.of(""), 0, 0);
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


        Integer maxAdvancedHone = highestAdvancedHone.getAdvancedHone();

        int totalIncrement = 0;
        double totalCost = 0;
        List<String> targetList = new ArrayList<>();
        String description = "";

        // 상급 재련이 전혀 진행되지 않은 경우
        if (maxAdvancedHone == 0) {
            description = "상급 재련 10단계";
            totalCost += AdvancedHone.findTotalCostByTargetLevel(ARMOR_TYPES, 10, true);
            totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 10);
            targetList.addAll(ARMOR_TYPES);
        } else {
            List<BaseArmory> targetAdvancedArmoryList = baseArmories.stream().filter(baseArmory -> baseArmory.getAdvancedHone() < maxAdvancedHone).toList();

            // 상급 재련 균등하게 이루어진 경우
            if (targetAdvancedArmoryList.isEmpty()) {
                if (maxAdvancedHone == 20)
                    System.out.println("상급 재련이 이미 완료됨");
                else if (maxAdvancedHone == 10) {
                    description = "상급 재련 20단계";
                    totalCost += AdvancedHone.findTotalCostByTargetLevel(ARMOR_TYPES, 20, true);
                    totalIncrement += AdvancedHone.findTotalIncrementByNameAndTargetLevel(ARMOR_TYPES, 20);
                    targetList.addAll(ARMOR_TYPES);
                }
            } else {
                // 상급 재련이 10 미만일 시 10, 20 미만일 시 20을 제안한다.
//                for (BaseArmory baseArmory : targetAdvancedArmoryList) {
//
//                    Integer honeLvl = baseArmory.getAdvancedHone();
//                    String type = baseArmory.getType();
//
//                    int targetLevel = honeLvl < 10 ? 10 : 20;
//                    System.out.println("상급재련 " + targetLevel + "강을 진행함");
//
//                    totalCost += AdvancedHone.findCostByTargetLevel(type, targetLevel, true);
//                    totalIncrement += AdvancedHone.findIncrementByNameAndTargetLevel(type, targetLevel);
//                }

                if (maxAdvancedHone == 10) {
                    description = "상급 재련 10단계";
                    for (BaseArmory baseArmory : targetAdvancedArmoryList) {

                        Integer honeLvl = baseArmory.getAdvancedHone();
                        String type = baseArmory.getType();

                        if (honeLvl < 10) {
                            targetList.add(type);
                            totalCost += AdvancedHone.findCostByTargetLevel(type, 10, true);
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
                            totalCost += AdvancedHone.findCostByTargetLevel(type, 20, true);
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
            totalCost += T4ArmorHone.findTotalCostByTargetLevel(ARMOR_TYPES, maxLvl + 1, true);
            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(ARMOR_TYPES, maxLvl + 1);

        } else {
            description = "대상 부위를 " + (maxLvl + 1) + "강 강화";
            // 일부 방어구가 다른 방어구보다 높은 단계를 갖고 있을 경우
            List<String> typeList = targetArmoryList.stream().map(BaseArmory::getType).toList();
            targetList.addAll(typeList);

            totalCost += T4ArmorHone.findTotalCostByTargetLevel(typeList, maxLvl + 1, true);
            totalIncrement += T4ArmorHone.findTotalIncrementByTargetLevel(typeList, maxLvl + 1);
        }

        double expectedSpecUp = calculateHoneIncrementSpecUp(totalArmoryEffect, "방어구", totalIncrement);

        return new HoneSpecUp(description, targetList, expectedSpecUp * 100, totalCost);
    }


    public List<AccessorySpecUp> checkAccessory(List<SubEquipment> subEquipments, TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile) {
        List<AccessorySpecUp> finalAccessorySpecUpList = new ArrayList<>();
        int seq = 0;
        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class)) {
                seq++;
                List<AccessorySpecUp> accessorySpecUpList = new ArrayList<>();
                List<HoneEffect> honeEffects = ((Accessory) subEquipment).getHoneEffects();
                List<AccessoryOptionType> optionList = honeEffects.stream().map(HoneEffect::getType).filter(Objects::nonNull).toList();

                T4AccessoryData target = T4AccessoryData.findTypeByOptions(optionList);

                String partName = target.getPartName();

                List<T4AccessoryData> dataList = T4AccessoryData.getListOfData().stream().filter(value -> value.getPartName().equals(partName)).toList();

                for (T4AccessoryData t4AccessoryData : dataList)
                    accessorySpecUpList.add(calculateAccessoryIncrement(target, t4AccessoryData, totalArmoryEffect, characterProfile, seq));
                finalAccessorySpecUpList.addAll(accessorySpecUpList.stream().filter(value -> value.getExpectedSpecUp() > 0).toList());
            }
        }
        return finalAccessorySpecUpList;
    }

    public AccessorySpecUp calculateAccessoryIncrement(T4AccessoryData prevAcc, T4AccessoryData expAcc, TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile, int seq) {

        List<String> options = T4AccessoryData.findOptionsByType(prevAcc.getPartName());

        if (options == null)
            return null;

        AccessoryOptionDto dto = new AccessoryOptionDto();

        checkAccessoryOptionInfo(prevAcc, options, dto, false);
        checkAccessoryOptionInfo(expAcc, options, dto, true);

        calculateAccessoryDmg(dto, totalArmoryEffect, characterProfile, prevAcc.getPartName());

        double prevDmg = dto.getPrevDmg();
        double expDmg = dto.getExpDmg();
        double expectedSpecUp = expDmg / prevDmg * 100 - 100;

//        System.out.println(prevAcc + " -> " + expAcc);
//        System.out.println("prevDmg = " + prevDmg);
//        System.out.println("expDmg = " + expDmg);
//        System.out.println("Expected Spec Up = " + (expDmg / prevDmg * 100 - 100));
//        System.out.println("------------------------------------------------------------- ");

        String description = prevAcc.getType() + " " +  prevAcc.getPartName() + " (" + T4AccessoryData.getOptionInfo(prevAcc) + ") -> <br>" + expAcc.getType() + " " +  expAcc.getPartName()+ " (" + T4AccessoryData.getOptionInfo(expAcc) + ")";

        return new AccessorySpecUp(description, prevAcc.getPartName(), expectedSpecUp, expAcc.getPrice(), seq);
    }

    private void checkAccessoryOptionInfo(T4AccessoryData acc, List<String> options, AccessoryOptionDto dto, boolean isAdding) {
        String option1Name = options.get(0);
        String option2Name = options.get(1);

        if (Objects.equals(acc.getEffectName1(), option1Name))
            dto.addOptionIncrement(option1Name, AccessoryOptionType.findByTypeAndOptionRank(option1Name, acc.getEffectRank1()).getIncrement(), isAdding);
        else if (Objects.equals(acc.getEffectName1(), option2Name))
            dto.addOptionIncrement(option2Name, AccessoryOptionType.findByTypeAndOptionRank(option2Name, acc.getEffectRank1()).getIncrement(), isAdding);
        if (Objects.equals(acc.getEffectName2(), option1Name))
            dto.addOptionIncrement(option1Name, AccessoryOptionType.findByTypeAndOptionRank(option1Name, acc.getEffectRank2()).getIncrement(), isAdding);
        else if (Objects.equals(acc.getEffectName2(), option2Name))
            dto.addOptionIncrement(option2Name, AccessoryOptionType.findByTypeAndOptionRank(option2Name, acc.getEffectRank2()).getIncrement(), isAdding);
    }

    private void calculateAccessoryDmg(AccessoryOptionDto dto, TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile, String partName) {
        double option1Increment = dto.getOption1Increment();
        double option2Increment = dto.getOption2Increment();

        switch (partName) {
            case "반지" -> {
                int bluntSpike = totalArmoryEffect.getBluntSpike();
                double critRate = totalArmoryEffect.getCritRate() + statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
                double critDmg = totalArmoryEffect.getCritDmg() + 200;
                double outgoingDmgWhenCrit = totalArmoryEffect.getOutgoingDmgWhenCrit()
                        .stream().reduce((Double::sum)).get();
                if (bluntSpike != 0)
                    critRate = Math.min(80, critRate);
                else
                    critRate = Math.min(100, critRate);
                option1Increment += critRate;
                option2Increment += critDmg;
                if (bluntSpike != 0)
                    option1Increment = Math.min(80, option1Increment);
                else
                    option1Increment = Math.min(100, option1Increment);
                double prevDmg = critRate / 100 * critDmg / 100 * outgoingDmgWhenCrit + 1 * (1 - (critRate / 100));
                double expDmg = option1Increment / 100 * option2Increment / 100 * outgoingDmgWhenCrit + (1 - (option1Increment / 100));
                dto.setPrevDmg(prevDmg);
                dto.setExpDmg(expDmg);
            }
            case "귀걸이" -> {
                dto.setPrevDmg(totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect));
                dto.setExpDmg(totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, option1Increment, option2Increment));
            }
            case "목걸이" -> {
                double addDmg = totalArmoryEffect.getAddDmg();
                List<Double> outgoingDmgList = new ArrayList<>(totalArmoryEffect.getOutgoingDmg());

                double prevDmg = (addDmg / 100 + 1) * outgoingDmgList.stream().map(value -> value / 100 + 1).reduce(1.0, (a, b) -> a * b);
                dto.setPrevDmg(prevDmg);

                addDmg += option1Increment;
                for (Double outgoingDmg : dto.getOutgoingDmg()) {
                    if (outgoingDmg < 0)
                        outgoingDmgList.remove(-outgoingDmg);
                    else
                        outgoingDmgList.add(outgoingDmg);
                }

                double expDmg = (addDmg / 100 + 1) * outgoingDmgList.stream().map(value -> value / 100 + 1).reduce(1.0, (a, b) -> a * b);
                dto.setExpDmg(expDmg);
            }
        }
    }

    public List<GemSpecUp> calculateGemSpecUp(TotalArmoryEffect totalArmoryEffect) {

        List<GemSpecUp> gemSpecUpList = new ArrayList<>();

        for (int i = 6; i <= 10; i++) {
//            System.out.println("Expected Gem Level = " + i);
            T4GemData target = T4GemData.findDataByLvl(i);
            T4GemData prev = T4GemData.findDataByLvl(i - 1);

            double prevAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, prev.getBasicAtk(), prev.getEffect());
            double expectedAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, target.getBasicAtk(), target.getEffect());

//            System.out.println("prevAtkPower = " + prevAtkPower);
//            System.out.println("expectedAtkPower = " + expectedAtkPower);
//            System.out.println("expected spec up = " + (expectedAtkPower / prevAtkPower - 1));
//            System.out.println("---------------------------------------------");

            gemSpecUpList.add(new GemSpecUp(i + "레벨 겁화 보석으로 업그레이드", i, (expectedAtkPower / prevAtkPower - 1), target.getPrice()));
        }
        return gemSpecUpList;
    }
}
