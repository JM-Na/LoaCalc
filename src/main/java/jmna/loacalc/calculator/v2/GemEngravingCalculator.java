package jmna.loacalc.calculator.v2;

import jmna.loacalc.calculator.StatEffectCalculator;
import jmna.loacalc.calculator.TotalArmoryEffect;
import jmna.loacalc.calculator.TotalArmoryEffectCalculator;
import jmna.loacalc.calculator.engraving.EngravingAbilityStone;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.engraving.EngravingIncrementT4;
import jmna.loacalc.calculator.engraving.EngravingLvlIncrement;
import jmna.loacalc.calculator.specup.EngravingSpecUp;
import jmna.loacalc.calculator.specup.GemSpecUp;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import jmna.loacalc.processor.auction.T4GemData;
import jmna.loacalc.repository.RelicEngravingBookRepository;
import jmna.loacalc.repository.T4GemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GemEngravingCalculator {

    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final StatEffectCalculator statEffectCalculator;
    private final RelicEngravingBookRepository engravingBookRepository;
    private final T4GemRepository gemRepository;

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
            double price = engravingBookRepository.findByName(name + " 각인서").get().getPrice();
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

    public List<GemSpecUp> calculateGemSpecUp(TotalArmoryEffect totalArmoryEffect) {

        List<GemSpecUp> gemSpecUpList = new ArrayList<>();

        for (int i = 6; i <= 10; i++) {
            // 8겁화 기준으로 계산함
            T4GemData target = T4GemData.findDataByLvl(i);
            T4GemData prev = T4GemData.findDataByLvl(i - 1);

            double prevAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, prev.getBasicAtk(), prev.getEffect());
            double expectedAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect, target.getBasicAtk(), target.getEffect());

//            System.out.println("prevAtkPower = " + prevAtkPower);
//            System.out.println("expectedAtkPower = " + expectedAtkPower);
//            System.out.println("expected spec up = " + (expectedAtkPower / prevAtkPower - 1));
//            System.out.println("---------------------------------------------");

            int price = gemRepository.findByName(target.getName()).get().getPrice();

            gemSpecUpList.add(new GemSpecUp(i + "레벨 겁화 보석으로 업그레이드", i, (expectedAtkPower / prevAtkPower - 1), price * 8));
        }
        return gemSpecUpList;
    }
}
