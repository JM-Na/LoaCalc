package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        int totalTranscendence = characterEquipment.getTotalTranscendence();


        TranscendenceEffect transcendenceEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);

        System.out.println("sum = " + transcendenceEffect);
    }

    @Test
    void elixirTest() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();

        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);

        System.out.println("sum = " + elixirEffect);
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

        armoryEffectCalculator.calculateSubEquipmentEffect(subEquipments);

    }
}