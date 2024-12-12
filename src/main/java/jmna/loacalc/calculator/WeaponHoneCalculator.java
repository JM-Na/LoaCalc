package jmna.loacalc.calculator;

import jmna.loacalc.calculator.engraving.*;
import jmna.loacalc.calculator.hone.T4ArmorHone;
import jmna.loacalc.calculator.hone.T4ArmorIncrement;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeaponHoneCalculator {

    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final StatEffectCalculator statEffectCalculator;

    public void calculateExpectedValue(TotalArmoryEffect totalArmoryEffect, int level) {
        System.out.println("weaponLevel = " + level);

        if (level == 25) {
            log.info("무기 강화 수치가 이미 최대입니다.");
        }

        int increment = T4WeaponHone.findIncrementByTargetLevel(level);
        log.info(level + "강 도달로 증가하는 무기 공격력 수치: " + increment);
        double cost = T4WeaponHone.findCostByTargetLevel(level, true);
        log.info((level + 1) + "강을 위한 소모 골드: " + cost);

        // 기존 무기에서의 공격력 수치
        double preAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        // +1 강화된 상태에서의 공격력 수치
        totalArmoryEffect.addWeaponPower(increment);
        double nextAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        double incrementOnAtkPower = (nextAtkPower - preAtkPower) / preAtkPower;
        System.out.println("incrementOnAtkPower = " + incrementOnAtkPower * 100 + "%");
    }

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
                    expectedSpecUp += (incrementByBook / (previousIncrementByEngraving+100));
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
                    double critDmgIncrement =  incrementByBook / 100;
                    double outgoingDmgWhenCrit = totalArmoryEffect.getOutgoingDmgWhenCrit()
                            .stream().reduce(1.0, ((a, b) -> a * (b + 1)));

                    expectedSpecUp += (critRate*critDmgIncrement*outgoingDmgWhenCrit) / (critRate*critDmg*outgoingDmgWhenCrit + (1 - critRate));
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

    public void calculateArmorExpectedValue(TotalArmoryEffect totalArmoryEffect, String type, int level) {

        if (level == 25) {
            log.info("방어구 강화 수치가 이미 최대입니다.");
        }

        T4ArmorIncrement increment = T4ArmorHone.findIncrementByTargetLevel(type, level);
        log.info(level + "강 도달로 증가하는 능력치: " + increment);
        double cost = T4ArmorHone.findCostByTargetLevel(type, level, true);
        log.info((level + 1) + "강을 위한 소모 골드: " + cost);

        // 기존 무기에서의 공격력 수치
        double preAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        // +1 강화된 상태에서의 공격력 수치
        if (increment != null) {
            totalArmoryEffect.addMainStat(increment.getMainStat());
        }
        double nextAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        double incrementOnAtkPower = (nextAtkPower - preAtkPower) / preAtkPower;
        System.out.println("incrementOnAtkPower = " + incrementOnAtkPower * 100 + "%");
    }
}
