package jmna.loacalc.calculator.v2;

import jmna.loacalc.calculator.StatEffectCalculator;
import jmna.loacalc.calculator.TotalArmoryEffect;
import jmna.loacalc.calculator.TotalArmoryEffectCalculator;
import jmna.loacalc.calculator.specup.AccessorySpecUp;
import jmna.loacalc.calculator.subequipments.AccessoryOptionDto;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.accessory.HoneEffect;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.auction.AccessoryOptionType;
import jmna.loacalc.processor.auction.T4AccessoryData;
import jmna.loacalc.repository.T4AccessoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccessorySpecUpCalculator {
    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final StatEffectCalculator statEffectCalculator;
    private final T4AccessoryRepository accessoryRepository;

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

        int price = accessoryRepository.findByName(expAcc.toString()).get().getPrice();


        String description = prevAcc.getType() + " " +  prevAcc.getPartName() + " (" + T4AccessoryData.getOptionInfo(prevAcc) + ") -> <br>" + expAcc.getType() + " " +  expAcc.getPartName()+ " (" + T4AccessoryData.getOptionInfo(expAcc) + ")";

        return new AccessorySpecUp(description, prevAcc.getPartName(), expectedSpecUp, price, seq);
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

}
