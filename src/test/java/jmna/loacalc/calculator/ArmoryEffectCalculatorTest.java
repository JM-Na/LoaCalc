package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        List<TranscendenceEffect> transcendenceEffects = new ArrayList<>();

        for (BaseArmory baseArmory : baseArmories) {
            TranscendenceEffect transcendenceEffect = armoryEffectCalculator.calculateTranscEffect(baseArmory, totalTranscendence);
            System.out.println("transcendenceEffect = " + transcendenceEffect);
            transcendenceEffects.add(transcendenceEffect);
        }

        TranscendenceEffect sum = transcendenceEffects.stream().reduce(new TranscendenceEffect(), TranscendenceEffect::merge);
        System.out.println("sum = " + sum);
    }

    @Test
    void elixirTest() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();

        for (BaseArmory baseArmory : baseArmories) {
            if(baseArmory.getClass().equals(Armor.class)){
                List<ElixirEffect> elixirEffects = armoryEffectCalculator.calculateElixirEffect((Armor) baseArmory);
                System.out.println("elixirEffects = " + elixirEffects);
            }
        }

    }
}