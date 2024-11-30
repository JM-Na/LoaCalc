package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.elixir.ElixirType;
import jmna.loacalc.calculator.transcendence.TranscEffect;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.EquipmentProcessor;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.Armor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.ElixirData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ArmoryEffectCalculatorTest {

    @Autowired
    private ArmoryEffectCalculator armoryEffectCalculator;
    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EquipmentProcessor equipmentProcessor;

    @Test
    void transcTest() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        for (BaseArmory baseArmory : baseArmories) {
            String type = baseArmory.getType();
            System.out.println("type = " + type);
        }
        int totalTranscendence = characterEquipment.getTotalTranscendence();


        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);

        System.out.println("sum = " + transcEffect);
    }

    @Test
    void elixirTest() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();

//        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
//        System.out.println("sum = " + elixirEffect);

        List<ElixirEffect> elixirEffects = new ArrayList<>();
        int totalElixirLvl = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class)) {
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                ElixirEffect elixirEffect = new ElixirEffect();
                elixirEffect.setArmoryType(baseArmory.getType());
                for (ElixirData elixirData : elixirDataList) {
                    String effectName = elixirData.getEffectName();
                    totalElixirLvl += elixirData.getLevel();

                    ElixirType.applyEffect(effectName, elixirEffect, elixirData);

                }
                elixirEffects.add(elixirEffect);
            }
        }
        ElixirEffect totalElixirEffect = elixirEffects.stream().reduce(new ElixirEffect(), ElixirEffect::merge);

        switch (totalElixirEffect.getSetEffect()) {
            case "회심" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addOutGoingDmgWhenCrit(6);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addOutGoingDmgWhenCrit(6);
            }
            case "달인" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addCritRate(7);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addAddDmg(8.5);
            }
            case "선각자" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addAtkBuffEfficiency(8);
                if (totalElixirLvl >= 40) {
                    totalElixirEffect.addCoolDownReduction(5);
                    totalElixirEffect.addAtkBuffEfficiency(6);
                }
            }
            case "진군" -> {
                if (totalElixirLvl >= 35)
                    totalElixirEffect.addAdvanceEtherWeaponPower(2230);
                if (totalElixirLvl >= 40)
                    totalElixirEffect.addAtkBuffEfficiency(6);
            }
        }
        System.out.println("totalElixirEffect = " + totalElixirEffect);
    }

    @Test
    void armoryEffect() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();

        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        System.out.println("armoryEffect = " + armoryEffect);
    }

    @Test
    void totalEffect() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();

    }

    @Test
    void subEquipmentEffect() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        armoryEffectCalculator.calculateAccessoryEffect(subEquipments);
    }
}