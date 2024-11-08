package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.processor.CharacterEngraving;
import jmna.loacalc.processor.EngravingProcessor;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TotalEffectCalculatorTest {

    @Autowired
    private EngravingEffectCalculator engravingEffectCalculator;
    @Autowired
    private ArmoryEffectCalculator armoryEffectCalculator;
    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EquipmentProcessor equipmentProcessor;
    @Autowired
    private EngravingProcessor engravingProcessor;
    @Autowired
    private TotalEffectCalculator totalEffectCalculator;


    @Test
    void totalEffect() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        TranscendenceEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        ArmoryEngravings armoryEngravings = armoryClient.getArmoryEngravings("레게머리뿌뿌뿡");
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);


        TotalEffect totalEffect = totalEffectCalculator.calculateTotalEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        System.out.println("totalEffect = " + totalEffect);
    }

}